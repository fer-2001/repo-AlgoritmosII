import java.util.Comparator;
import java.util.Objects;


public class PairComparator implements Comparator<Pair> {
    @Override

    public int compare(Pair p1, Pair p2) {
       if(p1.getFirst() > p2.getFirst()){
           return 1;
       }
       else if(p1.getFirst() < p2.getFirst()){
           return -1;
       }
       else if(Objects.equals(p1.getFirst(), p2.getFirst()) && p1.getSecond()>p2.getSecond()){
            return 1;
        }
       else if(Objects.equals(p1.getFirst(), p2.getFirst()) && p1.getSecond()<p2.getSecond()){
            return -1;
        }
       else if(Objects.equals(p1.getFirst(), p2.getFirst()) && Objects.equals(p1.getSecond(), p2.getSecond())){
            return 0;
        }

       return 0;
    }
}