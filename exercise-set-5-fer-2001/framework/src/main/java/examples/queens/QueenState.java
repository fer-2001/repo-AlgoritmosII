package examples.queens;

import conventionalsearch.State;
import examples.jugs.JugsState;

import java.util.Arrays;

public class QueenState implements State {
  private int[][] matriz;
  private int i;
  private int j;
  private int cantReinas;
  private QueenState parent;

  public boolean repOK() {
    return (i >= 0 && i <= 7) && (j >= 0 && j <= 7);
  }

  // Constructor
  public QueenState(int i, int j, int cantReinas) {
    this.i = i;
    this.j = j;
    this.cantReinas = cantReinas;
    this.matriz = new int[8][8];
    for (int k = 0; k < 8; k++) {
      for (int l = 0; l < 8; l++) {
        matriz[k][l] = (2);
      }
    }
    if (!repOK()) {
      throw new IllegalArgumentException("invalid values");
    }
  }

  public QueenState() {
    this.i = 0;
    this.j = 0;
    this.cantReinas = 0;
    this.matriz = new int[8][8];
    for (int k = 0; k < 8; k++) {
      for (int l = 0; l < 8; l++) {
        matriz[k][l] = (2);
      }
    }
  }

  // Getters y Setters de atributos
  public int getContentOfI() {
    return i;
  }

  public int getContentOfJ() {
    return j;
  }


  public void setContentOfI(int i) {
    this.i = i;
  }

  public void setContentOfJ(int j) {
    this.j = j;
  }

  public int[][] getMatriz() {
    return matriz;
  }

  public void setMatriz(int[][] matriz) {
    this.matriz = matriz;
  }

  public int getCantReinas() {
    return cantReinas;
  }

  public void setCantReinas(int cantReinas) {
    this.cantReinas = cantReinas;
  }

  // Metodos de la interface State

  @Override
  public QueenState getParent() {
    return parent;
  }
  public void setParent(QueenState parent) {
    this.parent = parent;
  }
  @Override
  public boolean isSuccess() {
    return cantReinas == 8;
  }

  // Metodos comunes

  @Override
  public String toString() {
    return "(" + this.i + ", " + this.j + ")";

  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof QueenState)) {
      return false;
    }

    QueenState otherOne = (QueenState) other;

    return otherOne.getContentOfI() == this.getContentOfI() && otherOne.getContentOfJ() == this.getContentOfJ();

  }

  public void pintar(int i, int j) {
    if(matriz[i][j] == 2){
      matriz[i][j] = 0;
    }
    else{
      throw new UnsupportedOperationException("Estas pintando mal, seras castigada");
    }
    // A la abajo de la reina
    for (int l = i+1; l < 8; l++) {
      matriz[l][j] = 1;
    }
    // A la derecha de la reina
    for (int k = j+1; k < 8; k++) {
      matriz[i][k] = 1;
    }
    // A la arriba de la reina
    for (int l = i-1; l >= 0; l--) {
      matriz[l][j] = 1;
    }
    // A la derecha de la reina
    for (int k = j-1; k >= 0; k--) {
      matriz[i][k] = 1;
    }
    // Diagonal arriba izq de la reina
    int k = i;
    int l = j;
    while(k>0 && l>0){
      k--;
      l--;
      matriz[k][l] = 1;
    }
    // Diagonal arriba der de la reina
    k = i;
    l = j;
    while(k>0 && l<7){
      k--;
      l++;
      matriz[k][l] = 1;
    }
    // Diagonal abajo der de la reina
    k = i;
    l = j;
    while(k<7 && l<7){
      k++;
      l++;
      matriz[k][l] = 1;
    }
    // Diagonal arriba izq de la reina
    k = i;
    l = j;
    while(k<7 && l>0){
      k++;
      l--;
      matriz[k][l] = 1;
    }
  }
}
