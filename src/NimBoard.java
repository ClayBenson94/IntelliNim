import java.util.ArrayList;
import java.util.stream.Collector;

/**
 * @author Clay Benson (cmb3602@rit.edu)
 *
 * Class representing a board (series of heaps) of Nim.
 * Keeps track of whose turn it currently is, as well as the current state of the heaps (board itself).
 * Also holds logic for making moves on the board and printing the board state.
 */
public class NimBoard {
    private boolean isHumanTurn;
    private ArrayList<Integer> nimBoard;

    /**
     * Constructor for the game board. Takes in a string representing the board and turns it into a game.
     * @param boardString Comma separated string of integers representing the stacks (e.g. "1,4,2")
     * @param humanPlaying Boolean representing if the human is playing currently (human's turn first)
     */
    public NimBoard(String boardString, boolean humanPlaying) {
        nimBoard = new ArrayList<>();
        try {
            for (String splitChar: boardString.split(",")) {
                nimBoard.add(Integer.parseInt(splitChar));
            }
        } catch (NumberFormatException e) {
            System.err.println("You done goofed up your input! I'll give you a sample board to play on...");
            nimBoard = new ArrayList<>();
            nimBoard.add(1);
            nimBoard.add(4);
            nimBoard.add(2);
        }

        isHumanTurn = humanPlaying;
    }

    /**
     * Constructor used for copying a board. Useful when making temporary boards to calculate possible moves
     * @param copy A NimBoard object to make a copy of
     */
    public NimBoard(NimBoard copy) {
        isHumanTurn = new Boolean(copy.isHumanTurn());
        nimBoard = new ArrayList<>(copy.getNimBoard());
    }

    /**
     * Makes a printable string version of the board
     *
     * Example:
     *      (Empty)
     * Example 2:
     *      (1,4,2)
     *
     * @return A string representing the heaps on the board
     */
    public String toString(){
        String outputString = "(";

        if (isEmpty()) {
            return "(Empty)";
        }

        for (int i = 0; i < getBoardSize(); ++i) {
            if (nimBoard.get(i) != 0) {
                outputString += Integer.toString(nimBoard.get(i));
            }
            if (i != getBoardSize()-1) {
                outputString += ",";
            }
        }
        return outputString + ")";
    }

    /**
     * Function to query if the board is empty or not
     * @return Boolean representing if the board is empty or not
     */
    public boolean isEmpty() {
        return getBoardSize() == 0;
    }

    /**
     * This function makes a move on this NimBoard object.
     * @param position 0 indexed location of the heap on which to move
     * @param amount The amount to subtract from the given heap
     * @return Boolean representing if the move was valid or not
     */
    public boolean makeMove(int position, int amount) {
        if (position < 0){
            System.out.println("Position cannot be less than 0");
            return false;
        }
        if (position >= getBoardSize()){
            System.out.println("Position cannot be more than length of board");
            return false;
        }
        if (amount <= 0){
            System.out.println("Amount must be greater than 0");
            return false;
        }
        if (nimBoard.get(position)-amount < 0){
            System.out.println("Position cannot be less than 0");
            return false;
        }
        nimBoard.set(position,nimBoard.get(position)-amount);
        cleanBoard();
        changeTurns();
        return true;
    }

    /**
     * Returns if it is the humans turn or not
     * @return Boolean representing if it's the human's turn or not
     */
    public boolean isHumanTurn() {
        return isHumanTurn;
    }

    /**
     * Removes all 0 stacks from the board, simplifying it.
     */
    public void cleanBoard() {
        nimBoard.removeIf(stack -> stack.equals(0));
    }

    /**
     * Negates the boolean representing whose turn it is.
     */
    public void changeTurns() {
        isHumanTurn = !isHumanTurn;
    }

    /**
     * Gets the size of the board
     * @return The number of heaps on the board (will include 0 stacks if not simplified using cleanBoard())
     */
    public int getBoardSize() {
        return nimBoard.size();
    }

    /**
     * Gives a reference to the ArrayList representing the heaps of the board.
     * This allows other code to query certain positions of the board.
     * @return An ArrayList of Integers representing the heaps
     */
    public ArrayList<Integer> getNimBoard() {
        return nimBoard;
    }

    /**
     * Prints a human readable message stating the heaps on the board
     */
    public void printBoardStateMessage() {
        System.out.println();
        printGraphicBoard();
        System.out.println("The Board's Current State is: " + toString());
    }

    /**
     * A (maybe unnecessarily) complex method to print out a graphic representation of a board
     */
    public void printGraphicBoard() {

        //Don't print if its a huge board
        if ((getBoardSize() > 10) | (getMaxValue() > 10 )) {
            return;
        }

        int width = 3*getBoardSize();
        width += getBoardSize();
        int height = getMaxValue()+1;



        char[][] printArray = new char[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                printArray[i][j] = '=';
                if ((j+1) % 4 == 0) {
                    printArray[i][j] = ' ';
                    if (!(nimBoard.get(((j+1)/4)-1)+i >= height-1)) {
                        printArray[i][j-1] = ' ';
                        printArray[i][j-2] = ' ';
                        printArray[i][j-3] = ' ';
                    }
                }
                if (((j+3) % 4 == 0) & (i == height-1)) {
                    printArray[i][j] = Integer.toString(((j+3)/4)-1).charAt(0);
                    printArray[i][j-1] = ' ';
                    j++;
                    printArray[i][j] = ' ';
                }

            }
        }

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                System.out.print(printArray[i][j]);
            }
            System.out.println();
        }

    }

    /**
     * Returns the value of the largest heap
     * @return The value of the largest heap
     */
    public int getMaxValue() {
        int maxValue = 0;
        for (int val : nimBoard) {
            if (val > maxValue) {
                maxValue = val;
            }
        }
        return maxValue;
    }

}
