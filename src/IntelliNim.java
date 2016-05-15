import java.util.Scanner;

/**
 * @author Clay Benson (cmb3602@rit.edu)
 *
 * Main class of the Nim Game. Handles taking input from users and determining who wins the game.
 */
public class IntelliNim {

    public enum Difficulty {
        EASY, MEDIUM, HARD;
    }

    /**
     * Main method.
     * @param args Command line arguments. Currently none are used.
     */
    public static void main(String[] args) {

        Difficulty gameDifficulty = Difficulty.HARD;
        String myArg;

        for (int i = 0; i < args.length; ++i) {
            try {
                myArg = args[i].toLowerCase();
                if ((myArg.equals("-difficulty")) | ((myArg.equals("-d")))) {
                    if (args[i+1].equals("easy")) {
                        gameDifficulty = Difficulty.EASY;
                    } else if (args[i+1].toLowerCase().equals("medium")) {
                        gameDifficulty = Difficulty.MEDIUM;
                    } else if (args[i+1].toLowerCase().equals("hard")) {
                        gameDifficulty = Difficulty.HARD;
                    }
                }
                if ((myArg.equals("-help")) | (myArg.equals("-h"))) {
                    printUsageStatement();
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Error with command line arguments");
                System.err.println(e.toString());
            }
        }

        System.out.println("Welcome to NimAI!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Num Board (e.g. 1,4,2): ");
        String nimString = scanner.next();

        System.out.print("Would you like to go first? [y/n]: ");
        String humanFirst = scanner.next();
        boolean humanFirstBool;
        if (humanFirst.toLowerCase().equals("y")) {
            humanFirstBool = true;
        } else {
            humanFirstBool = false;
        }

        NimBoard board = new NimBoard(nimString, humanFirstBool);
        board.cleanBoard();

        while (!board.isEmpty()) {

            String position, amount;
            if (board.isHumanTurn()) {
                //Do Human Things
                do {
                    //Print board state
                    board.printBoardStateMessage();

                    //Get input
                    System.out.print("Enter the position on the Nim board (0 indexed): ");
                    position = scanner.next();

                    System.out.print("Enter the amount to remove: ");
                    amount = scanner.next();
                } while (!board.makeMove(Integer.parseInt(position),Integer.parseInt(amount)));
                System.out.println("You brought the board to: " + board.toString());
            } else {
                //Do Computer Things
                //Print board state
                board.printBoardStateMessage();

                NimAI.makeAIMoveFromDifficulty(gameDifficulty, board);
                System.out.println("The AI brought the board to: " + board.toString());

            }
        }

        System.out.print("\nThe winner is ");
        if (!board.isHumanTurn()) { //Since this is the player who was forced to make an invalid move, they're the current player
            System.out.print("Human!");
        } else {
            System.out.print("Computer!");
        }
        System.out.println();
    }

    public static void printUsageStatement() {
        System.out.println("USAGE");
    }

}
