import java.util.ArrayList;

 class Item<V,W> {

	private int value;
	private int weight;
  
	/* constructor */ 
	public Item(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	public int getValue(){
		return value;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getWeight(){
		return weight;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}
}

public class Knapsack {

  /**
   * Knapsack problem using dynamic programming technique
   * @param items available items to stole . each one with his current value and weight
   * @param backpack_w is the weight of the backpack
   * @return max and optimal value of items to stole
   */
  public static int knapDyn(ArrayList<Item> items, int backpack_w){

    if (backpack_w == 0 || items.size()==0) // el peso de la mochila es 0 o no existen items para robar
      return 0;

    int[][] insideitems = new int[items.size()+1][backpack_w+1];  // se considera la fila 0 y col 0

    for (int i = 0; i <= items.size(); i++){   // filas (items)

      for (int j = 0; j <= backpack_w; j++){ //  columnas  (peso mochila)

        if (i == 0 || j == 0)       // se llenan la fila 0 y la columna 0 con 0s
          insideitems[i][j] = 0;
    
        else{
          if (items.get(i-1).getWeight() <= j ){        // el valor del item es <= al peso de la mochila actual
            
            int m1 = items.get(i-1).getValue() + insideitems[i-1][j - items.get(i-1).getWeight()];     // valor del current item mas el valor del item de arriba fila ...
            int val_max_item = insideitems[i-1][j];          //  el valor de arriba hasta el momento es el max valor para ese peso
            insideitems[i][j] = Math.max(m1, val_max_item); // el max entre ambos sera el valor a colocar en la matriz

          }else {
            insideitems[i][j] = insideitems[i-1][j];  //pongo el de arriba que hasta el momento era el max valor para ese peso
          }
        }
      }
    }
    return insideitems[items.size()][backpack_w]; // en esa pos de la matriz se encontrara el resultado optimo
  }

  public static void main(String args[]){
  
    Item i1 = new Item(12,2);
    Item i2 = new Item(10,1);
    Item i3 = new Item(20,3);
    Item i4 = new Item(15,2);

    ArrayList<Item> items = new ArrayList<Item>();
    items.add(i1);
    items.add(i2);
    items.add(i3);
    items.add(i4);

    int res = Knapsack.knapDyn(items, 5);
    System.out.println(res);

  }

} 




