
package fi.yussiv.minigolf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Heptaurus
 */
public class GameRunner extends Timer implements ActionListener {
    
    private GUI gui;
    
    public GameRunner(int interval, GUI gui) {
        super(interval, null);
        this.gui = gui;
    }

    
    /**
     * Method to process changes in the GUI. Triggered by the timer at even
     * intervals (15ms). Calculates the movement of the ball during the
     * interval.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        gui.animate();
    }
    
}
