package util.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements several algorithms to compute Matrix Chain
 * Multiplication problem. Wich consists to find the right parenthesize
 * of a matrices chain. In order to compute the product of a matrices
 * chain with the minimum number of multiplication operations.
 */
public class MatrixChainMultiplication
{
    private Integer[] chain; // contains the matrices dimensions.

    private int [][] p; // p will store index of parenthesized split point
						// p almacenará el índice del punto de división entre paréntesis

	private static Integer [][] dp; // p will store index of parenthesized split point

	/*
    * Matrix A[i] has dimension chain[i-1] x chain[i] for i = 1..n
    * @param chain contains the matrices dimensions.
    */
	public MatrixChainMultiplication(Integer[] chain){
		this.chain = chain;
		this.p = new int [chain.length][chain.length];
		this.dp = new Integer[chain.length][chain.length];
		for (Integer[] row : dp)
			Arrays.fill(row, -1);
	}

	/* Method to build parenthesized solution */
	public String getParenthesized(){

		return buildParenthesized(1,chain.length-1);
	}

	private String buildParenthesized(int i, int j){
		throw new UnsupportedOperationException("method not yet implemented");
	}

	/*
	* This method compute the best matrix chain multiplication
	* by diferent techniques.
	* @param chain contains matrices dimensions
	*/  
	public int matrixChainMultiplication(Integer[] chain){
		throw new UnsupportedOperationException("method not yet implemented");
	}

	/*
	* This method compute the best matrix chain multiplication
	* by Divide & Conquer technique.
	* @param chain contains matrices dimensions
	*/  
	public static int divideAndConquerMCM(Integer[] chain, int i, int j){
		if (i == j)
			return 0;

		int min = Integer.MAX_VALUE;

		// Place parenthesis at different places
		// between first and last matrix,
		// recursively calculate count of multiplications
		// for each parenthesis placement
		// and return the minimum count
		for (int k = i; k < j; k++) {
			int count = divideAndConquerMCM(chain, i, k)
					+ divideAndConquerMCM(chain, k + 1, j)
					+ chain[i - 1] * chain[k] * chain[j];

			if (count < min)
				min = count;
		}

		// Return minimum count
		return min;
	}

	/*
	* This method compute the best matrix chain multiplication
	* by Dynamic technique.
	* @param chain contains matrices dimensions
	*/  
	public int dynamicMCM(Integer[] chain, int i, int j){
		if (i == j)
		{
			return 0;
		}
		if (dp[i][j] != -1)
		{
			return dp[i][j];
		}
		dp[i][j] = Integer.MAX_VALUE;
		for (int k = i; k < j; k++)
		{
			dp[i][j] = Math.min(
					dp[i][j], dynamicMCM(chain, i, k)
							+ dynamicMCM(chain, k + 1, j)
							+ chain[i - 1] * chain[k] * chain[j]);
		}
		return dp[i][j];	}

	// Driver code
	public static void main(String args[])
	{
		Integer arr[] = new Integer[] { 13, 5, 89, 3, 34 };
		MatrixChainMultiplication aux = new MatrixChainMultiplication(arr);
		int N = arr.length;

		// Function call
		System.out.println(
				"Minimum number of multiplications is "
						+ aux.dynamicMCM(arr, 1, N - 1));
	}


}
