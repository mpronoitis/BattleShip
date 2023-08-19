package battleship;
/**
 * This class is our main class
 * @author mpronoitis
 */
public class BattleShip {

    public static void main(String[] args) {
        PlayersDetailsFrame pdFrame = new PlayersDetailsFrame();
        while ((pdFrame.getName()).equals("")) pdFrame = new PlayersDetailsFrame();
        //System.out.println("Name: " + pdFrame.getName());
        ShipsPlacementFrame spFrame = new ShipsPlacementFrame(pdFrame.getName());
        spFrame.setVisible(true);
    }
}
