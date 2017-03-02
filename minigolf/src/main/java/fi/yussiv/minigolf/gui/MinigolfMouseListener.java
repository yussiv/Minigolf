package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.logic.Geometry;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Mouse listener for the games main input.
 */
public class MinigolfMouseListener implements MouseListener, MouseMotionListener {

    private Canvas canvas;
    private Point start;
    private Point end;
    private GUI gui;

    /**
     * Constructor for listener.
     * 
     * @param gui the GUI to report to
     * @param canvas the canvas being listened to
     */
    public MinigolfMouseListener(GUI gui, Canvas canvas) {
        this.canvas = canvas;
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        // not implemented
    }

    /**
     * When mouse is pressed, the starting point is saved for calculating mouse
     * dragging animation and eventual putt force calculation.
     *
     * @param me
     */
    @Override
    public void mousePressed(MouseEvent me) {
        if (gui.inputNotAllowed()) {
            return;
        }
        this.start = me.getPoint();
        canvas.setDragLineStart(start);
        canvas.setDragLineEnd(start);
    }

    /**
     * When mouse is released, the putt force is calculated and the dragging
     * animation cleared.
     *
     * @param me
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        if (gui.inputNotAllowed()) {
            return;
        }
        end = me.getPoint();
        double angle = Geometry.calculateAngle(start, end);
        double distance = start.distance(end);
        gui.hitBall(distance, angle);
        canvas.clearDragLine();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        // not implemented
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // not implemented
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        canvas.setDragLineEnd(me.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        canvas.setMousePoint(me.getPoint());
    }

}
