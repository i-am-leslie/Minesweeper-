import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MineSweeperModelUtil {


    private MineSweeperModelUtil() {
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " " + " | ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

}
