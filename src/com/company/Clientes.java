package com.company;

public class Clientes implements Runnable{

    boolean afeitado;
    Barberia barberia;
    String nombre;

    public Clientes(Barberia barberia,String nombre) {
        this.barberia = barberia;
        this.afeitado = false;
        this.nombre = nombre;
    }

    @Override
    public synchronized void run() {
        barberia.entrar();
        barberia.ocuparSillon(this);
        synchronized (this){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        barberia.liberarSillon(this);
        barberia.salida();
    }
}
