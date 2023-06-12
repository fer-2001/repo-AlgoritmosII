package examples.TicTacToe;

import engine.AdversaryEngines.MinMaxPodaAlfaBeta;
import java.util.Scanner;

public class TicTacToeSearchApp {
  public static void main(String[] args) {
    TicTacToeStateProblemAdversary spa = new TicTacToeStateProblemAdversary();
    TicTacToeStateAdversary currentState = spa.initialState();
    MinMaxPodaAlfaBeta<TicTacToeStateAdversary, TicTacToeStateProblemAdversary> engine = 
                                        new MinMaxPodaAlfaBeta<TicTacToeStateAdversary, TicTacToeStateProblemAdversary>(spa, 2);

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Welcome to Tic Tac Toe! You are playing against the computer.");
  
      while (!currentState.isSuccess()) {
        System.out.println(currentState);
        
        if (currentState.isMax()) {
          currentState = engine.computeSuccessor(currentState);
          System.out.println(engine.getProblem());
        } else {
          System.out.print("Your turn. Please enter the row and column (separated by a space) where you want to place your mark (X): ");
          int row = scanner.nextInt();
          int col = scanner.nextInt();
          currentState = currentState.placeMark(row, col, currentState);
          currentState.setMAX(!(currentState.isMax()));
        }
      }
      if (currentState.value() == -1) {
        System.out.println(currentState);
        System.out.println("\nYou won! :)");
      } else if (currentState.value() == 1) {
        System.out.println(currentState);
        System.out.println("\nthe computer wins, let's take over the world! HAHAHA");
      } else {
        System.out.println(currentState);
        System.out.println("\nEMPATE");
      }
    }
  }
}
