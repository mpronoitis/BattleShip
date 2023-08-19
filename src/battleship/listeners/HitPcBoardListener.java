package battleship.listeners;

import battleship.PcBoardPane;
import battleship.PlayGameFrame;
import battleship.myShips.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
/**
 * This class is a MouseListener, which is activated when the player hits a tile on PC's board
 * @author mpronoitis
 */
public class HitPcBoardListener implements MouseListener {
    /**
     * Constructor of HitPcBoardListener
     * @param pcBoard: the board of the PC
     * @param playGameFrame: the frame where the game is played
     */
    public HitPcBoardListener(PcBoardPane pcBoard, PlayGameFrame playGameFrame) {
        this.pcBoard = pcBoard;
        this.playGameFrame = playGameFrame;
    }
    /**
     * This method checks if the game is over
     * @return true: the game is over
     */
    public boolean checkIfGameOver() {
        if (pcBoard.getHitted() == pcBoard.getShipsTiles()) {
            return true;
        }
        //System.out.println("You hit " + pcBoard.getHitted() + " from " + pcBoard.getShipsTiles());
        return false;
    }
    /**
     * This method is activated when we click on a tile of the PC's board
     * @param e: the event that activated the Mouse Listener 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        hittedBtn = (JButton) e.getSource();
        pcBoardBtns = pcBoard.getBoardBtns();
        pcShips = pcBoard.getPcShips();
        if (!checkIfGameOver()) {
            //System.out.println(hittedBtn.getName());
            if (hittedBtn.getName().equals("0")) {
                //System.out.println("you missed");
                hittedBtn.setBackground(Color.white);
                for (int i = 0; i < pcBoard.getRowsBoard(); i++) {
                    for (int j = 0; j < pcBoard.getColsBoard(); j++) {
                        if (hittedBtn == pcBoard.getBoardBtns()[i][j]) {
                            pcBoard.getBoardBtns()[i][j].setName("hitted");
                            pcBoard.getBoardBtns()[i][j].removeMouseListener(this);
                        }
                    }
                }
            } else {
                //System.out.println("boom!");
                for (int i = 0; i < pcBoard.getNumOfShips(); i++) {
                    if (pcShips[i].getClass().getSimpleName().equals("BigShip")) {
                        for (int j = 0; j < ((BigShip) pcShips[i]).getSize(); j++) {
                            if (hittedBtn == ((BigShip) pcShips[i]).getShipBtns()[j]) {
                                //System.out.println("you got me");
                                ((BigShip) pcShips[i]).getShipBtns()[j].setBackground(Color.red);
                                ((BigShip) pcShips[i]).getShipBtns()[j].setName("hitted");
                                ((BigShip) pcShips[i]).getShipBtns()[j].removeMouseListener(this);
                            }
                        }
                    }
                    if (pcShips[i].getClass().getSimpleName().equals("MediumShip")) {
                        for (int j = 0; j < ((MediumShip) pcShips[i]).getSize(); j++) {
                            if (hittedBtn == ((MediumShip) pcShips[i]).getShipBtns()[j]) {
                                //System.out.println("you got me");
                                ((MediumShip) pcShips[i]).getShipBtns()[j].setBackground(Color.red);
                                ((MediumShip) pcShips[i]).getShipBtns()[j].setName("hitted");
                                ((MediumShip) pcShips[i]).getShipBtns()[j].removeMouseListener(this);
                            }
                        }
                    }
                    if (pcShips[i].getClass().getSimpleName().equals("SmallShip")) {
                        for (int j = 0; j < ((SmallShip) pcShips[i]).getSize(); j++) {
                            if (hittedBtn == ((SmallShip) pcShips[i]).getShipBtns()[j]) {
                                //System.out.println("you got me");
                                ((SmallShip) pcShips[i]).getShipBtns()[j].setBackground(Color.red);
                                ((SmallShip) pcShips[i]).getShipBtns()[j].setName("hitted");
                                ((SmallShip) pcShips[i]).getShipBtns()[j].removeMouseListener(this);
                            }
                        }
                    }
                    if (pcShips[i].getClass().getSimpleName().equals("TinyShip")) {
                        for (int j = 0; j < ((TinyShip) pcShips[i]).getSize(); j++) {
                            if (hittedBtn == ((TinyShip) pcShips[i]).getShipBtns()[j]) {
                                //System.out.println("you got me");
                                ((TinyShip) pcShips[i]).getShipBtns()[j].setBackground(Color.red);
                                ((TinyShip) pcShips[i]).getShipBtns()[j].setName("hitted");
                                ((TinyShip) pcShips[i]).getShipBtns()[j].removeMouseListener(this);
                            }
                        }
                    }
                }
                pcBoard.shipHitted();
            }
            if (this.checkIfGameOver()) {
                for (int i = 0; i < pcBoard.getRowsBoard(); i++) {
                    for (int j = 0; j < pcBoard.getColsBoard(); j++) {
                        (pcBoard.getBoardBtns()[i][j]).removeMouseListener(this);
                    }
                }
                playGameFrame.gameOver(playGameFrame.getPlayersName());
            }
        }
        playGameFrame.pcTurn();
    }
    /**
     * This method is activated when the mouse is pressed
     * @param e: the event that activated the Mouse listener
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }
    /**
     * This method is activated when the mouse is released
     * @param e: the event that activated the Mouse listener
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    /**
     * This method is activated when the mouse is entered
     * @param e: the event that activated the Mouse listener
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        previewBtn = (JButton) e.getSource();
        if (!checkIfGameOver()) {
            previewBtn.setBackground(Color.green);
        }
    }
    /**
     * This method is activated when the mouse is exited
     * @param e: the event that activated the Mouse listener
     */
    @Override
    public void mouseExited(MouseEvent e) {
        pcBoardBtns = pcBoard.getBoardBtns();
        for (int i = 0; i < pcBoard.getRowsBoard(); i++) {
            for (int j = 0; j < pcBoard.getColsBoard(); j++) {
                if (!pcBoardBtns[i][j].getName().equals("hitted"))
                    pcBoardBtns[i][j].setBackground(Color.cyan);
            }
        }
        pcBoard.setBoardBtns(pcBoardBtns);
    }

    private JButton hittedBtn;

    private PcBoardPane pcBoard;

    private JButton[][] pcBoardBtns;

    private Ship[] pcShips;

    private JButton previewBtn;

    private PlayGameFrame playGameFrame;
}
