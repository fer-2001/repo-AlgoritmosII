import java.util.ArrayList;

public class Vuelto {
ArrayList<Pair> monedas;
ArrayList<Integer> monedasvuelto;

    public Vuelto(ArrayList<Pair> monedas){
        this.monedas = monedas;
        this.monedasvuelto = new ArrayList<Integer>();
    }

    public ArrayList<Integer> darVuelto(Integer vuelto){
        Integer aux;
        if(vuelto == 0){
            return monedasvuelto;
        }
        if(vuelto < 0){
            throw new IllegalArgumentException("El vuelto a dar debe ser un valor positivo");
        }
        int i=0;
        while(i < monedas.size() && vuelto>=0){
            if((monedas.get(i)).getFirst() <= vuelto && monedas.get(i).getSecond() > 0){
                vuelto = vuelto - monedas.get(i).getFirst();
                aux = monedas.get(i).getSecond();
                monedas.get(i).setSecond(aux-1);
                monedasvuelto.add(monedas.get(i).getFirst());
            }
            else{
                i++;
            }
        }

            return monedasvuelto;
        }

        public static void main(String[] args){
            ArrayList<Pair> monedasM = new ArrayList<>();
            Pair d1 = new Pair(7,5);
    //        Pair d2 = new Pair(10,5);
            Pair d3 = new Pair(5,5);
            Pair d4 = new Pair(1,5);
            monedasM.add(d1);
      //      monedasM.add(d2);
            monedasM.add(d3);
            monedasM.add(d4);
            Vuelto aux = new Vuelto(monedasM);
            System.out.println(aux.darVuelto(3000));
        }


    }



