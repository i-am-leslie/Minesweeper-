import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame implements  MineSweeperView{
    private final JButton[][] buttons;


    public MineSweeperFrame(){
        super("MineSweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(MineSweeperModel.SIZE, MineSweeperModel.SIZE));
        MineSweeperModel model = new MineSweeperModel();
        MineSweeperController controller=new MineSweeperController(model);

        buttons=new JButton[MineSweeperModel.SIZE][MineSweeperModel.SIZE];

        model.addView(this);
        for(int i = 0; i< MineSweeperModel.SIZE; i++){
            for(int j = 0; j< MineSweeperModel.SIZE; j++){
                JButton b=new JButton(" ");
                b.setActionCommand(i + " "+ j);
                buttons[i][j]=b;
                b.addActionListener(controller);
                this.add(b);
            }
        }

        this.setSize(700,700);
        this.setVisible(true);
    }


    @Override
    public void updateView(char[][] board, MineSweeperModel.GameStatus gameStatus) {
        for (int i = 0; i < MineSweeperModel.SIZE; i++) {
            for (int j = 0; j < MineSweeperModel.SIZE; j++) {
                char square = board[i][j];
                if (square != 'E' && square != 'M') {
                    buttons[i][j].setText(String.valueOf(square));
                }
            }
        }

        if (gameStatus == MineSweeperModel.GameStatus.LOST) {
            System.out.println(gameStatus);
            JOptionPane.showMessageDialog(this, "You Lost");
            this.dispose();
        } else if (gameStatus == MineSweeperModel.GameStatus.WIN) {
            System.out.println(gameStatus);
            JOptionPane.showMessageDialog(this, "You Won");
            this.dispose();
        }


    }
    public static void main(String[] args){
        new MineSweeperFrame();
    }
}
