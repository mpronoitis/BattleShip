package battleship.listeners;

import battleship.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
/**
 * This class is an Action Listener, which is activated when the start game button is clicked
 * @author mpronoitis
 */
public class StartGameListener implements ActionListener {
    /**
     * Constructor of StartGameListener
     * @param spFrame: the frame where the player places his ships on board
     * @param boardPane: the player's board where the ships are placed  
     */
    public StartGameListener(ShipsPlacementFrame spFrame, YourBoardPane boardPane) {
        this.spFrame = spFrame;
        this.boardPane = boardPane;
    }
    /**
     * This method is activated when the start game button is pressed
     * @param e: the event that activated the listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton startBtn = (JButton) e.getSource();
        spFrame.setVisible(false);
        spFrame.dispose();
        playGameFrame = new PlayGameFrame(boardPane);
        playGameFrame.setVisible(true);
    }

    private ShipsPlacementFrame spFrame;

    private PlayGameFrame playGameFrame;

    private YourBoardPane boardPane;
}
