package com.company;

import java.util.ArrayList;

public class Main {

    private static String nombre = "Cliente";
    private static  int i = 0;

    public static void main(String[] args) {

        Barberia barberia = new Barberia();
        Barbero barbero = new Barbero();
        barbero.run();
        while (true){
            i++;
            nombre = nombre+i;
            Clientes cliente = new Clientes(barberia,nombre);
            cliente.run();
        }
    }
}
