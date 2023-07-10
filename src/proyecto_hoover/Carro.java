package proyecto_hoover;

import java.util.*;

/**
 *
 * @author Daniel
 */

public class Carro {

    private boolean estadoCarrera;
    private boolean recogiendo;
    private int posicion;
    private int pasajero;
    private int id;
    private Carrera carrera;
    private int valorAcumulado = 0;
    private int ValorCarrera = 0;
    private int totalCarreras = 0;
    private ArrayList<Integer> ruta; 

    public Carro(int posicion, int id) {
        this.posicion = posicion;
        this.ruta=new ArrayList<Integer>();
        this.id = id;
        this.pasajero = -1;
        this.estadoCarrera = false;
        this.carrera = null;
        this.recogiendo = true;
    }

    public ArrayList<Integer> getRuta() {
        return ruta;
    }

    public void setRuta(ArrayList<Integer> ruta) {
        this.ruta = ruta;
    }
    
    public void agregarPaso(int paso){
        ruta.add(paso);
    }

    public int getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(int valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public int getValorCarrera() {
        return ValorCarrera;
    }

    public void setValorCarrera(int ValorCarrera) {
        this.ValorCarrera = ValorCarrera;
    }

    public boolean isRecogiendo() {
        return recogiendo;
    }

    public void setRecogiendo(boolean Recogiendo) {
        this.recogiendo = Recogiendo;
    }

    public boolean isEstadoCarrera() {
        return estadoCarrera;
    }

    public void setEstadoCarrera(boolean estadoCarrera) {
        this.estadoCarrera = estadoCarrera;
    }

    public int getTotalCarreras() {
        return totalCarreras;
    }

    public void setTotalCarreras(int totalCarreras) {
        this.totalCarreras = totalCarreras;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPasajero() {
        return pasajero;
    }

    public void setPasajero(int pasajero) {
        this.pasajero = pasajero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void acumularCarrera(int acumulado) {

        ValorCarrera = ValorCarrera + acumulado;
    }

    public void aumentarAcumulado(int acumulado) {

        valorAcumulado = valorAcumulado + acumulado;
    }

    public void acumularPedidos() {
        totalCarreras++;
    }

}
