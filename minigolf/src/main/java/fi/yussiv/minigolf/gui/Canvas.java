package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Obstacle;
import fi.yussiv.minigolf.domain.Wall;
import fi.yussiv.minigolf.logic.Geometry;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * The Graphical area representing the playing area.
 */
public class Canvas extends JPanel {

    private static final Color GREEN = new Color(62, 142, 42);
    private static final Color BROWN = new Color(101, 79, 14);
    private int width;
    private int height;
    private Ball ball;
    private Point mousePoint = new Point(0, 0);
    private LevelArea level;
    private Point mouseStart;
    private Point mouseEnd;

    public Canvas(int width, int height, Ball ball, LevelArea level) {
        this.width = width;
        this.height = height;
        this.ball = ball;
        this.level = level;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(GREEN);
        g.fillRect(0, 0, width, height);

        int ballX = (int) ball.getX();
        int ballY = (int) ball.getY();
        Point target = level.getTarget();

        g.setColor(Color.BLACK);
        g.fillOval(target.x - 9, target.y - 9, 19, 19); // point is the center

        Graphics2D g2d = (Graphics2D) g;

        for (Obstacle o : level.getObstacles()) {
            if (o.getClass() == Wall.class) {
                Wall w = (Wall) o;
                g2d.setColor(BROWN);
                g2d.setStroke(new BasicStroke(w.getWidth()));
                g2d.drawLine(w.getStart().x, w.getStart().y, w.getEnd().x, w.getEnd().y);
            }
        }

        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("mouse:(" + mousePoint.x + "," + mousePoint.y + ")", 480, 20);
        g.drawString("ball:(" + ballX + "," + ballY + ")", 480, 35);
        g.drawString(String.format("angle: %.2f", ball.getAngle()), 480, 50);

        // draw ball
        if (ball.isVisible() && ballX >= 0 && ballY >= 0) {
            g.fillOval(ballX - 6, ballY - 6, 13, 13); // point is the center
        }

        // game is over
        if (!ball.isVisible()) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Victory!", 260, 400);
        }

        // if mouse is pressed and dragged
        if (mouseStart != null) {
            g2d.setColor(Color.YELLOW);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawLine(mouseStart.x, mouseStart.y, mouseEnd.x, mouseEnd.y);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            g2d.setColor(Color.WHITE);
            g2d.drawString(String.format("angle:%.1f",Geometry.calculateAngle(mouseStart, mouseEnd)), mouseEnd.x + 10, mouseEnd.y - 10);
        }
    }

    public void setMousePoint(Point point) {
        this.mousePoint = point;
    }

    public void refresh() {
        super.repaint();
    }

    void setDragLineStart(Point start) {
        mouseStart = start;
    }

    void setDragLineEnd(Point end) {
        mouseEnd = end;
    }

    void clearDragLine() {
        mouseStart = null;
    }
}
