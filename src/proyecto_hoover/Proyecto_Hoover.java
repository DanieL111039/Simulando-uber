/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_hoover;

import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class Proyecto_Hoover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //inicializo grafo
       System.out.println("GRAFO LISTAS ENLAZADAS:");
       int cantidadNodos=15;
        Grafo grafo_prueba = new Grafo(cantidadNodos);
        
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(0,"Provenza");
        hashMap.put(1,"Plaza satelite");
        hashMap.put(2,"Diamante II");
        hashMap.put(3,"La cumbre");
        hashMap.put(4,"Barrio Caldas");
        hashMap.put(5,"UIS");
        hashMap.put(6,"Floridablanca");
        hashMap.put(7,"Mutis");
        hashMap.put(8,"Dangon");
        hashMap.put(9,"real de minas");
        hashMap.put(10,"centro comercial Cacique");
        hashMap.put(11,"Panamericana");
        hashMap.put(12,"Puente de la novena");
        hashMap.put(13,"Parque de los niños");
        hashMap.put(14,"UTS");
        
        grafo_prueba.agregarArista(0, 1, 14);
        grafo_prueba.agregarArista(0, 8, 12);
        grafo_prueba.agregarArista(0, 2, 10);
        grafo_prueba.agregarArista(0, 7, 26);
        grafo_prueba.agregarArista(1, 4, 4);
        grafo_prueba.agregarArista(1, 2, 8);
        grafo_prueba.agregarArista(1, 7, 34);
        grafo_prueba.agregarArista(2, 8, 21);
        grafo_prueba.agregarArista(2, 3, 30);
        grafo_prueba.agregarArista(2, 6, 24);
        grafo_prueba.agregarArista(3, 6, 19);
        grafo_prueba.agregarArista(3, 4, 23);
        grafo_prueba.agregarArista(4, 10, 11);
        grafo_prueba.agregarArista(4, 3, 23);
        grafo_prueba.agregarArista(5, 11, 27);
        grafo_prueba.agregarArista(5, 13, 17);
        grafo_prueba.agregarArista(6, 5, 14);
        grafo_prueba.agregarArista(6, 8, 43);
        grafo_prueba.agregarArista(6, 7, 70);
        grafo_prueba.agregarArista(7, 8, 45);
        grafo_prueba.agregarArista(7, 1, 26);
        grafo_prueba.agregarArista(8, 0, 12);
        grafo_prueba.agregarArista(8, 14, 45);
        grafo_prueba.agregarArista(9, 0, 34);
        grafo_prueba.agregarArista(9, 12, 7);
        grafo_prueba.agregarArista(9, 7, 12);
        grafo_prueba.agregarArista(10, 11, 23);
        grafo_prueba.agregarArista(10, 3, 43);
        grafo_prueba.agregarArista(10, 14, 23);
        grafo_prueba.agregarArista(10, 4, 12);
        grafo_prueba.agregarArista(11, 2, 29);
        grafo_prueba.agregarArista(11, 8, 47);
        grafo_prueba.agregarArista(11, 5, 27);
        grafo_prueba.agregarArista(11, 9, 20);
        grafo_prueba.agregarArista(12, 0, 32);
        grafo_prueba.agregarArista(12, 13, 28);
        grafo_prueba.agregarArista(13, 7, 38);
        grafo_prueba.agregarArista(13, 10, 33);
        grafo_prueba.agregarArista(14, 3, 50);
        grafo_prueba.agregarArista(14, 11, 20);
        grafo_prueba.agregarArista(14, 0, 37);
        
        grafo_prueba.imprimirMatrizPesos();


        Simulacion intentoSimulacion=new Simulacion(grafo_prueba, 30,hashMap);
       intentoSimulacion.iniciarSimulacion();
        

    }
    
}
