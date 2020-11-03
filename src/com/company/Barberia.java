package com.company;

import java.util.concurrent.Semaphore;

public class Barberia {
    Sillon sillon = new Sillon();
    Semaphore semaforoEntrada = new Semaphore(4);

    public void entrar(){
        try{
            System.out.println("Entra a la barberia");
            semaforoEntrada.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void ocuparSillon(Clientes cliente){
        synchronized (sillon){
            while (sillon.ocupado){
                try {
                    sillon.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Se a sentado para afeitarse "+cliente.nombre);
            sillon.ocupado = true;
            sillon.cliente = cliente;
            sillon.notifyAll();
        }
    }

    public void liberarSillon(Clientes cliente){
        synchronized (sillon){
            System.out.println("Se a levantado para irse "+cliente.nombre);
            sillon.ocupado = false;
            sillon.cliente = null;
            sillon.notifyAll();
        }
    }

    public void salida(){
        System.out.println("El cliente se va de la barberia ");
        semaforoEntrada.release();
    }
}
