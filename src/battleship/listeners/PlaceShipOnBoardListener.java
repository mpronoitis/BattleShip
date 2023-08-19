package battleship.listeners;

import battleship.ShipsPlacementFrame;
import battleship.ShipsToBePlacedPane;
import battleship.YourBoardPane;
import battleship.listeners.RotateShipListener;
import battleship.myShips.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
/**
 * This class is a MouseListener, which is activated when the player is placing his ships on his board
 * @author mpronoitis
 */
public class PlaceShipOnBoardListener implements MouseListener {
    /**
     * Construtor of PlaceShipOnBoardListener
     * @param ship: the ship that the player wants to place on board
     * @param myPane: the player's board, where the ships are going to be placed
     * @param row: the row of the tile that its mouse listener is activated on the board 
     * @param col: the column of the tile that its mouse listener is activated on the board 
     * @param selectedShip: the exact tile on the ship that we chose 
     * @param stbpPane: the JPanel where the ships that we can place on board are
     * @param spFrame: the frame where the player places his ships on the board
     * @param selected: helps us understand if the ship is already placed on board 
     */
    public PlaceShipOnBoardListener(Ship ship, YourBoardPane myPane, int row, int col, JButton selectedShip, ShipsToBePlacedPane stbpPane, ShipsPlacementFrame spFrame, boolean selected) {
        this.ship = ship;
        this.myPane = myPane;
        this.row = row;
        this.col = col;
        this.selectedShip = selectedShip;
        this.stbpPane = stbpPane;
        this.spFrame = spFrame;
        this.selected = selected;
        rotateListener = new RotateShipListener();
        spFrame.getRotateBtn().addActionListener(rotateListener);
    }
    /**
     * This method returns the JPanel where the player's board is
     * @return myPane: the JPanel where the player's board is
     */
    public YourBoardPane getMyPane() {
        return myPane;
    }
    /**
     * This method return the buttons of the ship that was selected
     * @return shipBtn: the buttons of the ship that was selected
     */
    public JButton getShipBtn() {
        return shipBtn;
    }
    /**
     * Ths method returns the row on board that was selected, so that the ship can be placed on board
     * @return row: the row on player's board where the ship is going to be placed
     */
    public int getRow() {
        return row;
    }
    /**
     * Ths method returns the column on board that was selected, so that the ship can be placed on board
     * @return column: the column on player's board where the ship is going to be placed
     */
    public JButton getSelectedShip() {
        return selectedShip;
    }
    /**
     * This method sets the JPanel where the player's board is 
     * @param myPane: the new JPanel where the player's board is
     */
    public void setMyPane(YourBoardPane myPane) {
        this.myPane = myPane;
    }
    /**
     * This method sets the buttons of the ship that was selected
     * @param shipBtn: the buttons of the ship
     */
    public void setShipBtn(JButton shipBtn) {
        this.shipBtn = shipBtn;
    }
    /**
     * This method sets the row on the player's board that was selected
     * @param row: the row on the player's board
     */
    public void setRow(int row) {
        this.row = row;
    }
    /**
     * This method sets the column on the player's board that was selected
     * @param col: the column on the player's board
     */
    public void setCol(int col) {
        this.col = col;
    }
    /**
     * This method sets the ship that was selected
     * @param selectedShip: the ship that was selected
     */
    public void setSelectedShip(JButton selectedShip) {
        this.selectedShip = selectedShip;
    }
    /**
     * This method sets if the ship is already selected
     * @param selected: true if already selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    /**
     * This method is activated when the mouse is clicked
     * @param e: the event that activate the listener
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        shipBtn = (JButton) e.getSource();
        myShips = myPane.getBoardBtns();
        if (this.placedAll()) {
            for (int i = 0; i < myPane.getRowsBoard(); i++) {
                for (int j = 0; j < myPane.getColsBoard(); j++) {
                    myShips[i][j].removeMouseListener(this);
                }
            }
            myPane.setBoardBtns(myShips);
        }
        if (selected) {
            if (true) {
                if (ship.getPositioned() == 1) {
                    selectedShip.removeMouseListener(this);
                    selectedShip.addActionListener(rotateListener);
                } else {
                    if (shipBtn.getName().equals("1")) {
                        shipBtn.setBackground(Color.red);
                    } else {
                        myShips = myPane.getBoardBtns();
                        int newRow = this.row;
                        int newCol = this.col;
                        int newCol2 = this.col;
                        int newRow2 = this.row;
                        occupied = false;
                        if (ship.getClass().getSimpleName().equals("BigShip") && ship.getSelected() == 1) {
                            if ((rotateListener).getRotation().equals("horizontal")) {
                                if (newCol > myPane.getColsBoard() - ((BigShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newCol < myPane.getColsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newCol++;
                                    }
                                } else {
                                    for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                        if (myShips[newRow][newCol2].getName().equals("1"))
                                            occupied = true;
                                        newCol2++;
                                    }
                                    if (occupied == false) {
                                        ship.setRowOnBoard(newRow);
                                        ship.setColOnBoard(newCol);
                                        JButton[] tmpBtns = new JButton[((BigShip) ship).getSize()];
                                        for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newCol++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((BigShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            } else {
                                if (newRow > myPane.getRowsBoard() - ((BigShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newRow < myPane.getRowsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newRow++;
                                    }
                                } else {
                                    for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                        if (myShips[newRow2][newCol].getName().equals("1"))
                                            occupied = true;
                                        newRow2++;
                                    }
                                    if (occupied == false) {
                                        JButton[] tmpBtns = new JButton[((BigShip) ship).getSize()];
                                        for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newRow++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((BigShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            }
                        } else if (ship.getClass().getSimpleName().equals("MediumShip") && ship.getSelected() == 1) {
                            if ((rotateListener).getRotation().equals("horizontal")) {
                                if (newCol > myPane.getColsBoard() - ((MediumShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newCol < myPane.getColsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newCol++;
                                    }
                                } else {
                                    for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                        if (myShips[newRow][newCol2].getName().equals("1"))
                                            occupied = true;
                                        newCol2++;
                                    }
                                    if (occupied == false) {
                                        JButton[] tmpBtns = new JButton[((MediumShip) ship).getSize()];
                                        for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newCol++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((MediumShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            } else {
                                if (newRow > myPane.getRowsBoard() - ((MediumShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newRow < myPane.getRowsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newRow++;
                                    }
                                } else {
                                    for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                        if (myShips[newRow2][newCol].getName().equals("1"))
                                            occupied = true;
                                        newRow2++;
                                    }
                                    if (occupied == false) {
                                        JButton[] tmpBtns = new JButton[((MediumShip) ship).getSize()];
                                        for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newRow++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((MediumShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            }
                        } else if (ship.getClass().getSimpleName().equals("SmallShip") && ship.getSelected() == 1) {
                            if ((rotateListener).getRotation().equals("horizontal")) {
                                if (newCol > myPane.getColsBoard() - ((SmallShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newCol < myPane.getColsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newCol++;
                                    }
                                } else {
                                    for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                        if (myShips[newRow][newCol2].getName().equals("1"))
                                            occupied = true;
                                        newCol2++;
                                    }
                                    if (occupied == false) {
                                        JButton[] tmpBtns = new JButton[((SmallShip) ship).getSize()];
                                        for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newCol++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((SmallShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            } else {
                                if (newRow > myPane.getRowsBoard() - ((SmallShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newRow < myPane.getRowsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newRow++;
                                    }
                                } else {
                                    for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                        if (myShips[newRow2][newCol].getName().equals("1"))
                                            occupied = true;
                                        newRow2++;
                                    }
                                    if (occupied == false) {
                                        JButton[] tmpBtns = new JButton[((SmallShip) ship).getSize()];
                                        for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newRow++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((SmallShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            }
                        } else if (ship.getClass().getSimpleName().equals("TinyShip") && ship.getSelected() == 1) {
                            if ((rotateListener).getRotation().equals("horizontal")) {
                                if (newCol > myPane.getColsBoard() - ((TinyShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newCol < myPane.getColsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newCol++;
                                    }
                                } else {
                                    for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                        if (myShips[newRow][newCol2].getName().equals("1"))
                                            occupied = true;
                                        newCol2++;
                                    }
                                    if (occupied == false) {
                                        JButton[] tmpBtns = new JButton[((TinyShip) ship).getSize()];
                                        for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newCol++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((TinyShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            } else {
                                if (newRow > myPane.getRowsBoard() - ((TinyShip) ship).getSize()) {
                                    System.out.println("out of boundaries");
                                    while (newRow < myPane.getRowsBoard()) {
                                        myShips[newRow][newCol].setBackground(Color.red);
                                        newRow++;
                                    }
                                } else {
                                    for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                        if (myShips[newRow2][newCol].getName().equals("1"))
                                            occupied = true;
                                        newRow2++;
                                    }
                                    if (occupied == false) {
                                        JButton[] tmpBtns = new JButton[((TinyShip) ship).getSize()];
                                        for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                            myShips[newRow][newCol].setBackground(Color.gray);
                                            ship.setPositioned(1);
                                            myShips[newRow][newCol].setName("1");
                                            tmpBtns[i] = myShips[newRow][newCol];
                                            newRow++;
                                        }
                                        this.turnShipToWhite();
                                        placedCnt++;
                                        myPane.setBoardBtns(myShips);
                                        ((TinyShip) ship).setShipBtns(tmpBtns);
                                        this.setSelected(false);
                                        //System.out.println("ship placed, selected=" + selected);
                                    } else {
                                        shipBtn.setBackground(Color.red);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * This method is activated when the mouse is pressed
     * @param e: the event that activate the listener
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }
    /**
     * This method is activated when the mouse is released
     * @param e: the event that activate the listener
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    /**
     * This method is activated when the mouse is entered
     * @param e: the event that activate the listener
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        shipBtn = (JButton) e.getSource();
        myShips = myPane.getBoardBtns();
        //System.out.println("selected= " + selected);
        if (this.placedAll()) {
            for (int i = 0; i < myPane.getRowsBoard(); i++) {
                for (int j = 0; j < myPane.getColsBoard(); j++) {
                    myShips[i][j].removeMouseListener(this);
                }
            }
            myPane.setBoardBtns(myShips);
        }
        if (selected) {
            if (true) {
                if (shipBtn.getName().equals("1")) {
                    shipBtn.setBackground(Color.red);
                } else {
                    int newRow = this.row;
                    int newCol = this.col;
                    int newCol2 = this.col;
                    int newRow2 = this.row;
                    occupied = false;
                    if (ship.getClass().getSimpleName().equals("BigShip") && ship.getPositioned() == 0) {
                        if ((rotateListener).getRotation().equals("horizontal")) {
                            if (newCol > myPane.getColsBoard() - ((BigShip) ship).getSize()) {
                                while (newCol < myPane.getColsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newCol++;
                                }
                            } else {
                                for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                    if (myShips[newRow][newCol2].getName().equals("1"))
                                        occupied = true;
                                    newCol2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newCol++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        } else {
                            if (newRow > myPane.getRowsBoard() - ((BigShip) ship).getSize()) {
                                while (newRow < myPane.getRowsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newRow++;
                                }
                            } else {
                                for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                    if (myShips[newRow2][newCol].getName().equals("1"))
                                        occupied = true;
                                    newRow2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newRow++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        }
                    } else if (ship.getClass().getSimpleName().equals("MediumShip") && ship.getPositioned() == 0) {
                        if ((rotateListener).getRotation().equals("horizontal")) {
                            if (newCol > myPane.getColsBoard() - ((MediumShip) ship).getSize()) {
                                while (newCol < myPane.getColsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newCol++;
                                }
                            } else {
                                for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                    if (myShips[newRow][newCol2].getName().equals("1"))
                                        occupied = true;
                                    newCol2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newCol++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        } else {
                            if (newRow > myPane.getRowsBoard() - ((MediumShip) ship).getSize()) {
                                while (newRow < myPane.getRowsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newRow++;
                                }
                            } else {
                                for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                    if (myShips[newRow2][newCol].getName().equals("1"))
                                        occupied = true;
                                    newRow2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newRow++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        }
                    } else if (ship.getClass().getSimpleName().equals("SmallShip") && ship.getPositioned() == 0) {
                        if ((rotateListener).getRotation().equals("horizontal")) {
                            if (newCol > myPane.getColsBoard() - ((SmallShip) ship).getSize()) {
                                while (newCol < myPane.getColsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newCol++;
                                }
                            } else {
                                for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                    if (myShips[newRow][newCol2].getName().equals("1"))
                                        occupied = true;
                                    newCol2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newCol++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        } else {
                            if (newRow > myPane.getRowsBoard() - ((SmallShip) ship).getSize()) {
                                while (newRow < myPane.getRowsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newRow++;
                                }
                            } else {
                                for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                    if (myShips[newRow2][newCol].getName().equals("1"))
                                        occupied = true;
                                    newRow2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newRow++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        }
                    } else if (ship.getClass().getSimpleName().equals("TinyShip") && ship.getPositioned() == 0) {
                        if ((rotateListener).getRotation().equals("horizontal")) {
                            if (newCol > myPane.getColsBoard() - ((TinyShip) ship).getSize()) {
                                while (newCol < myPane.getColsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newCol++;
                                }
                            } else {
                                for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                    if (myShips[newRow][newCol2].getName().equals("1"))
                                        occupied = true;
                                    newCol2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newCol++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        } else {
                            if (newRow > myPane.getRowsBoard() - ((TinyShip) ship).getSize()) {
                                while (newRow < myPane.getRowsBoard()) {
                                    myShips[newRow][newCol].setBackground(Color.red);
                                    newRow++;
                                }
                            } else {
                                for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                    if (myShips[newRow2][newCol].getName().equals("1"))
                                        occupied = true;
                                    newRow2++;
                                }
                                if (occupied == false)
                                    for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                                        myShips[newRow][newCol].setBackground(Color.green);
                                        newRow++;
                                    }
                                else {
                                    shipBtn.setBackground(Color.red);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * This method is activated when the mouse is exited
     * @param e: the event that activate the listener
     */
    @Override
    public void mouseExited(MouseEvent e) {
        myShips = myPane.getBoardBtns();
        for (int i = 0; i < myPane.getRowsBoard(); i++) {
            for (int j = 0; j < myPane.getColsBoard(); j++) {
                if (myShips[i][j].getName().equals("1")) {
                    myShips[i][j].setBackground(Color.gray);
                    continue;
                }
                myShips[i][j].setBackground(Color.cyan);
            }
        }
        myPane.setBoardBtns(myShips);
    }
    /**
     * This method turns the color of the tiles of the ships that have been placed on board as white
     */
    public void turnShipToWhite() {
        JButton[] tmpBtns;
        if (ship.getClass().getSimpleName().equals("BigShip")) {
            tmpBtns = ((BigShip) ship).getShipBtns();
            for (int i = 0; i < ((BigShip) ship).getSize(); i++) {
                tmpBtns[i].setBackground(Color.white);
            }
            ((BigShip) ship).setShipBtns(tmpBtns);
        } else if (ship.getClass().getSimpleName().equals("MediumShip")) {
            tmpBtns = ((MediumShip) ship).getShipBtns();
            for (int i = 0; i < ((MediumShip) ship).getSize(); i++) {
                tmpBtns[i].setBackground(Color.white);
            }
            ((MediumShip) ship).setShipBtns(tmpBtns);
        } else if (ship.getClass().getSimpleName().equals("SmallShip")) {
            tmpBtns = ((SmallShip) ship).getShipBtns();
            for (int i = 0; i < ((SmallShip) ship).getSize(); i++) {
                tmpBtns[i].setBackground(Color.white);
            }
            ((SmallShip) ship).setShipBtns(tmpBtns);
        } else if (ship.getClass().getSimpleName().equals("TinyShip")) {
            tmpBtns = ((TinyShip) ship).getShipBtns();
            for (int i = 0; i < ((TinyShip) ship).getSize(); i++) {
                tmpBtns[i].setBackground(Color.white);
            }
            ((TinyShip) ship).setShipBtns(tmpBtns);
        }
    }
    /**
     * This method checks if the player placed all his ships on board
     * @return: true if the the player placed all his ships on board
     */
    public boolean placedAll() {
        if (placedCnt == stbpPane.getNumOfShips()) {
            spFrame.getStartBtn().setEnabled(true);
            return true;
        }
        return false;
    }
    private YourBoardPane myPane;

    private JButton shipBtn;

    private JButton[][] myShips;

    private int row;

    private int col;

    private JButton selectedShip;

    private boolean occupied;

    private ShipsToBePlacedPane stbpPane;

    private ShipsPlacementFrame spFrame;

    private static int placedCnt = 0;

    private Ship ship;

    private RotateShipListener rotateListener;

    private static boolean selected = false;
}
