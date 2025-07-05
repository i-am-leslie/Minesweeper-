import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperController implements ActionListener {

    private final MineSweeperModel model;

    public MineSweeperController(MineSweeperModel model) {
        this.model=model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] command = e.getActionCommand().split(" ");
        int i=Integer.parseInt(command[0]);
        int j=Integer.parseInt(command[1]);
        model.clickBoard(i,j);
    }
}
