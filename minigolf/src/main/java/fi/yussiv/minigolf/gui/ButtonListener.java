
package fi.yussiv.minigolf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class to listen button actions (duh).
 */
public class ButtonListener implements ActionListener {
    private GUI gui;

    public ButtonListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        gui.reset();
    }
    
}
