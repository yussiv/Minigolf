package fi.yussiv.minigolf.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author jkvoipio
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
        start = me.getPoint();
        System.out.println("mouse pressed!");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        end = me.getPoint();
        double angle;
        int vertical = start.y - end.y;
        int horizontal = start.x - end.x;
        if(horizontal == 0)
            angle = 0;
        else
            angle = Math.tan(Math.abs(vertical)/horizontal);
        
        if(vertical > 0)
            angle += 180;
        
        double distance = start.distance(end);
        System.out.println("dis:"+distance+" ang:"+angle);
        gui.getGame().excecutePutt(distance, angle);//executeCommand(distance, angle);
        System.out.println("mouse listened!");
    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void mouseDragged(MouseEvent me) {
        canvas.setMouseCoords("drag: (" + me.getX() + "," + me.getY() + ")");
//        canvas.refresh();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        canvas.setMouseCoords("move: (" + me.getX() + "," + me.getY() + ")");
    }

}
