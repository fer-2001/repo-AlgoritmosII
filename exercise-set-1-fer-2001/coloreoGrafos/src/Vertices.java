public class Vertices {
    private boolean visited;
    private int color;
    private int valor;

    public Vertices(boolean visited, int color, int valor) {
        this.visited = visited;
        this.color = color;
        this.valor = valor;
    }

    public Vertices() {
        this.visited = false;
        this.color = 0;
        this.valor = 0;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int getColor() {
        return this.color;
    }

    public int getValor() {
        return this.valor;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isColored(){
        return (this.color != 0);
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void Visited(){
        this.visited=true;
    }

}

