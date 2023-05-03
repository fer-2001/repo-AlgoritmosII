import java.util.HashMap;

public class Prueba {
    HashMap<Integer, Integer> cache;

    public Prueba() {
        this.cache = new HashMap<>();
    }

    public Integer f(Integer n) {
        if (n == 0) return 0;
        if (!(cache.containsKey(n))){
            cache.put(n, (f(n - 1) + g(n - 1)));
        }
        return cache.get(n);
    }

    public Integer g(Integer n) {
        if (n==0) return 1;
        return f(n-1);
    }

    public static void main(String[] args){
        Prueba n = new Prueba();
        System.out.println(n.f(40));
    }

}