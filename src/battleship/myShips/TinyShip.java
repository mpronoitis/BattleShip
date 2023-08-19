package battleship.myShips;

import battleship.listeners.SelectShipListener;
import battleship.ShipsPlacementFrame;
import battleship.ShipsToBePlacedPane;
import battleship.YourBoardPane;
import java.awt.Color;
import javax.swing.JButton;
/**
 * This class represents a tiny sized ship
 * @author mpronoitis
 */
public class TinyShip extends Ship {
    /**
     * Constructor of TinyShip for player
     * @param selected: the selected status of the ship
     * @param positioned: the positioned status of the ship
     * @param rotation: the rotation status of the ship
     * @param rowOnBoard: the row of the ship on board
     * @param colOnBoard: the column of the ship on board
     * @param player: which player's ship is 
     * @param stbpPane: the JPanel where the available to be placed on board player's ships are
     * @param row: the row of the ship before it's placed on board
     * @param boardPane: the column of the ship before it's placed on board
     * @param spFrame: the JFrame where the player places his ships on board
     */
    public TinyShip(int selected, int positioned, int rotation, int rowOnBoard, int colOnBoard, String player, ShipsToBePlacedPane stbpPane, int row, YourBoardPane boardPane, ShipsPlacementFrame spFrame) {
        super(selected, positioned, rotation, rowOnBoard, colOnBoard, player);
        this.stbpPane = stbpPane;
        this.row = row;
        this.boardPane = boardPane;
        this.spFrame = spFrame;
        selectListener = new SelectShipListener[size];
        shipBtns = new JButton[size];
        for (int i = 0; i < size; i++) {
            shipBtns[i] = new JButton();
            shipBtns[i].setBackground(Color.gray);
            selectListener[i] = new SelectShipListener(this, this.stbpPane, row, this.boardPane, this.spFrame);
            shipBtns[i].addActionListener(selectListener[i]);
        }
    }
    /**
     * Constructor of TinyShip for PC
     * @param player: name of the player
     */
    public TinyShip(String player) {
        super(player);
        shipBtns = new JButton[size];
        for (int i = 0; i < size; i++) {
            shipBtns[i] = new JButton();
            shipBtns[i].setBackground(Color.cyan);
        }
    }
    /**
     * This method returns the buttons of the ship
     * @return shipBtns: the buttons of the ship
     */
    public JButton[] getShipBtns() {
        return shipBtns;
    }
    /**
     * This method returns the size of the ship
     * @return size: size of the ship
     */
    public int getSize() {
        return size;
    }
    /**
     * This method sets the buttons of the ship
     * @param shipBtns: the new buttons of the ship
     */
    public void setShipBtns(JButton[] shipBtns) {
        this.shipBtns = shipBtns;
    }
    /**
     * This method sets the size of the ship
     * @param size: size of the ship
     */
    public void setSize(int size) {
        this.size = size;
    }

    private JButton[] shipBtns;

    private int size = 2;

    private ShipsToBePlacedPane stbpPane;

    private int row;

    private YourBoardPane boardPane;

    private ShipsPlacementFrame spFrame;

    private SelectShipListener selectListener[];
}
