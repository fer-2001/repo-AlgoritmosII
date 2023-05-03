public class MinJumps {

    private static int minJumps (int[] arr, int l, int h ) {
        if (h == l)
            return 0;
        if ( arr[l] == 0)
            return Integer . MAX_VALUE ;
        int min = Integer . MAX_VALUE ;
        for (int i = l+1; i <= h && i <= l + arr[l]; i++) {
            int jumps = minJumps (arr , i, h);
            if ( jumps != Integer . MAX_VALUE && jumps + 1 < min ) // jumps + 1 < min  siempre true dsp de que sale de la llamada recursiva
                min = jumps + 1;
        }
        return min ;
    }

    public static void main(String[] args){
        int[] arr = new int[]{1,1,11,15,21,314,15,618,412,316,11,1,11,5,1,1,11,15,21,314,15,618,412,316,11,1,11,5,1,1,11,15,21,314,15,618,412,316,11,1,11,5,1,1,11,15,21,314,15,618,412,316,11,1,11,5,1,1,11,15,21,314,15,618,412,316,11,1,11,5,216};
       System.out.println(minJumps(arr, 0 ,50));
    }

}
