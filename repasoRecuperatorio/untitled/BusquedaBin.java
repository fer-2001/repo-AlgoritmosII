public class BusquedaBin {

  public static boolean busqueda(int[] array, int i, int j){
    if(i == j && array[i] != i) return false;
    int mid = (j-i)/2;
    if ((array[i] == i) || (array[j] ==j) || array[mid] == mid) return true;

    if(array[mid] < mid){
    return  busqueda(array,i,mid);
    }
    else {
      return busqueda(array,j, array.length-1);
    }
  }
  public static void main(String[] args){
    int[] arr = {1,2,5,3,11,22,4};
    System.out.println(busqueda(arr,0,(arr.length-1)));
  }
}

