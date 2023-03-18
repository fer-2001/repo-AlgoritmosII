package util.math;

/**
 * Computeds greatest common divisor of two nonnegative, not-both-zero
 * integers using diferents algorithms.
 * 
 * @author scilingo
 */

public class GreatestCommonDivisor {

	/**
	* Computes greatest common divisor by Euclid's algorithm
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int euclidAlgorithm(int m, int n){
		if (m < 0 || n < 0 || (m == 0 && n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		if(n == 0)
			return m;
		return euclidAlgorithm(n,m%n);
	}

	/**
	* Computes greatest common divisor by definition based algorithm
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int definitionBasedAlgorithm(int m, int n){
		//throw new UnsupportedOperationException("method not yet implemented");
		if (m < 0 || n < 0 || (m == 0 || n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		int t = Math.min(m,n);
		while(t > 0){
			if(m%t == 0){
				if(n%t == 0){
					return t;
				}else{
					t=t-1;
				}
			}
			else{
				t=t-1;
			}
		}
		return t;
	}

	/**
	* Computes greatest common divisor by middle school procedure
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int middleSchoolAlgorithm(int m, int n){
		//throw new UnsupportedOperationException("method not yet implemented");
		if (m < 0 || n < 0 || (m == 0 && n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		int[] factoresPrimosM = factoresPrimo(m);
		int[] factoresPrimosN = factoresPrimo(n);

		int max = Math.min(factoresPrimosM.length,factoresPrimosN.length);
		int[] salida = new int[max];
		int z=0;
		for(int i=0; i<factoresPrimosM.length; i++){
			for(int j=z; j<factoresPrimosN.length; j++){
				if((factoresPrimosM[i] !=0) && (factoresPrimosN[j] !=0) && factoresPrimosM[i] == factoresPrimosN[j]){
					salida[z] = factoresPrimosN[j];
					z++;
				}
			}
		}
		int i=0;
		int resultado=1;
		while(i<salida.length){
			if(salida[i] != 0){
				resultado = resultado * salida[i];
				i++;
			}
			else{
				i++;
			}
		}
	return resultado;
	}

	/**
	 * Metodo auxiliar privado para factorizar un numero segun sus factores primos
	 * @param n numero a factorizar
	 * @return un arreglo con la secuencia de numeros que multiplicados, devuelven n
	 */
	private static int[] factoresPrimo(int n){
		int[] factoresPrimos= sieve(n);
		int[] factorizacionN = new int[factoresPrimos.length];
		int aux=n;
		int i=0;
		int j=0;
		while(aux > 1 && i<factoresPrimos.length){
			if ((factoresPrimos[i] != 0) && ((aux%factoresPrimos[i]) == 0)) {
				aux=aux/factoresPrimos[i];
				factorizacionN[j] = factoresPrimos[i];
				j++;
			}
			else {
				i++;
			}
		}
		return factorizacionN;
	}

	/**
	* Implements the sieve of Eratosthenes
	* @param n is a number greater than 1
	* @return Array of all prime numbers less than or equal to n.
	*/
	private static int[] sieve(int n){
		//throw new UnsupportedOperationException("method not yet implemented");
		int aux = (int) Math.floor((Math.sqrt(n)));
		int j;
		int[] array = new int[n];
		int[] output = new int[n];
		for(int p = 2; p<n; p++){
			array[p]=p;
		}
		for(int p=2; p<aux; p++){
			if(array[p] != 0){
				j = p*p;
				while(j<n){
					array[j]=0;
					j=j+p;
				}
			}
		}
		int i=0;

		for(int p=2; p<n; p++){
			if(array[p] != 0){
				output[i]=array[p];
				i++;
			}
		}
		return output;
	}


}