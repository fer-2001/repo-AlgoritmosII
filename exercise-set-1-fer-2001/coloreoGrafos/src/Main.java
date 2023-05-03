import java.util.Arrays;
import java.util.*;

/**
 * Clase ejecutable para demostrar el uso de {@code graph}
 */
public class Main {

    public static void main(String[] args) {
        int k = 10000;
        AdjMatrizGraph graph = new AdjMatrizGraph(8);
        graph.addEdge(0,1);
        graph.addEdge(1,0);
        graph.addEdge(0,2);
        graph.addEdge(2,0);
        graph.addEdge(0,3);
        graph.addEdge(3,0);
        graph.addEdge(0,4);
        graph.addEdge(4,0);
        graph.addEdge(1,3);
        graph.addEdge(3,1);
        graph.addEdge(1,2);
        graph.addEdge(2,1);
        graph.addEdge(1,7);
        graph.addEdge(7,1);
        graph.addEdge(4,6);
        graph.addEdge(6,4);
        graph.addEdge(4,5);
        graph.addEdge(5,4);
        graph.addEdge(6,5);
        graph.addEdge(5,6);
        graph.addEdge(7,6);
        graph.addEdge(6,7);
        graph.addEdge(7,5);
        graph.addEdge(5,7);



        /*
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(3, 1);
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 2);
        graph.addEdge(4, 0);
        graph.addEdge(0, 4);
        graph.addEdge(0, 2);
        graph.addEdge(2, 0);
*/
        graph.coloreo();

        graph.mostrar();
        graph.mostrarColores();
    }


}