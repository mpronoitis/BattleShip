package battleship;

import battleship.listeners.StartGameListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
/**
 * This class is a JFrame, where the player places his ships on his board
 * @author mpronoitis
 */
public class ShipsPlacementFrame extends JFrame {
    /**
     * Constructor of ShipsPlacementFrame
     * @param playersName: the name of the player
     */
    public ShipsPlacementFrame(String playersName) {
        super();
        this.playersName = playersName;
        setSize(700, 500);
        setTitle("BattleShip- Ship's Placement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        instructionsLabel = new JLabel("Please select ships from the left and place them in your board. Press \"start game\" when you are ready!", SwingConstants.CENTER);
       
        instructionsPane=new JPanel(new FlowLayout());
        helpButton=new JButton("Help");
        helpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                try {
                    File myObj = new File("BattleshipHelp.txt");
                    Scanner myReader = new Scanner(myObj);
                    String helpStr="";
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        helpStr=helpStr+data+"\n";
                    }
                    helpPane=new HelpFrame(helpStr);
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        });
        instructionsPane.add(instructionsLabel);
        instructionsPane.add(helpButton);
        
        centerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerPane.setOneTouchExpandable(false);
        centerPane.setDividerLocation(250);
        boardPane = new YourBoardPane(this.playersName);
        btnsPane = new JPanel(new BorderLayout());
        startBtn = new JButton("Start game");
        startBtn.setEnabled(false);
        startBtn.addActionListener(new StartGameListener(this, boardPane));
        rotateBtn = new JButton("Rotate ship");
        stbpPane = new ShipsToBePlacedPane(this, boardPane);
        centerPane.add(stbpPane);
        centerPane.add(boardPane);
        btnsPane.add(rotateBtn, BorderLayout.WEST);
        btnsPane.add(startBtn, BorderLayout.EAST);
        add(instructionsPane, BorderLayout.NORTH);//add(instructionsLabel, BorderLayout.NORTH);
        add(centerPane, BorderLayout.CENTER);
        add(btnsPane, BorderLayout.SOUTH);
        this.checkBoard();
    }
    /**
     * This method returns the state of the rotate button
     * @return rotateBtn: the rotate button
     */
    public JButton getRotateBtn() {
        return rotateBtn;
    }
    /**
     * This method returns the state of the start button
     * @return startBtn: the start button
     */
    public JButton getStartBtn() {
        return startBtn;
    }
    /**
     * This method checks if there are enough tiles for each ship to be placed.
     * If something is wrong, then an error frame appears
     */
    public void checkBoard() {
        if (stbpPane.getShipsTiles() > boardPane.getColsBoard() * boardPane.getRowsBoard()) {
            System.out.println("Something is wrong");
            errorFrame = new ErrorFrame("player");
        }
    }

    private JLabel instructionsLabel;

    private ShipsToBePlacedPane stbpPane;

    private JSplitPane centerPane;

    private JPanel btnsPane;

    private JButton rotateBtn;

    private JButton startBtn;

    private YourBoardPane boardPane;

    private String playersName;

    private ErrorFrame errorFrame;
    
    private JPanel instructionsPane;
    
    private JButton helpButton;
    
    private HelpFrame helpPane;
}
