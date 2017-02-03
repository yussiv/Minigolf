package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.domain.Ball;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author jkvoipio
 */
public class Canvas extends JPanel {
    private int width;
    private int height;
    private Ball ball;
    private String mouseCoords="";

    public Canvas(int width, int height, Ball ball) {
        this.width = width;
        this.height = height;
        this.ball = ball;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(62,142,42));
        g.fillRect(0,0,width,height);
        g.setColor(Color.WHITE);
        g.fillOval((int)ball.getX(), height-(int)ball.getY(), 10, 10);
        g.drawString(mouseCoords, 480, 20);
    }

    public void setMouseCoords(String mouseCoords) {
        this.mouseCoords = mouseCoords;
    }

    public void refresh() {
        super.repaint();
//            System.out.println("called canvas");
    }
}
