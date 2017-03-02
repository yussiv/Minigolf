package fi.yussiv.minigolf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 * A class to listen button actions (duh).
 */
public class ButtonListener implements ActionListener {

    private GUI gui;

    /**
     * The constructor.
     * 
     * @param gui the GUI instance it is supposed to provide input for.
     */
    public ButtonListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "reset":
                gui.reset();
                break;
            case "disable":
                JCheckBox box = (JCheckBox) ae.getSource();
                if (box.isSelected()) {
                    gui.setEnforceOneHitAtATime(false);
                } else {
                    gui.setEnforceOneHitAtATime(true);
                }
        }
        
    }

}
