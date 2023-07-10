/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_hoover;

import java.util.*;

/**
 *
 * @author Daniel
 */
public class Simulacion {

    private Grafo grafo;
    private ArrayList<Carro> carros;
    private ArrayList<Carrera> carreras;
    private ArrayList<String> reportes;
    private int tiempoSimulacion;
    private HashMap<Integer, String> hashMap = new HashMap<>();
    private HashMap<Integer, String> barrios;
    private int flagHoraPico=0;

    public Simulacion(Grafo grafo, int tiempo, HashMap<Integer, String> barrios) {
        this.grafo = grafo;
        this.carros = new ArrayList<>();
        this.carreras = new ArrayList<>();
        this.reportes = new ArrayList<>();
        this.barrios = barrios;
        this.tiempoSimulacion = tiempo;
        hashMap.put(0, "Semaforo en rojo");
        hashMap.put(1, "Accidente automovilistico");
        hashMap.put(2, "Vía despejada");
        hashMap.put(3, "Hora pico"); //PRIM Y DUPLICAR LAS ARISTAS CORRESPONDIENTES
        hashMap.put(4, "lluvias");
    }

    public void agregarCarro(Carro carro) {
        carros.add(carro);
    }

    public void agregarCarrera(Carrera carrera) {
        carreras.add(carrera);
    }

    public void iniciarSimulacion() {
        Random brandom = new Random();
        int tiempo = 0;
        int numeroCarrera = 1;
        int repeticiones = brandom.nextInt(1, grafo.getV()) / 2;
        //crear carros aleatorios inciales
        for (int i = 0; i < repeticiones; i++) {
            Carro c = new Carro(brandom.nextInt(grafo.getV()), carros.size() + 1);
            carros.add(c);
        }
        while (tiempo < tiempoSimulacion) {
            //Asignar carrearas y carros aleatorios por cada iteracion
            if (brandom.nextDouble() < 0.3) {
                int posicion = brandom.nextInt(grafo.getV());
                Carro c = new Carro(posicion, carros.size() + 1);
                carros.add(c);
            }

            if (brandom.nextDouble() < 0.45) {
                for (int i = 0; i < brandom.nextInt(grafo.getV()) / 2; i++) {
                    int origen = brandom.nextInt(grafo.getV());
                    int destino = brandom.nextInt(grafo.getV());
                    if (origen != destino) {
                        Carrera c = new Carrera(numeroCarrera, origen, destino);
                        carreras.add(c);
                        numeroCarrera++;
                    }
                }
            }
            if (!carreras.isEmpty()) {
                //asignar a cada carro disponible cada carrera disponible, por cada iteracion
                for (Carrera carrera : carreras) {
                    if (carrera.isEnEspera() && !carrera.isAnalizado()) {
                        Carro carroCercano = null;
                        int distanciaMinima = Integer.MAX_VALUE;

                        for (Carro carro : carros) {
                            if (!carro.isEstadoCarrera()) {
                                int dist = grafo.dijkstra(carro.getPosicion(), carrera.getOrigen());
                                if (dist < distanciaMinima) {
                                    distanciaMinima = dist;
                                    carroCercano = carro;
                                }
                            }
                        }
                        if (carroCercano != null) {
                            carrera.setAnalizado(true);
                            carroCercano.setCarrera(carrera);
                            carroCercano.agregarPaso(carrera.getOrigen());
                            carroCercano.acumularPedidos();
                            carroCercano.setEstadoCarrera(true);
                        }
                    }
                }
                //llevar al carro al origen o destino del pasajero, si el carro tiene una carreara
                for (Carro carro : carros) {
                    if (carro.isEstadoCarrera() && carro.isRecogiendo()) {
                        int dist = grafo.dijkstra(carro.getPosicion(), carro.getCarrera().getOrigen());
                        if (!grafo.pasos.isEmpty()) {
                            carro.setPosicion(grafo.pasos.poll());
                        }
                        if (carro.getPosicion() == carro.getCarrera().getOrigen()) {
                            carro.setRecogiendo(false);
                            carro.getCarrera().setEnEspera(false);
                        }
                    } else if (carro.isEstadoCarrera() && !carro.isRecogiendo()) {
                        if (carro.getPosicion() == carro.getCarrera().getDestino()) {
                            reportes.add(registro(carro,tiempo));
                            carro.aumentarAcumulado(carro.getValorCarrera());
                            carro.setValorCarrera(0);
                            carreras.remove(carro.getCarrera());
                            carro.setCarrera(null);
                            carro.setEstadoCarrera(false);
                            carro.setRecogiendo(true);

                        } else {
                            int dist = grafo.dijkstra(carro.getPosicion(), carro.getCarrera().getDestino());
                            if (!grafo.pasos.isEmpty()) {
                                carro.acumularCarrera(grafo.getPesos()[carro.getPosicion()][grafo.pasos.peek()]);
                                carro.agregarPaso(grafo.pasos.peek());
                                int newPos = grafo.pasos.poll();
                                carro.setPosicion(newPos);
                                carro.getCarrera().setPosicion(newPos);
                            }

                        }
                    }
                }
            }
            

            if (brandom.nextDouble() < 0.6) {
                cambioGrafo(grafo, carros, brandom.nextInt(hashMap.size()), tiempo,flagHoraPico);
            }

            tiempo++;
        }
        
        System.out.println("______________________________RESUMEN______________________________");
        resumenSimulacion(carros);
        
    }
    
