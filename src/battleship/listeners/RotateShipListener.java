package battleship.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
/**
 * This class is an ActionListener, which is activated when the rotate button is pressed
 * @author mpronoitis
 */
public class RotateShipListener implements ActionListener {
    /**
     * This method returns the status of the rotate button
     * @return rotation: status of the rotate button
     */
    public String getRotation() {
        return rotation;
    }
    /**
     * This method is activated when the rotate button is pressed and converts the status of the button
     * @param e: the event that activated the listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        rotateBtn = (JButton) e.getSource();
        if (rotation.equals("horizontal"))
            rotation = "vertical";
        else
            rotation = "horizontal";
    }

    private JButton rotateBtn;

    private String rotation = "horizontal";
}
