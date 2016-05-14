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

            //Print board state
            board.printBoardStateMessage();

            boolean moveMade = false;
            String position, amount;
            if (board.isHumanTurn()) {
                //Do Human Things
                do {
                    //Get input
                    System.out.print("Enter the position on the Nim board (0 indexed): ");
                    position = scanner.next();

                    System.out.print("Enter the amount to remove: ");
                    amount = scanner.next();
                } while (!board.makeMove(Integer.parseInt(position),Integer.parseInt(amount)));
            } else {
                //Do Computer Things
            }
        }
    }
}
