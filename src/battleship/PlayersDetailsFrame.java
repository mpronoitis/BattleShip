package battleship;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * This class is a JFrame, which shows up when the game is opened and the player must insert his name
 * @author mpronoitis
 */
public class PlayersDetailsFrame extends JFrame {
    /**
     * Constructor of PlayersDetailsFrame
     */
    public PlayersDetailsFrame() {
        this.name = JOptionPane.showInputDialog(null, "Please insert your name and press \"OK\"", "BattleShip-" + "Player's Details", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * This method returns the name that the player inserted
     * @return name: the name of the player
     */
    public String getName() {
        return name;
    }
    /**
     * This method changes the name of the player
     * @param name: the new name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
