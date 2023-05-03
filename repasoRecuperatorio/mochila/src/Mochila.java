import java.util.ArrayList;

public class Mochila {
  ArrayList<Objeto> lista;
  public static class Objeto implements Comparable<Objeto>{
    private int peso;
    private int valor;

    /**
     * Constructor de la clase
     * @param peso valor del peso en kg que tiene un objeto
     * @param valor valor numerico que tiene un determinado objeto
     */
    public Objeto(int peso, int valor){
      this.peso = peso;
      this.valor = valor;
    }

    /**
     * Getter valor
     * @return valor
     */
    public int getValor(){
      return valor;
    }

    /**
     * Getter peso
     * @return peso
     */
    public int getPeso(){
      return peso;
    }

    /**
     * Setter valor
     * @param valor valor del objeto
     */
    public void setValor(int valor){
      this.valor = valor;
    }

    /**
     * Setter peso
     * @param peso peso del objeto
     */
    public void setPeso(int peso){
      this.peso = peso;
    }

    @Override
    public int compareTo(Objeto o) {
      if (this.getValor() > o.getValor()) {
        return 1;
      }else if (this.getValor() < o.getValor())
      {
        return  -1 ;
      } else { return 0 ; }
    }

    @Override
    public String toString() {
      return "Peso :" + this.getPeso() + " y " + "Valor: " + this.getValor() + "\n";
    }

  }

  public Mochila(ArrayList<Objeto> array){
    lista = array;
  }
  public static int knapSack(int W, ArrayList<Objeto> lista, int n) {
    // Base Case
    if (n == 0 || W == 0)
      return 0;

    // If weight of the nth item is
    // more than Knapsack capacity W,
    // then this item cannot be included
    // in the optimal solution
    if(lista.get(n-1).getPeso()>W){
      return knapSack(W,lista,n-1);
    }

      // Return the maximum of two cases:
      // (1) nth item included
      // (2) not included
    else {
      //System.out.println(lista.get(n-1).getValor());
      int mi = (lista.get(n-1).getValor() + knapSack((W-lista.get(n-1).getPeso()),lista,n-1));
      int md= knapSack(W, lista, n - 1);
      return (Math.max(mi, md));
    }
  }

  public static void main(String args[]) {
    ArrayList<Objeto> array = new ArrayList<>();
    array.add(new Objeto(2, 10));
    array.add(new Objeto(8, 40));
    array.add(new Objeto(1, 5));
    array.add(new Objeto(3, 15));
    array.add(new Objeto(4, 20));
    array.add(new Objeto(10, 50));
    array.add(new Objeto(5, 25));
    array.add(new Objeto(7, 35));
    array.add(new Objeto(9, 45));
    array.add(new Objeto(6, 30));
    array.add(new Objeto(5, 21));
    array.add(new Objeto(7, 22));
    array.add(new Objeto(8, 23));
    array.add(new Objeto(6, 25));
    array.add(new Objeto(2, 40));
    array.add(new Objeto(3, 70));
    array.add(new Objeto(8, 80));
    array.add(new Objeto(7, 45));
    array.add(new Objeto(6, 10));
    array.add(new Objeto(1, 31));
    array.add(new Objeto(2, 32));
    array.add(new Objeto(3, 62));
    array.add(new Objeto(1, 26));
    int W = 50;
    int n = array.size();
    System.out.println(knapSack(W,array, n));
  }


}
