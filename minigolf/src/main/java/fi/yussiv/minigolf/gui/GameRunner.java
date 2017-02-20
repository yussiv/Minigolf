package fi.yussiv.minigolf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * A class that is responsible for running the animation loop.
 */
public class GameRunner extends Timer implements ActionListener {

    private GUI gui;

    /**
     * @param interval refresh rate in milliseconds
     * @param gui
     */
    public GameRunner(int interval, GUI gui) {
        super(interval, null);
        this.gui = gui;
    }

    /**
     * Calls the GUI to refresh the game state. 
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        gui.animate();
    }

}
