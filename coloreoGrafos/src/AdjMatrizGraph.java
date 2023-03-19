public class AdjMatrizGraph<T> {
    int[][] matriz;
    int vertices;

    /**
     * Constructor de la clase
     * @param vertices
     */
    public AdjMatrizGraph(int vertices){
        this.vertices=vertices;
        this.matriz = new int[vertices][vertices];

    }

    public void addEdge(int origen, int destino){
        if(origen>vertices && destino>vertices)
            throw new IllegalArgumentException("Fuera de rango");
        matriz[origen][destino]=1;
        matriz[destino][origen]=1;

    }

    public void removeEdge(int origen, int destino){
        if(origen>vertices && destino>vertices)
            throw new IllegalArgumentException("Fuera de rango");
        matriz[origen][destino]=0;
        matriz[destino][origen]=0;

    }

}
