/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_hoover;

import java.util.*;

public class Grafo {

    private LinkedList<Integer>[] adj;
    private int V;
    private int A;
    private int[][] pesos;
    public Queue<Integer> pasos;
    
    
    public Grafo(int nodos) {
        this.V = nodos;
        this.A = 0;
        this.adj = new LinkedList[nodos];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
        this.pesos = new int[nodos][nodos];
        for (int i = 0; i < nodos; i++) {
            for (int j = 0; j < nodos; j++) {
                if (i == j) {
                    pesos[i][j] = 0;
                } else {
                    pesos[i][j] = 0;
                }
            }
        }
    }

    public int getV() {
        return V;
    }

    public void setV(int V) {
        this.V = V;
    }

    public int getA() {
        return A;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Integer>[] adj) {
        this.adj = adj;
    }

    public int[][] getPesos() {
        return pesos;
    }

    public void setPesos(int[][] pesos) {
        this.pesos = pesos;
    }

    public Queue<Integer> getPasos() {
        return pasos;
    }

    public void setPasos(Queue<Integer> pasos) {
        this.pasos = pasos;
    }


    
    
    public void agregarArista(int u, int v, int peso) {
        adj[u].add(v);
        pesos[u][v] = peso;
        A++;
    }

    public void eliminarArista(int u, int v) {
        adj[u].removeFirstOccurrence(v);
        A--;
    }

    public void imprimirGrafo() {
        for (int v = 0; v < V; v++) {
            System.out.print("Nodo " + v + ": ");
            for (int w = 0; w < adj[v].size(); w++) {
                System.out.print(adj[v].get(w) + " ");
            }
            System.out.println();
        }
    }

    public void imprimirMatriz(int[][] matriz) {
        int longitudMaxima = 0;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                int longitud = String.valueOf(matriz[i][j]).length();
                if (longitud > longitudMaxima) {
                    longitudMaxima = longitud;
                }
            }
        }
        for (int v = 0; v < V; v++) {
            //System.out.print("Fila " + v + ": ");
            for (int w = 0; w < V; w++) {
                System.out.printf("%-" + (longitudMaxima + 1) + "d", matriz[v][w]);
            }
            System.out.println();
        }
    }

    public void imprimirMatrizPesos() {
        int longitudMaxima = 0;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                int longitud = String.valueOf(pesos[i][j]).length();
                if (longitud > longitudMaxima) {
                    longitudMaxima = longitud;
                }
            }
        }
        for (int v = 0; v < V; v++) {
           // System.out.print("Fila " + v + ": ");
            for (int w = 0; w < V; w++) {
                System.out.printf("%-" + (longitudMaxima + 1) + "d", pesos[v][w]);System.out.print(",");
            }
            System.out.println();
        }
    }

    public int dijkstra(int origen, int destino){
        int[] distancia = new int[V];
        boolean[] visited = new boolean[V];
        int[] camino = new int[V];
        distancia[origen] = 0;
        visited[origen] = true;
        pasos = new LinkedList<>();
        int acumulado = 0;
        for (int i = 0; i < V; i++) {
            if (i != origen) {
                distancia[i] = Integer.MAX_VALUE;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(origen);
        while (!q.isEmpty() || !visited[destino]) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (!visited[v] && pesos[u][v] + acumulado < distancia[v]) {
                    distancia[v] = pesos[u][v] + acumulado;
                    camino[v] = u;
                }
            }
            int menor = Integer.MAX_VALUE;
            int posMenor = -1;
            for (int i = 0; i < V; i++) {
                if (!visited[i] && distancia[i] != Integer.MAX_VALUE && distancia[i] < menor && distancia[i] != 0) {
                    menor = distancia[i];
                    posMenor = i;
                }
            }
            if (posMenor == origen || posMenor == -1) {
                break;
            } else {
                acumulado = menor;
                visited[posMenor] = true;
                q.offer(posMenor);
            }
        }
       // System.out.println("El camino más corto entre el nodo " + origen + " y el nodo " + destino + " es: ");
        ArrayList<Integer> recorrido = new ArrayList<>();
        int paso = destino;
        while (paso != origen) {
            recorrido.add(paso);
            paso = camino[paso];
        }
        
        Collections.reverse(recorrido);
        for (int nodo : recorrido) {
         //   System.out.print(nodo + " ");
            pasos.add(nodo);
        }
        //System.out.print("y pesa: " + distancia[destino] + "\n");
        return distancia[destino];
    }

    public void prim(int origen) {
        Grafo mst = new Grafo(V);
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> aristas = new ArrayList<>();
        Stack<Integer> nodos = new Stack<>();
        visited[origen] = true;
        q.offer(origen);
        int menor = Integer.MAX_VALUE;
        int siguiente = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (!visited[v]) {
                    aristas.add(v);
                }
            }
            if (!aristas.isEmpty()) {
                for (int v : aristas) {
                    if (pesos[u][v] < menor) {
                        menor = pesos[u][v];
                        siguiente = v;
                    }
                }
                visited[siguiente] = true;
                mst.agregarArista(u, siguiente, menor);
                nodos.push(u);
                q.offer(siguiente);
                menor = Integer.MAX_VALUE;
                aristas = new ArrayList<>();

            } else {
                if (!nodos.isEmpty()) {
                    q.offer(nodos.pop());
                }
            }
        }
        System.out.println("ARBOL DE EXPLANSION MINIMA POR EL ALGORITMO PRIM: ");
        mst.imprimirGrafo();
    }
    
    public void floydWarshall(int origen, int destino) {
    int[][] matrizPesos = new int[V][V];
    int[][] recorrido = new int[V][V];
    int pivote = 0;
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            if (i == j) {
                recorrido[i][j] = -1;
            } else {
                matrizPesos[i][j] = (pesos[i][j] != 0) ? pesos[i][j] : Integer.MAX_VALUE;
                recorrido[i][j] = (matrizPesos[i][j] != Integer.MAX_VALUE) ? j : -1;
            }
        }
    }
    while (pivote < V) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (matrizPesos[i][pivote] != Integer.MAX_VALUE && matrizPesos[pivote][j] != Integer.MAX_VALUE && matrizPesos[i][j] > matrizPesos[i][pivote] + matrizPesos[pivote][j]) {
                    matrizPesos[i][j] = matrizPesos[i][pivote] + matrizPesos[pivote][j];
                    recorrido[i][j] = pivote;
                }
            }
        }
        pivote++;
    }
    System.out.println("MATRIZ DE ADYACENCIA (PESOS): \n");
    imprimirMatriz(matrizPesos);

    System.out.println("MATRIZ DE ADYACENCIA (RECORRIDO): \n");
    imprimirMatriz(recorrido);

    System.out.print("CAMINO MÁS CORTO ENTRE " + origen + " Y " + destino + ": ");
    int paso = recorrido[origen][destino];
    int arranque = origen;
    while (arranque != destino) {
        System.out.print(arranque);
        arranque = paso;
        paso = recorrido[paso][destino];
        System.out.print("-");
    }
    System.out.print(destino);
    System.out.print(" y cuesta: " + matrizPesos[origen][destino] + "\n");
}

}
    

