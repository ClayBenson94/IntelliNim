import java.util.ArrayList;

/**
 * @author Clay Benson (cmb3602@rit.edu)
 *
 * Class that holds the logic for making intelligent (or not so intelligent) moves on a NimBoard object.
 *
 * These are all static methods as they just perform logic or make moves on NimBoard objects
 */
public class NimAI {

    public static boolean makeAIMoveFromDifficulty(IntelliNim.Difficulty difficulty, NimBoard board) {
        switch (difficulty) {
            case EASY:
                return makeStupidAIMove(board);
            case MEDIUM:
                return makeBasicAIMove(board);
            case HARD:
                return makeSmartInefficientAIMove(board);
            default:
                return makeSmartInefficientAIMove(board);
        }
    }

    /**
     * Makes a basic move on a NimBoard. This AI will only know how to win if the board has 2 heaps that are not equal
     * If that is the case, it will reduce the board to 2 equal heaps, which is a winning position for the AI.
     * @param board The NimBoard to make a move on
     * @return Boolean representing if it was able to make a valid move (should be true in most cases, false if
     *          something broke)
     */
    public static boolean makeBasicAIMove(NimBoard board) {
        if (board.getBoardSize() == 2) {
            int leftAmount, rightAmount, difference;
            leftAmount = board.getNimBoard().get(0);
            rightAmount = board.getNimBoard().get(1);
            if (board.getNimBoard().get(0) > board.getNimBoard().get(1)) {
                difference = leftAmount - rightAmount;
                return board.makeMove(0, difference);
            } else if (board.getNimBoard().get(0) < board.getNimBoard().get(1)) {
                difference = rightAmount - leftAmount;
                return board.makeMove(1, difference);
            }
        } else if (board.getBoardSize() == 1) {
            return board.makeMove(0, board.getNimBoard().get(0));
        }
        return board.makeMove(0,1);
    }


    public static boolean makeStupidAIMove(NimBoard board) {
        return board.makeMove(0,1);
    }

    /**
     * Makes an intelligent move on a NimBoard. This uses the fact that if the NimSum of a given board is Zero, then
     * it is a winning position for the player who brings it to that state.
     * The reason this method is inefficient is because it tries every possible move on every stack until it sees that
     * one of those moves makes a NimSum of zero. If that is the case, it will make the move and finish.
     * @param board The NimBoard to make a move on
     * @return Boolean representing if it was able to make a valid move (should be true in most cases, false if
     *          something broke)
     */
    public static boolean makeSmartInefficientAIMove(NimBoard board) {

        NimBoard tempGame;

        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 1; j <= board.getNimBoard().get(i); ++j) {
                tempGame = new NimBoard(board);
                tempGame.makeMove(i,j);
                if (nimSum(tempGame) == 0) {
                    return board.makeMove(i,j);
                }
            }
        }

        return board.makeMove(0,1);
    }

    /**
     * Returns the NimSum of a given board. This is best explained as the Bitwise XOR of all the heaps. Given that
     * Bitwise XOR (and therefore NimSum) is associative, it can simply be calculated as a running NimSum.
     * @param board The NimBoard to calculate the NimSum of
     * @return The NimSum of the board
     */
    public static int nimSum(NimBoard board) {
        if (board.getBoardSize() == 0) {
            return 0;
        } else if (board.getBoardSize()== 1) {
            return board.getNimBoard().get(0);
        } else {
            int runningSum = board.getNimBoard().get(0);
            for (int i = 1; i < board.getBoardSize(); ++i) {
                runningSum = runningSum ^ board.getNimBoard().get(i);
            }
            return runningSum;
        }
    }

}
