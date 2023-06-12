package examples.queens;
import engine.BreadthFirstEngine;


import java.util.List;

public class QueenSearchApp {

  //if ((args.length > 2) || (args.length == 0)) {
  //System.out.println("*** Usage: java JugsSearchApp <int> <int>");
  //} else {
  public static void main(String[] args) {

    int a = Integer.parseInt(String.valueOf(5));
    int b = Integer.parseInt(String.valueOf(1));

    QueenStateProblem sp1 = new QueenStateProblem(a, b);


    /*App using Breadth-first search */
    BreadthFirstEngine<QueenState, QueenStateProblem> engineBfs =
            new BreadthFirstEngine<QueenState, QueenStateProblem>(sp1);
    QueenState successBfS = engineBfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Breadth-first search ***");
    System.out.println("Solution found? " + successBfS.toString());
    if (!(successBfS == null)) {
      System.out.print("Path to goal: ");
      List<QueenState> pathBfS = engineBfs.getPath();
      for (int i = 0; i < pathBfS.size(); i++) {
        QueenState current = (QueenState) pathBfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBfs.report();

    /*App using depth-first visited search */


    //}

  }
}
