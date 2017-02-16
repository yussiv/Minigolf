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

    public MinigolfMouseListener(GUI gui) {
        this.canvas = gui.getCanvas();
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        this.start = me.getPoint();
        canvas.setDragLineStart(start);
        canvas.setDragLineEnd(start);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        end = me.getPoint();
        double angle = Geometry.calculateAngle(start, end);
        double distance = start.distance(end);
        gui.getGame().excecutePutt(distance, angle);
        System.out.printf("Putt: start:(%d,%d) end:(%d,%d) angle: %.2f force: %.2f", start.x, start.y, end.x, end.y, angle, distance);
        canvas.clearDragLine();
    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

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
