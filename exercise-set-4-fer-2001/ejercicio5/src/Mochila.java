import java.util.ArrayList;

public class Mochila {

    private ArrayList<Objeto> objetos;
    private int pesoMax;

    /**
     * Constructo de la clase
     * @param array lista de objetos ordenados segun su valor
     * @param pesoMax peso maximo que puede llevar la mochila
     */
    public Mochila(ArrayList<Objeto> array, int pesoMax){
        this.pesoMax=pesoMax;
        this.objetos = array;
    }

    /**
     * Pre-Condicion: La lista de objetos debe estar ordenada según su valor
     * @return Una lista de objetos que tiene el maximo beneficio posible de
     * meter n objetos en una mochila - Usando Greedy
     */
    public ArrayList<Objeto> maxBeneficio(){
        if (objetos.size() == 0) return objetos;
        if (pesoMax<0) {
            throw new IllegalArgumentException("Peso debe ser positivo");
        }
        ArrayList<Objeto> mochila = new ArrayList<>();
        int n = objetos.size() -1;
        int i = 0;
        int pesoAux = 0;
        Objeto aux;
        while(pesoAux <= pesoMax && mochila.size() != objetos.size() && i<n){
            aux = objetos.get(n-i);
            if (pesoAux + aux.getPeso()<=pesoMax){
               mochila.add(aux);
                pesoAux += aux.getPeso();
            }
            i++;
        }
        return mochila;
    }

    public static void main(String[] args){
        Objeto ob1 = new Objeto(2,20);
        Objeto ob2 = new Objeto(5,30);
        Objeto ob3 = new Objeto(10,50);
        Objeto ob4 = new Objeto(5,10);
        ArrayList<Objeto> array = new ArrayList<>();
        array.add(ob1);
        array.add(ob2);
        array.add(ob3);
        array.add(ob4);
        array.sort(Objeto::compareTo);
        Mochila aux = new Mochila(array,15);
        System.out.println(aux.maxBeneficio());
    }

}