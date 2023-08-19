package battleship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
/**
 * This class is a JFrame, which shows up, when the player has placed all of his ships on board and is ready to play
 * @author mpronoitis
 */
public class PlayGameFrame extends JFrame {
    /**
     * Constructor of PlayGameFrame
     * @param myBoard: the player's board, which he created
     */
    public PlayGameFrame(YourBoardPane myBoard) {
        super();
        setSize(900, 500);
        setTitle("BattleShip- Play Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.myBoard = myBoard;
        mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainPane.setOneTouchExpandable(false);
        mainPane.setDividerLocation(450);
        myBoardPane = new YourGameBoardPane(this.myBoard);
        pcBoardPane = new PcBoardPane(this);
        mainPane.add(myBoardPane);
        mainPane.add(pcBoardPane);
        add(mainPane);
    }
    /**
     * This method return the name of the player
     * @return myBoard.getPlayersName(): this method returns the name of the player
     */
    public String getPlayersName() {
        return myBoard.getPlayersName();
    }
    /**
     * This method checks if the game is over and if it's over, then it shows the Game Over frame with the name of the winner
     * @param winner: the name of the winner
     */
    public void gameOver(String winner) {
        System.out.println("Game Over! Winner--> " + winner);
        gameOverFrame = new GameOverFrame(winner);
    }
    /**
     * This method chooses a random position on player's board and hits it, as if the PC is playing
     */
    public void pcTurn() {
        int randomRow;
        int randomCol;
        randomRow = (int) (Math.random() * (((myBoard.getRowsBoard() - 1) - 0) + 1)) + 0;
        randomCol = (int) (Math.random() * (((myBoard.getColsBoard() - 1) - 0) + 1)) + 0;
        while (myBoard.getBoardBtns()[randomRow][randomCol].getName().equals("hitted")) {
            randomRow = (int) (Math.random() * (((myBoard.getRowsBoard() - 1) - 0) + 1)) + 0;
            randomCol = (int) (Math.random() * (((myBoard.getColsBoard() - 1) - 0) + 1)) + 0;
        }
        if (myBoard.getBoardBtns()[randomRow][randomCol].getName().equals("0")) {
            myBoard.getBoardBtns()[randomRow][randomCol].setBackground(Color.white);
        } else if (myBoard.getBoardBtns()[randomRow][randomCol].getName().equals("1")) {
            myBoard.getBoardBtns()[randomRow][randomCol].setBackground(Color.red);
            myBoard.shipHitted();
        }
        myBoard.getBoardBtns()[randomRow][randomCol].setName("hitted");
        if (this.checkIfPcWon()) {
            this.gameOver("Computer");
        }
    }
    /**
     * This method checks if the PC is the winner
     * @return true: the PC won
     */
    public boolean checkIfPcWon() {
        if (myBoard.getHitted() == myBoard.getShipsTiles()) {
            return true;
        }
        //System.out.println(myBoard.getHitted() + " " + myBoard.getShipsTiles());
        return false;
    }

    private YourBoardPane myBoard;

    private JSplitPane mainPane;

    private YourGameBoardPane myBoardPane;

    private PcBoardPane pcBoardPane;

    private GameOverFrame gameOverFrame;
}
