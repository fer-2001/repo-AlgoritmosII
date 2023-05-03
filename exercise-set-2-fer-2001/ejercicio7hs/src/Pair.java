import java.util.Objects;

public class Pair{

        private Integer first;
        private Integer second;

        /**
         * Default Constructor.
         */
        public Pair() {
            this.first = null;
            this.second = null;
        }

        /**
         * Constructor Parametrizado.
         */
        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst(){
            return first;
        }

        public void setFirst(Integer first){
            this.first = first;
        }

        public Integer getSecond(){
            return second;
        }

        public void setSecond(Integer second){
            this.second = second;
        }

        /**
         *Overriding toString() to convert Tuple objects to String.
         */
        @Override
        public String toString() {
            return ("(" + first.toString() + "," + second.toString() + ")");
        }

        /**
         * Overriding equals() to compare two Tuple objects.
         */
        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (!(other instanceof Pair)) {
                return false;
            }
            Pair actualOther = (Pair) other;
            boolean equals = actualOther.getFirst().equals(this.first);
            equals &= actualOther.getSecond().equals(this.second);
            return equals;
        }
    public static int compare(Pair p1, Pair p2) {
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

    public static int compare2(Pair p1, Pair p2) {
        if(p1.getSecond().equals(p2.getSecond())){
            return compare(p1,p2);
        }
        if (p1.getSecond() > p2.getSecond()) {
            return 1;
        }else{
            return -1;
        }

    }
}


