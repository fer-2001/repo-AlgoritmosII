// A Dynamic Programming based solution
// for 0-1 Knapsack problem

import java.io.*;

class KnapsackDyn {

  // A utility function that returns
  // maximum of two integers
  static int max(int a, int b) { return (a > b) ? a : b; }

  // Returns the maximum value that can
  // be put in a knapsack of capacity W
  static int knapSack(int W, int wt[], int val[], int n)
  {
    int i, w;
    int K[][] = new int[n + 1][W + 1];

    // Build table K[][] in bottom up manner
    for (i = 0; i <= n; i++) {
      for (w = 0; w <= W; w++) {
        if (i == 0 || w == 0)
          K[i][w] = 0;
        else if (wt[i - 1] <= w)
          K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
        else
          K[i][w] = K[i - 1][w];
      }
    }

    return K[n][W];
  }

  // Driver code
  public static void main(String args[])
  { int profit[] = new int[] {30,10,40,5,35,15,25,20,45,5,50,40,20,25,35,45,5,10,40,30,15,21,22,23,25,40,70,80,45,10,31,32,62,26};
    int weight[] = new int[] {6,2,8,1,7,3,5,4,9,1,10,8,4,5,7,9,1,2,8,6,3,5,7,8,6,2,3,8,7,6,1,2,3,1};
    int W = 50;
    int n = profit.length;
    System.out.println(knapSack(W, weight, profit, n));
  }
}
/*This code is contributed by Rajat Mishra */
