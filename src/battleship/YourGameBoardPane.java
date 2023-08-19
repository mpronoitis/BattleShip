package battleship;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
/**
 * This class is a JPanel, where the player's board with a title is placed
 * @author mpronoitis
 */
public class YourGameBoardPane extends JPanel {
    /**
     * Constructor of YourGameBoardPane
     * @param boardPane: the JPanel, where the player's board is
     */
    public YourGameBoardPane(YourBoardPane boardPane) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.boardPane = boardPane;
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        playersName = boardPane.getPlayersName();
        this.boardPane.setTitle(playersName + "'s Board");
        this.add(boardPane);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private YourBoardPane boardPane;

    private String playersName;
}
