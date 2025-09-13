import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MineSweeperModelBase {
    private final List<MineSweeperView> mineSweeperViewList;

    protected final int[] dirX = {-1,-1,-1, 0, 0, 1, 1, 1};

    protected  final int[] dirY = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static final int SIZE = 5;

    public enum GameStatus{WIN,UNDECIDED, LOST};

    public MineSweeperModelBase(){
        mineSweeperViewList= new ArrayList<>();
    }

    public int randomlyPlaceMine(char[][] board, int size, List<int[]> minePlacement ) {
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

    public void addView(MineSweeperView mineSweeper) {
        this.mineSweeperViewList.add(mineSweeper);
    }

    public void updateView(char[][] board,GameStatus gameStatus) {
        mineSweeperViewList.forEach((v)->  v.updateView(board, gameStatus));
    }

    public void printBoard(char[][] board) {
        MineSweeperModelUtil.printBoard(board);
    }
}
