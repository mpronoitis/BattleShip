package battleship.myShips;
/**
 * This class represents the superclass Ship
 * @author mpronoitis
 */
public abstract class Ship {
    /**
     * Constructor of Ship for player
     * @param selected: the selected status of the ship
     * @param positioned: the positioned status of the ship
     * @param rotation: the rotation status of the ship
     * @param rowOnBoard: the row of the ship on board
     * @param colOnBoard: the column of the ship on board
     * @param player: which player's ship is 
     */
    public Ship(int selected, int positioned, int rotation, int rowOnBoard, int colOnBoard, String player) {
        this.selected = selected;
        this.positioned = positioned;
        this.rotation = rotation;
        this.rowOnBoard = rowOnBoard;
        this.colOnBoard = colOnBoard;
        this.player = player;
        this.id++;
    }
    /**
     * Constructor of Ship for PC
     * @param player: name of the player
     */
    public Ship(String player) {
        this.player = player;
    }
    /**
     * This method returns if the ship is already selected
     * @return selected: 1 if already selected, else 0
     */
    public int getSelected() {
        return selected;
    }
    /**
     * This method returns if the ship is already positioned on the board
     * @return positioned: 1 if already positioned, else 0
     */
    public int getPositioned() {
        return positioned;
    }
    /**
     * This method returns the rotation status of the ship
     * @return rotation: 0 if vertical, else 1
     */
    public int getRotation() {
        return rotation;
    }
    /**
     * This method returns the name of the player
     * @return player: name of the player
     */
    public String getPlayer() {
        return player;
    }
    /**
     * This method sets the selected status
     * @param selected: 1 if already selected, else 0
     */
    public void setSelected(int selected) {
        this.selected = selected;
    }
    /**
     * This method sets the positioned status
     * @param positioned: 1 if already positioned, else 0
     */
    public void setPositioned(int positioned) {
        this.positioned = positioned;
    }
    /**
     * This method sets the rotation status
     * @param rotation: 0 id vertical, else 1
     */
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    /**
     * This method returns the id of the ship
     * @return id: id of the ship
     */
    public int getId() {
        return id;
    }
    /**
     * This method returns the row on board where the ship is placed
     * @return rowOnBoard: the row on board where the ship is placed
     */
    public int getRowOnBoard() {
        return rowOnBoard;
    }
    /**
     * This method returns the column on board where the ship is placed
     * @return colOnBoard: the column on board where the ship is placed
     */
    public int getColOnBoard() {
        return colOnBoard;
    }
    /**
     * This method sets the row on board where the ship is placed
     * @param rowOnBoard: the row on board where the ship is placed
     */
    public void setRowOnBoard(int rowOnBoard) {
        this.rowOnBoard = rowOnBoard;
    }
    /**
     * This method sets the column on board where the ship is placed
     * @param colOnBoard: the column on board where the ship is placed
     */
    public void setColOnBoard(int colOnBoard) {
        this.colOnBoard = colOnBoard;
    }
    /**
     * This method sets the name of the player
     * @param player: name of the player
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    private static int id = 0;

    private int selected;

    private int positioned;

    private int rotation;

    private int rowOnBoard;

    private int colOnBoard;

    private String player;
}
