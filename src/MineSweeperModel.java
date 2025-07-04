import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineSweeperModel {

    // Board
    // final array of possible positions
    private final char[][] board;

    private  final int[] dirX = {-1,-1,-1, 0, 0, 1, 1, 1};

    private  final int[] dirY = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static final int SIZE = 5;

    public enum GameStatus{WIN,UNDECIDED, LOST};

    private GameStatus gameStatus;

    private final List<MineSweeperView> mineSweeperViewList;

    private int numberOfNonMines;


    public MineSweeperModel() {
        this.board = new char[SIZE][SIZE];
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++) {
                this.board[i][j] = 'E';
            }
        }

        //randomly place mines
        Random rand = new Random();
        int minesPlaced = 0;
        int minesToPlace= rand.nextInt(SIZE);

        while (minesPlaced < minesToPlace) {
            int x = rand.nextInt(SIZE);
            int y = rand.nextInt(SIZE);

            if (board[x][y] != 'M') {
                board[x][y] = 'M';
                minesPlaced++;
            }
        }
        gameStatus= GameStatus.UNDECIDED;
        mineSweeperViewList=new ArrayList<>();
        numberOfNonMines=(SIZE * SIZE)- minesPlaced;
        printBoard();
    }

    // Play method
    public  void updateBoard(int i, int j ){
        if(board[i][j] == 'M' || getGameStatus()== GameStatus.LOST){ //early return
            gameStatus=GameStatus.LOST;
            mineSweeperViewList.forEach((v)->  v.updateView(this.board,getGameStatus()));
        }
        else{
            dfs(i,j);
            printBoard();
        }
        checkIfWon();
        System.out.println("Game Status: "+gameStatus);
        mineSweeperViewList.forEach((v)->  v.updateView(this.board,getGameStatus()));
    }

    private void dfs(int i, int j ){
        if(i<0 || i>=SIZE || j<0 || j>=SIZE ){ // handles invalid cases
            return;
        }
        if( board[i][j] != 'E'){ // handle case when it has been visited
            return;
        }

        int countMine=0;
        numberOfNonMines--;
        for(int x=0; x < 8; x++){
            int iPosition= i+dirX[x]; //edge of the graph
            int jPosition= j+dirY[x]; //edge of the graph

            //valid condition to check for a mine in the 8 adjacent corners
            if(iPosition>=0 && jPosition >=0 && iPosition < board.length && jPosition < board[0].length && board[iPosition][jPosition] == 'M' ){
                countMine++;
            }
        }
        if(countMine>0){
            board[i][j] = (char)(countMine + '0');
        }else {
            board[i][j] = 'B';
            // recurse tp other squares
            for(int x=0; x < 8; x++){
                dfs(i+dirX[x],j+dirY[x]);
            }
        }

    }
    public void checkIfWon(){
        if(numberOfNonMines==0){
            gameStatus=GameStatus.WIN;
            return;
        }
        gameStatus=GameStatus.UNDECIDED;
    }

    public List<MineSweeperView> getMineSweeperViewList() {
        return mineSweeperViewList;
    }

    public void addView(MineSweeperView mineSweeper) {
        this.mineSweeperViewList.add(mineSweeper);
    }

    private void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " "+ " | " );
            }
            System.out.println( " ");
        }
        System.out.println( " ");
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
