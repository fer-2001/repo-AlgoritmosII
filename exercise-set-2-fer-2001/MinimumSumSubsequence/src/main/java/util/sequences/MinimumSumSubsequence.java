package util.sequences;

import java.util.ArrayList;

/**
 * Computeds minimum sum subsequence of Integer sequences
 * 
 * @author scilingo
 */

public class MinimumSumSubsequence{
	private static final ArrayList<Tuple<Integer,Integer,Integer>> sumaSubSeq = new ArrayList<>();

	/**
	 * Computes a minimum sum subsequence by divide and conquer strategy.
	 * @param sequence is an Integer sequence.
	 * @return return a tuple containing,  value of the sum, the begin index
	 * of subsequence and the end of the subsequence
	 */
	public static Tuple <Integer,Integer,Integer> minimumSumSubsequence (Integer[] sequence){
		Tuple<Integer, Integer, Integer> salida;
		Tuple<Integer, Integer, Integer> salidaMidSeq;

		sumaSubSeq.clear();
		Tuple<Integer,Integer,Integer> vacia = new Tuple<>(0,-1,-1);
		sumaSubSeq.add(vacia);
		mergeSort(sequence,0,sequence.length-1,false);
		salida = minSumSequence(sumaSubSeq);
		salidaMidSeq=midSequence(sequence);

		if(salidaMidSeq.getFirst()<salida.getFirst()){
			return salidaMidSeq;
		}
		else{
			return salida;
		}

	}

	private static Tuple <Integer,Integer,Integer> midSequence(Integer[] sequence){

		int midIndex = sequence.length / 2;
		int indexI=midIndex;
		int indexD=midIndex;
		int z=1;
		Tuple<Integer, Integer, Integer> mitadIzq = new Tuple<>(0,-1,-1);
		Tuple<Integer, Integer, Integer> mitadDer = new Tuple<>(0,-1,-1);

		if(sequence.length == 0){
			return mitadIzq;
		}

		Integer sumaParcialIzq=sequence[midIndex];
		Integer sumaParcialDer=sequence[midIndex];

		// Se revisa desde el medio hacia la izquierda de la secuencia si hay algo menor a lo ya calculado
		for(int i=midIndex-1; i>0; i--){
			if(sequence[i] < 0){
				sumaParcialIzq= sumaParcialIzq+ sequence[i];
				mitadIzq.setFirst(sumaParcialIzq);
				mitadIzq.setSecond(i);
				mitadIzq.setThird(indexI);
				// Tambien reviso el lado derecho
				if(sequence[midIndex+z] < 0){
					sumaParcialIzq= sumaParcialIzq+ sequence[midIndex+z];
					mitadIzq.setFirst(sumaParcialIzq);
					mitadIzq.setSecond(i);
					indexI= midIndex+z;
					mitadIzq.setThird(indexI);
					z++;
				}
			}
			if(sequence[i] > 0){
				i=0;
			}

		}
		z=1;
		// Se revisa desde el medio hacia la derecha de la secuencia si hay algo menor a lo ya calculado
		for(int j=midIndex+1; j<sequence.length; j++){
			if(sequence[j] < 0){
				sumaParcialDer= sumaParcialDer + sequence[j];
				mitadDer.setFirst(sumaParcialDer);
				mitadDer.setSecond(indexD);
				mitadDer.setThird(j);
				if(sequence[midIndex-z] < 0){
					sumaParcialDer= sumaParcialDer+ sequence[midIndex-z];
					mitadDer.setFirst(sumaParcialDer);
					indexD = midIndex-z;
					mitadDer.setSecond(indexD);
					mitadDer.setThird(j);
					z++;
				}
			}
			if(sequence[j] > 0){
				break;
			}
		}


		if(mitadIzq.getFirst()<mitadDer.getFirst()){
			return mitadIzq;
		}
		else{
			return mitadDer;
		}

	}

	/**
	 * Para el problema de las subsecuencias, calcula todas las subsecuencias que se forman
	 * de partir la secuencia en dos
	 * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
	 */
	private static void mergeSort(Integer[] array, int comienzo, int fin, boolean lado) {
		// FALTA TRATAR EXCEPCIONES
		int inputLength = array.length;

		if(array.length == 0){
			return;
		}
		if(inputLength==1){
			fin=comienzo;
		}

		Tuple<Integer,Integer,Integer> aux = new Tuple<>(sumaSubSegmento(array),comienzo, fin);
		sumaSubSeq.add(aux);

		//System.out.println(Arrays.toString(array));
		//System.out.println(aux);
		int maxIndexIzq=comienzo;
		int minIndexDer;

		// Caso base para cortar la recursion (tenemos un unico nodo en este punto)
		if (inputLength == 1) {
			return;
		}

		int midIndex = inputLength / 2;
		Integer[] leftHalf =  new Integer[midIndex];
		Integer[] rightHalf = new Integer[inputLength - midIndex];

		for (int i = 0; i < midIndex; i++) {
			leftHalf[i] = array[i];
			maxIndexIzq=i;
		}

		minIndexDer= maxIndexIzq + 1;
		for (int i = midIndex; i < inputLength; i++) {
			rightHalf[i - midIndex] = array[i];

		}

		// Estoy del lado izquierdo de la subsecuencia y estoy en el caso base del lado izquierdo
		if(lado && leftHalf.length == 1 && rightHalf.length == 1){
			maxIndexIzq=comienzo+maxIndexIzq;
			minIndexDer=fin;
			lado=false; // Lado pasa a ser falso para evitar entrar en el siguiente if
		}

		// Estoy del lado izquierdo de la subsecuencia
		if(lado){
			maxIndexIzq=comienzo+maxIndexIzq;
			minIndexDer=fin;
			int aux1 = maxIndexIzq;
			maxIndexIzq= minIndexDer;
			minIndexDer=aux1;

		}

		mergeSort(leftHalf,comienzo,maxIndexIzq,false);
		mergeSort(rightHalf,minIndexDer,fin,true);
	}

	/**
    * Metodo privado para calcular la suma de una sub secuencia
    * @param array arreglo que representa una sub secuencia
    * @return la suma de la sub secuencia
    */

	private static Integer sumaSubSegmento(Integer[] array){
		Integer suma=0;
		for(int i=0;i<array.length;i++){
			suma= array[i] + suma;
		}
		return suma;
	}

	/**
	 * Dada la lista con todas las subsecuencias de una secuencia, calcula la minima subsecuencia
	 * @param array Arraylist con todas las subsecuencias
	 * @return la minima subsecuencia
	 */
	private static Tuple<Integer,Integer,Integer> minSumSequence(ArrayList<Tuple<Integer,Integer,Integer>> array){
		if(array.size() == 1){
			return array.get(0);
		}
		Tuple<Integer,Integer,Integer> salida = array.get(0);
		Tuple<Integer,Integer,Integer> aux;
		for(int i=1; i<array.size(); i++){
			aux= array.get(i);
			if(aux.getFirst() < salida.getFirst()){
				salida = aux;
			}
		}
		return salida;
	}

	// Caso de prueba para comprobar caso donde la subSecuencia menor surge a partir del medio.
	public static void main(String[] args){
		Integer[] array;
		array = new Integer[]{10,-3,-6,-4,-10,-1,5};
		System.out.println(minimumSumSubsequence(array));
	}

}