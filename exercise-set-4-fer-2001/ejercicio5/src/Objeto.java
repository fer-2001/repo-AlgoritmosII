public class Objeto implements Comparable<Objeto>{
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
