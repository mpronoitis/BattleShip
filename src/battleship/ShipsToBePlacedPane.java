package battleship;

import battleship.myShips.*;
import java.awt.Color;
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
 * This class is a JPanel, where the ships that the player can place on board are
 * @author mpronoitis
 */
public class ShipsToBePlacedPane extends JPanel {
    /**
     * Constructor of ShipsToBePlacedPane
     * @param spFrame: the frame, where the player places his ships on his board 
     * @param boardPane: the JPanel where the player's board is
     */
    public ShipsToBePlacedPane(ShipsPlacementFrame spFrame, YourBoardPane boardPane) {
        this.spFrame = spFrame;
        this.boardPane = boardPane;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel("Ships to be placed");
        title.setAlignmentX(CENTER_ALIGNMENT);
        shipsPane = new JPanel(new GridLayout(numOfShips, maxSize, 0, 5));
        this.createWhiteBtns();
        this.createShips();
        boardPane.setShipsTiles(shipsTiles);
        shipsPane.setPreferredSize(new Dimension(250, 80));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(shipsPane);
        this.setBorder(BorderFactory.createEmptyBorder(100, 10, 100, 10));
    }
    /**
     * This method return the player's ships
     * @return myShips: the player's ships
     */
    public Ship[] getShips() {
        return myShips;
    }
    /**
     * This method creates the player's ships
     */
    private void createShips() {
        myShips = new Ship[numOfShips];
        for (int i = 0; i < numOfShips; i++) {
            if (i < numOfBigShips) {
                myShips[i] = new BigShip(0, 0, 0, 0, 0, "player", this, i, this.boardPane, this.spFrame);
                tmpBtns = ((BigShip) myShips[i]).getShipBtns();
                shipSize = ((BigShip) myShips[i]).getSize();
                for (int j = 0; j < shipSize; j++) {
                    shipsPane.add(tmpBtns[j]);
                    shipsTiles++;
                }
            } else if (i < numOfMediumShips + numOfBigShips) {
                myShips[i] = new MediumShip(0, 0, 0, 0, 0, "player", this, i, this.boardPane, this.spFrame);
                tmpBtns = ((MediumShip) myShips[i]).getShipBtns();
                shipSize = ((MediumShip) myShips[i]).getSize();
                for (int j = 0; j < maxSize; j++) {
                    if (j < shipSize) {
                        shipsPane.add(tmpBtns[j]);
                        shipsTiles++;
                    } else {
                        shipsPane.add(mediumWhiteBtns[i - numOfBigShips]);
                    }
                }
            } else if (i < numOfSmallShips + numOfMediumShips + numOfBigShips) {
                myShips[i] = new SmallShip(0, 0, 0, 0, 0, "player", this, i, this.boardPane, this.spFrame);
                tmpBtns = ((SmallShip) myShips[i]).getShipBtns();
                shipSize = ((SmallShip) myShips[i]).getSize();
                for (int j = 0; j < maxSize; j++) {
                    if (j < shipSize) {
                        shipsPane.add(tmpBtns[j]);
                        shipsTiles++;
                    } else {
                        shipsPane.add(smallWhiteBtns[i - numOfBigShips - numOfMediumShips][j - shipSize]);
                    }
                }
            } else {
                myShips[i] = new TinyShip(0, 0, 0, 0, 0, "player", this, i, this.boardPane, this.spFrame);
                tmpBtns = ((TinyShip) myShips[i]).getShipBtns();
                shipSize = ((TinyShip) myShips[i]).getSize();
                for (int j = 0; j < maxSize; j++) {
                    if (j < shipSize) {
                        shipsPane.add(tmpBtns[j]);
                        shipsTiles++;
                    } else {
                        shipsPane.add(tinyWhiteBtns[i - numOfBigShips - numOfMediumShips - numOfSmallShips][j - shipSize]);
                    }
                }
            }
        }
    }
    /**
     * This method creates the white buttons, where no ship is placed
     */
    public void createWhiteBtns() {
        mediumWhiteBtns = new JButton[numOfMediumShips];
        for (int i = 0; i < numOfMediumShips; i++) {
            mediumWhiteBtns[i] = new JButton();
            mediumWhiteBtns[i].setBackground(Color.white);
        }
        smallWhiteBtns = new JButton[numOfSmallShips][maxSize - 3];
        for (int i = 0; i < numOfSmallShips; i++) {
            for (int j = 0; j < (maxSize - 3); j++) {
                smallWhiteBtns[i][j] = new JButton();
                smallWhiteBtns[i][j].setBackground(Color.white);
            }
        }
        tinyWhiteBtns = new JButton[numOfTinyShips][maxSize - 2];
        for (int i = 0; i < numOfTinyShips; i++) {
            for (int j = 0; j < (maxSize - 2); j++) {
                tinyWhiteBtns[i][j] = new JButton();
                tinyWhiteBtns[i][j].setBackground(Color.white);
            }
        }
    }
    /**
     * This method returns the total number of ships the player has
     * @return numOfShips: the number of the player's ships
     */
    public int getNumOfShips() {
        return numOfShips;
    }
    /**
     * This method returns the total number of tiles the player's ships are on
     * @return shipsTiles: the total number of tiles the player's ships are on
     */
    public int getShipsTiles() {
        return shipsTiles;
    }

    private JLabel title;

    private JPanel shipsPane;

    private YourBoardPane boardPane;

    private ShipsPlacementFrame spFrame;

    private Ship[] myShips;

    private int maxSize = 5;

    private int shipSize;

    private JButton[] tmpBtns;

    private int numOfBigShips = 1;

    private int numOfMediumShips = 1;

    private int numOfSmallShips = 2;

    private int numOfTinyShips = 1;

    private int numOfShips = numOfBigShips + numOfMediumShips + numOfSmallShips + numOfTinyShips;

    private JButton[] mediumWhiteBtns;

    private JButton[][] smallWhiteBtns;

    private JButton[][] tinyWhiteBtns;

    private int shipsTiles = 0;
}
