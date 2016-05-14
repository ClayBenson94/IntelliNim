/**
 * Created by Clay on 5/13/2016.
 */
public class NimAI {

    public static boolean makeDumbAIMove(NimBoard board) {
        isNumSumZero(board);
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

    public static boolean makeSmartAIMove(NimBoard board) {
        //TODO Implement AI
        return false;
    }

    public static boolean isNumSumZero(NimBoard board) {

        int maxIndex = board.getMaxIndex();
        int maxValue = board.getNimBoard().get(maxIndex);

        System.out.println(Integer.toBinaryString(maxValue));

        return false;
    }



}
