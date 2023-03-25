import java.util.Stack;

public class AdjMatrizGraph{
    int[][] matriz;
    Vertices[] vertice;
    int cantVertices;
    int colores;

    /**
     * Constructor de la clase
     * @param vertices cantidad de vertices de la matriz
     */
    public AdjMatrizGraph(int vertices){
        this.cantVertices =vertices;
        this.matriz = new int[vertices][vertices];
        this.colores=0;
        this.vertice= new Vertices[vertices];
        for(int i=0; i<vertices;i++){
            vertice[i] = new Vertices(false,0,i);
        }
    }

    /**
     * Metodo para agrega una conexion entre dos vertices/nodos
     * @param origen Nodo de origen
     * @param destino Nodo de destino
     *                0 representa que no hay conexion, 1 que hay conexion
     */

    public void addEdge(int origen, int destino){
        if(origen> cantVertices && destino> cantVertices)
            throw new IllegalArgumentException("Fuera de rango");
        matriz[origen][destino]=1;
        matriz[destino][origen]=1;

    }

    /**
     * Metodo para remover la conexion entre dos nodos
     * @param origen Nodo origen
     * @param destino Nodo destino
     *                0 representa que no hay conexion, 1 que hay conexion
     */
    public void removeEdge(int origen, int destino){
        if(origen> cantVertices && destino> cantVertices)
            throw new IllegalArgumentException("Fuera de rango");
        matriz[origen][destino]=0;
        matriz[destino][origen]=0;

    }

    /**
     * Metodo que realiza una busqueda en profundidad (DFS) y realiza el coloreo de los grafos
     */
    public void coloreo(){
        Stack<Vertices> s = new Stack<>();
        Vertices v = vertice[0];
        s.push(v);
        v.Visited();
        v.setColor(1);
        vertice[0] = v;
        int contador;
        while(!s.isEmpty()){
            Vertices u = s.peek();
            Vertices w = getUnvisitedAdj(u);
            if(w!=null){
                contador = w.getValor();
                pintar(w);
                w.Visited();
                s.push(w);
                vertice[contador]=w;
            }
            else{
                s.pop();
            }
        }
}


    private int coloresAdyacentes(int i){
       // Matriz para saber el color de cada vertice (Posicion indica vertice y contenido en dicha posicion su color)
        int[][] coloresUsados = new int[cantVertices][1]; // Matriz de una columna y cantVertices Filas (podria ser atributo)
        // Inicialización de la estructura
        for(int h=0;h<cantVertices;h++){
            coloresUsados[h][0]=0;
        }
        // Carga de colores de los adyacentes al nodo dado (Los nodos no adyacentes quedan en 0)
        for(int k=0; k<cantVertices;k++){
            if(isAdyacente(i,k) && vertice[k].isColored()){
                coloresUsados[k][0] = vertice[k].getColor();
                }
            }
        // Aux representa el posible color de salida del nodo (Se inicializa en 1, ya que 0 representa que no está pintado)
        int aux=1;
        for(int p=0; p<cantVertices;p++){
            if(coloresUsados[p][0] == aux){ // Si el color que estoy viendo está usado, incremento en 1
                aux++;
            }
        }
        // Revisa si ninguno de sus adyacentes comparte el mismo color, si es el caso, repite el proceso de buscar el color
        while(hayColorRepetido(i,aux)){
            for(int p=0; p<cantVertices;p++){
                if(coloresUsados[p][0] == aux){ // Si el color que estoy viendo está usado, incremento en 1
                    aux++;
                }
            }
        }

        if(aux>colores){
            colores=aux;
        }
        return aux;
    }

    private boolean hayColorRepetido(int i, int colorDelNodo){
        // Matriz para saber el color de cada vertice (Posicion indica vertice y contenido en dicha posicion su color)
        int[][] coloresUsados = new int[cantVertices][1]; // Matriz de una columna y cantVertices Filas (podria ser atributo)
        // Inicialización de la estructura
        for(int h=0;h<cantVertices;h++){
            coloresUsados[h][0]=0;
        }

        // Carga de colores de los adyacentes al nodo dado (Los nodos no adyacentes quedan en 0)
        for(int k=0; k<cantVertices;k++){
            if(isAdyacente(i,k) && vertice[k].isColored()){
                coloresUsados[k][0] = vertice[k].getColor();
            }
        }

        int aux=colorDelNodo;
        for(int p=0; p<cantVertices;p++){
            if(coloresUsados[p][0] == aux){ // Si el color que estoy viendo está usado, incremento en 1
                return true;
            }
        }
        return false;
    }


    private void pintar(Vertices x){
        if(!(x.isColored())){
            x.setColor(coloresAdyacentes(x.getValor()));
        }
    }

    /**
     * Imprime los colores de los nodos
     */
    public void mostrarColores(){
        for(int i=0; i<cantVertices;i++){
            Vertices aux = vertice[i];
            System.out.println("Color del nodo " + i + " es " + aux.getColor() + "\n");
        }
    }


    private Vertices getUnvisitedAdj(Vertices v){
        int i = v.getValor();
            for(int j=0; j<cantVertices; j++){
                if(!(vertice[j].isVisited()) && isAdyacente(i,j)){
                    return vertice[j];
                }
            }
        return null;
    }

    private boolean isAdyacente(int i, int j){
        return matriz[i][j] == 1;
    }

    /**
     * Metodo para imprimir por consola la matriz de adyacencia
     */
    public void mostrar(){
        for (int x=0; x < this.cantVertices; x++) {
            System.out.print("|");
            for (int y=0; y < this.cantVertices; y++) {
                System.out.print (this.matriz[x][y]);
                if (y!=this.cantVertices-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }
}
