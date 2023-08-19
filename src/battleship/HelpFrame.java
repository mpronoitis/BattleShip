/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class is a JFrame, that shows up when the help button is clicked
 * @author mpronoitis
 */
public class HelpFrame extends JFrame{
    /**
     * Constructor of HelpFrame
     * @param helpStr: the text of the user manual
     */
    public HelpFrame(String helpStr){
        textArea =new JTextArea(helpStr);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize( new Dimension( 600, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane);
    }
    private JScrollPane scrollPane;
    private JTextArea textArea;
}
