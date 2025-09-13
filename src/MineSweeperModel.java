import java.util.ArrayList;
import java.util.List;

public class MineSweeperModel extends MineSweeperModelBase {

    private final char[][] board;

    private GameStatus gameStatus;

    private int numberOfNonMines;

    private final List<int[]> minePlacement= new ArrayList<>();


    public MineSweeperModel() {
        super();
        this.board = new char[SIZE][SIZE];
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++) {
                this.board[i][j] = 'E';
            }
        }
        int minesPlaced= randomlyPlaceMine( this.board, SIZE, this.minePlacement);
        gameStatus= GameStatus.UNDECIDED;
        numberOfNonMines=(SIZE * SIZE) - minesPlaced;
        printBoard(this.board);
    }

    // Play method
    public  void clickBoard(int i, int j ){
        if(board[i][j] == 'M' || getGameStatus()== GameStatus.LOST){ //early return
            gameStatus=GameStatus.LOST;
            revealAllMines();
            updateView(this.board,getGameStatus());
            return;
        }
        revealSafeCells(i,j);
        printBoard(this.board);
        checkIfWon();
        System.out.println("Game Status: "+gameStatus);
        updateView(this.board,getGameStatus());
    }
    private void revealAllMines(){
        for(int[] pos: minePlacement){
            board[pos[0]][pos[1]]='X';
        }
    }

    private void revealSafeCells(int i, int j ){
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
                revealSafeCells(i+dirX[x],j+dirY[x]);
            }
        }
    }

    public void checkIfWon(){
        if(numberOfNonMines==0){
            gameStatus= GameStatus.WIN;
            return;
        }
        gameStatus= GameStatus.UNDECIDED;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
