package battleship;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * This class is a JFrame, which shows up, when the game is over
 * @author mpronoitis
 */
public class GameOverFrame extends JFrame {
    /**
     * Constructor of GameOverFrame
     * @param winner: name of the winner
     */
    public GameOverFrame(String winner) {
        this.winner = winner;
        JOptionPane.showMessageDialog(null, this.winner + " is the winner!");
        System.exit(0);
    }

    private String winner;
}
