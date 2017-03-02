package fi.yussiv.minigolf;

import fi.yussiv.minigolf.gui.GUI;
import javax.swing.SwingUtilities;

/**
 * Main class. Starts a game and binds the GUI to it.
 */
public class Minigolf {

    /**
     * Program main method. Pretty essential stuff.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Game game = new Game();
        
        GUI gui = new GUI(game, 5);
        SwingUtilities.invokeLater(gui);
    }
}
