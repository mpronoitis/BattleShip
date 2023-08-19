package battleship;

import battleship.listeners.HitPcBoardListener;
import battleship.myShips.*;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
/**
 * This class is a JPanel, which represents the board of the PC
 * @author mpronoitis
 */
public class PcBoardPane extends JPanel {
    /**
     * Constructor of PcBoardPane
     * @param playGameFrame: the frame, where the game is going to be played
     */
    public PcBoardPane(PlayGameFrame playGameFrame) {
        this.playGameFrame = playGameFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel("Computer's board");
        title.setAlignmentX(CENTER_ALIGNMENT);
        boardPane = new JPanel(new GridLayout(rowsBoard, colsBoard));
        boardBtns = new JButton[rowsBoard][colsBoard];
        hitPcBoardListener = new HitPcBoardListener[rowsBoard][colsBoard];
        for (int i = 0; i < rowsBoard; i++) {
            for (int j = 0; j < colsBoard; j++) {
                boardBtns[i][j] = new JButton();
                (boardBtns[i][j]).setName("0");
                (boardBtns[i][j]).setBackground(Color.cyan);
                hitPcBoardListener[i][j] = new HitPcBoardListener(this, this.playGameFrame);
                boardBtns[i][j].addMouseListener(hitPcBoardListener[i][j]);
                boardPane.add(boardBtns[i][j]);
            }
        }
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(boardPane);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.createPcShips();
        this.checkBoard();
        this.placeShipsOnBoard();
    }
    /**
     * This method return the buttons of the PC's board
     * @return boardBtns: the buttons of the PC's board
     */
    public JButton[][] getBoardBtns() {
        return this.boardBtns;
    }
    /**
     * This method sets the buttons on the PC's board
     * @param boardBtns: the buttons of the PC's board
     */
    public void setBoardBtns(JButton[][] boardBtns) {
        this.boardBtns = boardBtns;
    }
    /**
     * This method returns the PC's ships
     * @return pcShips: the ships of the PC
     */
    public Ship[] getPcShips() {
        return pcShips;
    }
    /**
     * This method return the total number of ships that the PC has
     * @return numOfShips: number of PC's ships
     */
    public int getNumOfShips() {
        return numOfShips;
    }
    /**
     * This method creates the PC's ships
     */
    public void createPcShips() {
        pcShips = new Ship[numOfShips];
        for (int i = 0; i < numOfShips; i++) {
            if (i < numOfBigShips) {
                pcShips[i] = new BigShip("computer");
                shipsTiles += ((BigShip) pcShips[i]).getSize();
            } else if (i < numOfMediumShips + numOfBigShips) {
                pcShips[i] = new MediumShip("computer");
                shipsTiles += ((MediumShip) pcShips[i]).getSize();
            } else if (i < numOfSmallShips + numOfMediumShips + numOfBigShips) {
                pcShips[i] = new SmallShip("computer");
                shipsTiles += ((SmallShip) pcShips[i]).getSize();
            } else {
                pcShips[i] = new TinyShip("computer");
                shipsTiles += ((TinyShip) pcShips[i]).getSize();
            }
        }
    }
    /**
     * This method places the PC's ships on random places on board
     */
    public void placeShipsOnBoard() {
        String[] rotation = { "horizontal", "vertical" };
        int randomRow;
        int randomCol;
        int randomRotation;
        Random rand = new Random();
        JButton[] tmpBtns;
        for (int i = 0; i < numOfShips; i++) {
            int tmpCnt = 0;
            randomRow = (int) (Math.random() * (rowsBoard));
            randomCol = (int) (Math.random() * (colsBoard));
            randomRotation = (int) (Math.random() * ((1 - 0) + 1)) + 0;
            if (pcShips[i].getClass().getSimpleName().equals("BigShip")) {
                //System.out.println("BigShip");
                tmpBtns = new JButton[((BigShip) pcShips[i]).getSize()];
                while (this.occupied(randomRow, randomCol, rotation[randomRotation], ((BigShip) pcShips[i]).getSize())) {
                    randomRow =rand.nextInt((rowsBoard-1 - 0) + 1) + 0;
                    randomCol =rand.nextInt((colsBoard-1 - 0) + 1) + 0;
                    randomRotation =rand.nextInt((1 - 0) + 1) + 0;
                }
                if (rotation[randomRotation].equals("horizontal")) {
                    for (int c = randomCol; c < randomCol + ((BigShip) pcShips[i]).getSize(); c++) {
                        boardBtns[randomRow][c].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[randomRow][c];
                    }
                    ((BigShip) pcShips[i]).setShipBtns(tmpBtns);
                } else if (rotation[randomRotation].equals("vertical")) {
                    for (int r = randomRow; r < randomRow + ((BigShip) pcShips[i]).getSize(); r++) {
                        boardBtns[r][randomCol].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[r][randomCol];
                    }
                    ((BigShip) pcShips[i]).setShipBtns(tmpBtns);
                }
            } else if (pcShips[i].getClass().getSimpleName().equals("MediumShip")) {
                //System.out.println("MediumShip");
                tmpBtns = new JButton[((MediumShip) pcShips[i]).getSize()];
                while (this.occupied(randomRow, randomCol, rotation[randomRotation], ((MediumShip) pcShips[i]).getSize())) {
                    randomRow =rand.nextInt((rowsBoard-1 - 0) + 1) + 0;
                    randomCol =rand.nextInt((colsBoard-1 - 0) + 1) + 0;
                    randomRotation =rand.nextInt((1 - 0) + 1) + 0;
                }
                if (rotation[randomRotation].equals("horizontal")) {
                    for (int c = randomCol; c < randomCol + ((MediumShip) pcShips[i]).getSize(); c++) {
                        boardBtns[randomRow][c].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[randomRow][c];
                    }
                    ((MediumShip) pcShips[i]).setShipBtns(tmpBtns);
                } else if (rotation[randomRotation].equals("vertical")) {
                    for (int r = randomRow; r < randomRow + ((MediumShip) pcShips[i]).getSize(); r++) {
                        boardBtns[r][randomCol].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[r][randomCol];
                    }
                    ((MediumShip) pcShips[i]).setShipBtns(tmpBtns);
                }
            } else if (pcShips[i].getClass().getSimpleName().equals("SmallShip")) {
                //System.out.println("SmallShip");
                tmpBtns = new JButton[((SmallShip) pcShips[i]).getSize()];
                while (this.occupied(randomRow, randomCol, rotation[randomRotation], ((SmallShip) pcShips[i]).getSize())) {
                    randomRow =rand.nextInt((rowsBoard-1 - 0) + 1) + 0;
                    randomCol =rand.nextInt((colsBoard-1 - 0) + 1) + 0;
                    randomRotation =rand.nextInt((1 - 0) + 1) + 0;
                }
                if (rotation[randomRotation].equals("horizontal")) {
                    for (int c = randomCol; c < randomCol + ((SmallShip) pcShips[i]).getSize(); c++) {
                        boardBtns[randomRow][c].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[randomRow][c];
                    }
                    ((SmallShip) pcShips[i]).setShipBtns(tmpBtns);
                } else if (rotation[randomRotation].equals("vertical")) {
                    for (int r = randomRow; r < randomRow + ((SmallShip) pcShips[i]).getSize(); r++) {
                        boardBtns[r][randomCol].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[r][randomCol];
                    }
                    ((SmallShip) pcShips[i]).setShipBtns(tmpBtns);
                }
            } else if (pcShips[i].getClass().getSimpleName().equals("TinyShip")) {
                //System.out.println("TinyShip");
                tmpBtns = new JButton[((TinyShip) pcShips[i]).getSize()];
                while (this.occupied(randomRow, randomCol, rotation[randomRotation], ((TinyShip) pcShips[i]).getSize())) {
                    randomRow =rand.nextInt((rowsBoard-1 - 0) + 1) + 0;
                    randomCol =rand.nextInt((colsBoard-1 - 0) + 1) + 0;
                    randomRotation =rand.nextInt((1 - 0) + 1) + 0;
                }
                if (rotation[randomRotation].equals("horizontal")) {
                    for (int c = randomCol; c < randomCol + ((TinyShip) pcShips[i]).getSize(); c++) {
                        boardBtns[randomRow][c].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[randomRow][c];
                    }
                    ((TinyShip) pcShips[i]).setShipBtns(tmpBtns);
                } else if (rotation[randomRotation].equals("vertical")) {
                    for (int r = randomRow; r < randomRow + ((TinyShip) pcShips[i]).getSize(); r++) {
                        boardBtns[r][randomCol].setName("1");
                        tmpBtns[tmpCnt++] = boardBtns[r][randomCol];
                    }
                    ((TinyShip) pcShips[i]).setShipBtns(tmpBtns);
                }
            }
        }
    }
    /**
     * This method checks if the dimensions of the board are enough for all ships to be placed
     */
    public void checkBoard() {
        if (rowsBoard * colsBoard < shipsTiles) {
            System.out.println("Something is wrong");
            errorFrame = new ErrorFrame("pc");
        }
    }
    /**
     * This method checks if the random position on board doesn't place the ship out of boundaries
     * @param randomRow: a random row on board
     * @param randomCol: a random column on board
     * @param rotation: the random rotation of the ship
     * @param size: the size of the ship
     * @return true: ship is out of boundaries
     */
    public boolean outOfBoundaries(int randomRow, int randomCol, String rotation, int size) {
        if (rotation.equals("horizontal")) {
            if (randomCol > (colsBoard - size)) {
                System.out.println("out of boundaries randomCol: " + randomCol);
                return true;
            }
        } else {
            if (randomRow > (rowsBoard - size)) {
                System.out.println("out of boundaries randomRow: " + randomRow);
                return true;
            }
        }
        //System.out.println("not out of boundaries row: " + randomRow + " col: " + randomCol);
        return false;
    }
    /**
     * This method checks if the ships falls on another already placed ship
     * @param randomRow: a random row on board
     * @param randomCol: a random column on board
     * @param rotation: the random rotation of the ship
     * @param size: the size of the ship
     * @return true: ship can't be placed, some tiles are taken
     */
    public boolean occupied(int randomRow, int randomCol, String rotation, int size) {
        if (!outOfBoundaries(randomRow, randomCol, rotation, size)) {
            if (rotation.equals("horizontal")) {
                for (int c = randomCol; c < randomCol + size; c++) {
                    if (boardBtns[randomRow][c].getName().equals("1")) {
                        System.out.println("horizontal occupied found one: " + boardBtns[randomRow][c].getName());
                        return true;
                    }
                }
            } else if (rotation.equals("vertical")) {
                for (int r = randomRow; r < randomRow + size; r++) {
                    if (boardBtns[r][randomCol].getName().equals("1")) {
                        System.out.println("vertical occupied found one: " + boardBtns[r][randomCol].getName());
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }
    /**
     * This method is a counter of the PC's ships that are hit by the player
     */
    public void shipHitted() {
        hitted++;
    }
    /**
     * This method returns the PC's ships that are hit by the player
     * @return hitted: PC's ships hit by the player
     */
    public int getHitted() {
        return hitted;
    }
    /**
     * This method return the total sum of tiles that the PC's ships are on
     * @return shipsTiles: number of tiles the PC's ships are on
     */
    public int getShipsTiles() {
        return shipsTiles;
    }
    /**
     * This method returns the number of rows 
     * @return rowsBoard: number of rows 
     */
    public int getRowsBoard() {
        return rowsBoard;
    }
    /**
     * This method returns the number of columns 
     * @return colsBoard: number of columns
     */
    public int getColsBoard() {
        return colsBoard;
    }

    private JLabel title;
    private JButton[][] boardBtns;
    private JPanel boardPane;
    private int rowsBoard = 10;
    private int colsBoard = 10;
    private int numOfBigShips = 1;
    private int numOfMediumShips = 1;
    private int numOfSmallShips = 2;
    private int numOfTinyShips = 1;
    private int numOfShips = numOfBigShips + numOfMediumShips + numOfSmallShips + numOfTinyShips;
    private Ship[] pcShips;
    private int maxSize = 5;
    private HitPcBoardListener[][] hitPcBoardListener;
    private static int hitted = 0;
    private static int shipsTiles = 0;
    private PlayGameFrame playGameFrame;
    private int hitRow;
    private int hitCol;
    private static int pcHitted = 0;
    private ErrorFrame errorFrame;
}
