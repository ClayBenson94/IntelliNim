import java.util.ArrayList;

/**
 * Created by Clay on 5/13/2016.
 */
public class NimBoard {
    private boolean isHumanTurn;
    private ArrayList<Integer> nimBoard;

    public NimBoard(String boardString, boolean humanPlaying) {
        nimBoard = new ArrayList<>();
        for (String splitChar: boardString.split(",")) {
            nimBoard.add(Integer.parseInt(splitChar));
        }
        isHumanTurn = humanPlaying;
    }

    public String toString(){
        String outputString = "(";

        if (isEmpty()) {
            return "(Empty)";
        }

        for (int i = 0; i < nimBoard.size(); ++i) {
            if (nimBoard.get(i) != 0) {
                outputString += Integer.toString(nimBoard.get(i));
            }
            if (i != nimBoard.size()-1) {
                outputString += ",";
            }
        }
        return outputString + ")";
    }

    public boolean isEmpty() {
        return nimBoard.size() == 0;
    }

    public boolean makeMove(int position, int amount) {
        if (position < 0){
            System.out.println("Position cannot be less than 0");
            return false;
        }
        if (position >= nimBoard.size()){
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

    public boolean isHumanTurn() {
        return isHumanTurn;
    }

    public void cleanBoard() {
        while (nimBoard.contains(0)) {
            nimBoard.remove((Integer) 0);
        }
    }

    public void changeTurns() {
        isHumanTurn = !isHumanTurn;
    }

    public int getBoardSize() {
        return nimBoard.size();
    }

    public ArrayList<Integer> getNimBoard() {
        return nimBoard;
    }

    public void printBoardStateMessage() {
        System.out.println();
        System.out.println("The Board's Current State is: " + toString());
    }

}
