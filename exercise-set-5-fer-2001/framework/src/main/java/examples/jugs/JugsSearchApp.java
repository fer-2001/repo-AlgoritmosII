package examples.jugs;

import engine.BestFirstSearchEngine;
import engine.BreadthFirstEngine;
import engine.DepthFirtsEngine;
import engine.IterativeDeepeningEngine;

import java.util.List;

public class JugsSearchApp {
  /**
   * Main for Jugs App.
   * @param args contents Jug A and contents Jug B are expected.
   */
  
  public static void main(String[] args) {
    
    //if ((args.length > 2) || (args.length == 0)) {
      //System.out.println("*** Usage: java JugsSearchApp <int> <int>");
    //} else {
      
      int a = Integer.parseInt(String.valueOf(1));
      int b = Integer.parseInt(String.valueOf(0));
      
      JugsStateProblem sp1 = new JugsStateProblem(a,b);
      
           
      /*App using Breadth-first search */
    BestFirstSearchEngine<JugsState,JugsStateProblem> engineBfs =
          new BestFirstSearchEngine<JugsState,JugsStateProblem>(sp1);
      JugsState successBfS = engineBfs.performSearch();
      System.out.println();     
      System.out.println("*** Result using Best-first search ***");
      System.out.println("Solution found? " + successBfS.toString());
      if (! (successBfS == null)) {
        System.out.print("Path to goal: ");
        List<JugsState> pathBfS = engineBfs.getPath();
        for (int i = 0; i < pathBfS.size(); i++) {
          JugsState current = (JugsState) pathBfS.get(i);
          System.out.print(current.toString());
        }
        System.out.println();
      }
      engineBfs.report();
      
      /*App using depth-first visited search */ 
          

    //}
    
  } 
    
}
/*
* *** Result using Breadth-first search ***
Solution found? (4, 2)
Path to goal: (0, 0)(0, 3)(3, 0)(3, 3)(4, 2)
Length of path to state when search finished: 5
* */