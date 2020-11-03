package com.company;

public class Barbero implements Runnable{

    Barberia barberia;

    public void Barbero(){}

    public void dormir(){
        synchronized (this){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void afeitar(){
        synchronized (barberia.sillon){
            while(!barberia.sillon.ocupado ||barberia.sillon.cliente.afeitado){
                try{
                    System.out.println("El barbero se duerme");
                    barberia.sillon.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            System.out.println("Se le esta afeitando al cliente "+barberia.sillon.cliente.nombre);
        }
    }


    @Override
    public void run() {

    }
}
