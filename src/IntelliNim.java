import java.util.Scanner;

public class IntelliNim {

    public static void main(String[] args) {
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

        while (!board.isEmpty()) {

            boolean moveMade = false;
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

                NimAI.makeDumbAIMove(board);
                System.out.println("The AI brought the board to: " + board.toString());

            }
        }

        System.out.print("\n\nThe winner is ");
        if (!board.isHumanTurn()) { //Since this is the player who was forced to make an invalid move, they're the current player
            System.out.print("Human!");
        } else {
            System.out.print("Computer!");
        }
    }
}
