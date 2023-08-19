package battleship;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class is a JPanel, where the player's board is
 * @author mpronoitis
 */
public class YourBoardPane extends JPanel {
    /**
     * Constructor of YourBoardPane
     * @param playersName: the name of the player
     */
    public YourBoardPane(String playersName) {
        this.playersName = playersName;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel("Your board");
        title.setAlignmentX(CENTER_ALIGNMENT);
        boardPane = new JPanel(new GridLayout(rowsBoard, colsBoard));
        boardBtns = new JButton[rowsBoard][colsBoard];
        for (int i = 0; i < rowsBoard; i++) {
            for (int j = 0; j < colsBoard; j++) {
                boardBtns[i][j] = new JButton();
                (boardBtns[i][j]).setName("0");
                (boardBtns[i][j]).setBackground(java.awt.Color.CYAN);
                boardPane.add(boardBtns[i][j]);
            }
        }
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(boardPane);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    /**
     * This method return the buttons on the player's board
     * @return boardBtns: the button's on the player's board
     */
    public JButton[][] getBoardBtns() {
        return this.boardBtns;
    }
    /**
     * This method sets the buttons on the player's board
     * @param boardBtns: the new buttons on the player's board
     */
    public void setBoardBtns(JButton[][] boardBtns) {
        this.boardBtns = boardBtns;
    }
    /**
     * This method return the number of rows on player's board
     * @return rowsBoard: number of rows on player's board
     */
    public int getRowsBoard() {
        return rowsBoard;
    }
    /**
     * This method return the number of columns on player's board
     * @return colsBoard: number of columns on player's board
     */
    public int getColsBoard() {
        return colsBoard;
    }
    /**
     * This method sets the title on top of the player's board
     * @param titleStr: new title on player's board
     */
    public void setTitle(String titleStr) {
        this.title.setText(titleStr);
    }
    /**
     * This method is a counter, which increases when the PC hits a player's ship
     */
    public void shipHitted() {
        hitted++;
    }
    /**
     * This method returns the number of player's ships that are hit by the PC
     * @return hitted: number of player's ships the PC hit
     */
    public int getHitted() {
        return hitted;
    }
    /**
     * This method return the name of the player
     * @return playersName: name of the player
     */
    public String getPlayersName() {
        return playersName;
    }
    /**
     * This method sets the number of the tiles that the ships are on   
     * @param shipsTiles: number of tiles the ships are on
     */
    public void setShipsTiles(int shipsTiles) {
        this.shipsTiles = shipsTiles;
    }
    /**
     * This method returns the number of tiles that the ships are on
     * @return shisTiles: number of tiles the ships are on
     */
    public int getShipsTiles() {
        return shipsTiles;
    }

    private JLabel title;

    private JButton[][] boardBtns;

    private JPanel boardPane;

    private int rowsBoard = 10;

    private int colsBoard = 10;

    private static int hitted = 0;

    private String playersName;

    private int shipsTiles;
}
