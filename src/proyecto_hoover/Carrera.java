
package proyecto_hoover;

/**
 *
 * @author Daniel
 */
public class Carrera {
    private int id;
    private int Origen;
    private int Destino;
    private boolean enEspera;
    private boolean analizado;
    private int posicion;
    

    public Carrera(int id, int Origen, int Destino) {
        this.id = id;
        this.Origen = Origen;
        this.posicion=Origen;
        this.Destino = Destino;
        this.enEspera = true;
        this.analizado=false;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    

    public boolean isAnalizado() {
        return analizado;
    }

    public void setAnalizado(boolean analizado) {
        this.analizado = analizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrigen() {
        return Origen;
    }

    public void setOrigen(int Origen) {
        this.Origen = Origen;
    }

    public int getDestino() {
        return Destino;
    }

    public void setDestino(int Destino) {
        this.Destino = Destino;
    }

    public boolean isEnEspera() {
        return enEspera;
    }

    public void setEnEspera(boolean enEspera) {
        this.enEspera = enEspera;
    }



   
}
