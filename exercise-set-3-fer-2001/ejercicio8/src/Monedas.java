import java.util.HashMap;

public class Monedas {

    HashMap<Integer,Integer> cache;
    Integer vuelto;
    Integer[] denominacionMonedas;

    public Monedas(Integer vuelto, Integer[] denominacionMonedas){
        this.vuelto = vuelto;
        this.denominacionMonedas = denominacionMonedas;
        this.cache = new HashMap<>();
    }


    public int cantmonedas(Integer vuelto){
        if(vuelto==0){
            return 0;
        }
        for(int i=vuelto; i>-1; i--){
            Integer temp = Integer.MAX_VALUE;
            int j=1;
            while(j <= denominacionMonedas.length && i>denominacionMonedas[i]){
                temp = Math.min(cantmonedas(i-denominacionMonedas[i]),temp);
                j++;

            }


        }

   }


}
