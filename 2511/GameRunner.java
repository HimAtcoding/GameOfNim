import java.util.Scanner;

public class GameRunner {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to the Game of Nim!");

    boolean playAgain = true;
    while (playAgain) {
      //basic player name initializations
      System.out.println("Enter Player 1's name: ");
      String player1Name = sc.nextLine();
      System.out.println("Enter Player 2's name: ");
      String player2Name = sc.nextLine();
      Player player1 = new Player(player1Name);
      Player player2 = new Player(player2Name);
      //this randomizes which player goes first, as the randomizer generates a number from 0 exclusive, and 1 inclusive
      Player currentPlayer;
      if (Math.random() < 0.5) {
        currentPlayer = player1;
        System.out.println(player1.getName() + " goes first!");
      } else {
        currentPlayer = player2;
        System.out.println(player2.getName() + " goes first!");
      }
      //this essentially creates a new pile of nims, and gets the randomized number of nims, randomized from 20-50, and the program prints out the name of the current player who's turn it is 
      Pile pile = new Pile();

      while (pile.getNumNims() > 0) {
        System.out.println("Number of Nims in the pile: " + pile.getNumNims());
        System.out.println(currentPlayer.getName() + ", it's your turn.");
        //This makes the exception for the max number of nims allowed to be taken, (at 1/2 of the pile) to give players the ability to take the last nim when it is their turn
        int maxNims;
        if (pile.getNumNims() == 1) {
          maxNims = 1; // If there's only one nim left, the player can take the last one
        } else {
          maxNims = pile.getNumNims() / 2;
        }

        int numNimsToRemove;
        do {
          System.out.print("Enter the number of nims to remove (1-" + maxNims + "): ");
          numNimsToRemove = sc.nextInt();
        } while (numNimsToRemove < 1 || numNimsToRemove > maxNims);
        //utilizes the pile class's inner method to remove the number of nims that was imported by the player 
        pile.removeNims(numNimsToRemove);
        currentPlayer.increaseScore();
        // if the number of nims left in the pile is zero, the program looks for the last player to take the last remaining nim, and award the opposing player as the winner, and label the player who took the last nim as the loser
        if (pile.getNumNims() == 0) {
          System.out.println(currentPlayer.getName() + " took the last nim and lost!");
          if (currentPlayer == player1) {
            player2.increaseScore();
            System.out.println(player2.getName() + " wins!");
          } else {
            player1.increaseScore();
            System.out.println(player1.getName() + " wins!");
          }
          break;
        }
        // this essentially checks to switch turns between either player
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
      }
        // final display of scores
      System.out.println("Scores:");
      System.out.println(player1.getName() + ": " + player1.getScore());
      System.out.println(player2.getName() + ": " + player2.getScore());
         // play again feature  
      System.out.print("Do you want to play again? (yes/no): ");
      String playChoice = sc.next();
      if (!playChoice.equalsIgnoreCase("yes")) {
        playAgain = false;
      } else {
        // Reset the pile and scores for a new game
        pile = new Pile();
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
      }
    }
  }
}