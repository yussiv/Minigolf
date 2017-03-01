package fi.yussiv.minigolf.gui;

import java.awt.Graphics2D;

/**
 * Interface for objects to be drawn on the canvas.
 */
interface Paintable {
    /**
     * Draws the element.
     * @param graphics 
     */
    public void paint(Graphics2D graphics);
}
