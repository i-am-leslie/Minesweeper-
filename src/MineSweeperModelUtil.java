import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MineSweeperModelUtil {

    private final List<MineSweeperView> mineSweeperViewList;
    public final int[] dirX = {-1,-1,-1, 0, 0, 1, 1, 1};

    public  final int[] dirY = {-1, 0, 1, -1, 1, -1, 0, 1};


    public MineSweeperModelUtil() {
        this.mineSweeperViewList=new ArrayList<>();
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

    public static int randomlyPlaceMine(char[][] board, int size, List<int[]> minePlacement ) {
        Random rand = new Random();
        int minesPlaced = 0;
        int minesToPlace= rand.nextInt(size)+1;

        while (minesPlaced < minesToPlace) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);

            if (board[x][y] != 'M') {
                board[x][y] = 'M';
                minePlacement.add(new int[]{x,y});
                minesPlaced++;
            }
        }
        return minesPlaced;
    }
    public abstract void checkIfWon();

    public void addView(MineSweeperView mineSweeper) {
        this.mineSweeperViewList.add(mineSweeper);
    }
    public void updateView(char[][] board, MineSweeperModel.GameStatus gameStatus) {
        mineSweeperViewList.forEach((v)->  v.updateView(board, gameStatus));
    }

}
