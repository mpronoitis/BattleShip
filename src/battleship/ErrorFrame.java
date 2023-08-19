package battleship;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is a JFrame, which shows up when an error occurs
 * @author mpronoitis
 */
public class ErrorFrame extends JFrame{
    /**
     * Constructor of ErrorFrame
     * @param player: name of player, who set wrong dimensions for his board or too many ships 
     */
    public ErrorFrame(String player){
        this.player=player;
        JOptionPane.showMessageDialog(null, "Too many ships or small board for "+this.player+"'s board");
        System.exit(0);
    }
    private String player;
}