    public void resumenSimulacion(ArrayList<Carro> carros){
        for (Carro carro : carros) {
            if(carro.getValorAcumulado()!=0){
            System.out.println("El carro N°"+carro.getId()+" realizo "+carro.getTotalCarreras()+" carreras y genero un total de $"+carro.getValorAcumulado()*100+" pesos");
   
        }
        }
        
    }

    public void cambioGrafo(Grafo grafo, ArrayList<Carro> carros, int idx, int tiempo,int flagHoraPico) {

        /* 
        hashMap.put(0, "Semaforo en rojo");
        hashMap.put(1, "Accidente automovilistico")
        hashMap.put(2, "Vía despejada");
        hashMap.put(3, "Hora pico"); //PRIM Y DUPLICAR LAS ARISTAS CORRESPONDIENTES
        hashMap.put(4, "lluvias");
         */

        
        Random rand = new Random();
        switch (idx) {
            case 0:
                System.out.println("En el minuto " + tiempo + " de simulacion Ocurrio:");

                int numeroAleatorio = rand.nextInt(1, carros.size());
                for (int i = 0; i < numeroAleatorio; i++) {
                    Carro c = carros.get(rand.nextInt(carros.size()));
                    for (int v : grafo.getAdj()[c.getPosicion()]) {
                        grafo.getPesos()[c.getPosicion()][v] += 2;
                    }
                }

                System.out.println(hashMap.get(idx));
                System.out.println("");

                break;
            case 1:
                System.out.println("En el minuto " + tiempo + " de simulacion Ocurrio:");
                numeroAleatorio = rand.nextInt(grafo.getV());
                for (int v : grafo.getAdj()[numeroAleatorio]) {
                    grafo.getPesos()[numeroAleatorio][v] *= 3;
                }
                System.out.println(hashMap.get(idx));
                System.out.println("");
                break;
            case 2:
                System.out.println("En el minuto " + tiempo + " de simulacion Ocurrio:");
                numeroAleatorio = rand.nextInt(grafo.getV());
                for (int v : grafo.getAdj()[numeroAleatorio]) {
                    grafo.getPesos()[numeroAleatorio][v] /= 3;
                }
                System.out.println(hashMap.get(idx));
                System.out.println("");
                break;
            case 3:
                
                if(flagHoraPico==0){
                    System.out.println("En el minuto " + tiempo + " de simulacion Ocurrio:");
                    System.out.println(hashMap.get(idx));
                System.out.println("");
                this.flagHoraPico=1;
                }
                
                break;
            case 4:
                System.out.println("En el minuto " + tiempo + " de simulacion Ocurrio:");
                for (int i = 0; i < grafo.getV(); i++) {
                    for (int j = 0; j < grafo.getV(); j++) {
                        if (i != j && grafo.getPesos()[i][j] != Integer.MAX_VALUE) {
                            grafo.getPesos()[i][j] *= 2;
                        }
                    }
                }
                System.out.println(hashMap.get(idx));

                hashMap.remove(idx);
                System.out.println("");

                break;
            default:
                System.out.println(hashMap.get(idx));
        }
    }

    public String registro(Carro carro,int tiempo) {
        System.out.println("En el minuto "+tiempo+" el carro " + carro.getId() + " termino la carrera No." + carro.getCarrera().getId() + " desde " + barrios.get(carro.getCarrera().getOrigen())
                + " hasta " + barrios.get(carro.getCarrera().getDestino())+" y tiene un valor de: $"+carro.getValorCarrera()*100 + " pesos.");

        System.out.print("Y siguio la siguiente ruta: ");
        
        for (Integer integer : carro.getRuta()) {
            System.out.print(barrios.get(integer)+"  ");
        }
        carro.setRuta(new ArrayList<>());
        System.out.print("\n");
        System.out.println("");
        return " ";
    }

}
