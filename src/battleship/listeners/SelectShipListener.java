package battleship.listeners;

import battleship.ShipsPlacementFrame;
import battleship.ShipsToBePlacedPane;
import battleship.YourBoardPane;
import battleship.myShips.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
/**
 * This class is an Action Listener, which is activated when the player selects a ship to place on board
 * @author mpronoitis
 */
public class SelectShipListener implements ActionListener {
    /**
     * Constructor of SelectShipListener
     * @param ship: the ship that is selected
     */
    public SelectShipListener(Ship ship) {
        this.ship = ship;
    }
    /**
     * Constructor of SelectShipListener
     * @param ship: the ship that is selected
     * @param stbpPane: the panel where the ships that the player can select to place on board are
     * @param row: the row of the ship the player selected
     * @param boardPane: the row of the ship the player selected
     * @param spFrame: the frame where the player placed his ships on the board
     */
    public SelectShipListener(Ship ship, ShipsToBePlacedPane stbpPane, int row, YourBoardPane boardPane, ShipsPlacementFrame spFrame) {
        this.ship = ship;
        this.stbpPane = stbpPane;
        this.row = row;
        this.boardPane = boardPane;
        this.spFrame = spFrame;
    }
    /**
     * This method is activated when a ship is selected
     * @param e: the event that activated the listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedShip = (JButton) e.getSource();
        if (ship.getSelected() == 1) {
            //System.out.println("Placed or selected already");
            selectedShip.removeActionListener(this);
        } else {
            if (ship.getClass().getSimpleName().equals("BigShip")) {
                ship.setSelected(1);
                shipBtns = ((BigShip) ship).getShipBtns();
                for (JButton btn : shipBtns) {
                    btn.setBackground(Color.yellow);
                }
                ((BigShip) ship).setShipBtns(shipBtns);
            } else if (ship.getClass().getSimpleName().equals("MediumShip")) {
                ship.setSelected(1);
                shipBtns = ((MediumShip) ship).getShipBtns();
                for (JButton btn : shipBtns) {
                    btn.setBackground(Color.yellow);
                }
                ((MediumShip) ship).setShipBtns(shipBtns);
            } else if (ship.getClass().getSimpleName().equals("SmallShip")) {
                ship.setSelected(1);
                shipBtns = ((SmallShip) ship).getShipBtns();
                for (JButton btn : shipBtns) {
                    btn.setBackground(Color.yellow);
                }
                ((SmallShip) ship).setShipBtns(shipBtns);
            } else if (ship.getClass().getSimpleName().equals("TinyShip")) {
                ship.setSelected(1);
                shipBtns = ((TinyShip) ship).getShipBtns();
                for (JButton btn : shipBtns) {
                    btn.setBackground(Color.yellow);
                }
                ((TinyShip) ship).setShipBtns(shipBtns);
            }
            for (int i = 0; i < boardPane.getRowsBoard(); i++) {
                for (int j = 0; j < boardPane.getColsBoard(); j++) {
                    (boardPane.getBoardBtns())[i][j].addMouseListener(new PlaceShipOnBoardListener(ship, boardPane, i, j, selectedShip, stbpPane, spFrame, true));
                }
            }
        }
    }

    private ShipsToBePlacedPane stbpPane;

    private JButton selectedShip;

    private int row;

    private YourBoardPane boardPane;

    private ShipsPlacementFrame spFrame;

    private Ship ship;

    private JButton[] shipBtns;
}
