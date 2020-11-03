package com.company;

public class Barbero implements Runnable{

    Barberia barberia;

    public void Barbero(Barberia barberia){
        this.barberia = barberia;
    }

    public void afeitar() throws InterruptedException {
        //////////////////////////////////////////////
        /////////// BARBERO SE DUERME  //////////////
        /////////////////////////////////////////////
        synchronized (barberia.sillon){
            while(!barberia.sillon.ocupado || barberia.sillon.cliente.afeitado){
                try{
                    System.out.println("El barbero se duerme");
                    barberia.sillon.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            //////////////////////////////////////////////
            /////////// AFEITADO A CLIENTE //////////////
            /////////////////////////////////////////////
            try{
                System.out.println("Le esta afeitando al cliente "+barberia.sillon.cliente.nombre);
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            barberia.sillon.cliente.afeitado = true;
            synchronized (barberia.sillon.cliente){
                barberia.sillon.cliente.notifyAll();
            }
        }
    }


    @Override
    public void run() {
        while (true){
            try {
                afeitar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
