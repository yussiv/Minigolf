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
    private final int width;
    private final int height;
    private Ball ball;
    private Point mousePoint = new Point(0, 0);
    private final LevelArea level;
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

        int ballX = ball.getPosition().x;
        int ballY = ball.getPosition().y;
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
        g.drawString("mouse:(" + mousePoint.x + "," + mousePoint.y + ")", 510, 20);
        g.drawString("ball:(" + ballX + "," + ballY + ")", 510, 35);
        g.drawString(String.format("angle: %.2f", ball.getAngle()), 510, 50);

        // game is over
        if (!ball.isVisible()) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Victory!", 260, 400);
        }

        // draw ball
        int radius = ball.getRadius();
        if (ball.isVisible() && ballX >= 0 && ballY >= 0) {
            g.fillOval(ballX - radius, ballY - radius, radius * 2, radius * 2); // point is the center
        }

        // if mouse is pressed and dragged
        if (mouseStart != null) {
            g2d.setColor(Color.YELLOW);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawLine(mouseStart.x, mouseStart.y, mouseEnd.x, mouseEnd.y);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            g2d.setColor(Color.WHITE);
            g2d.drawString(String.format("angle:%.1f", Geometry.calculateAngle(mouseStart, mouseEnd)), mouseEnd.x + 10, mouseEnd.y - 10);
            g2d.drawString(String.format("force:%.1f", Math.min(mouseStart.distance(mouseEnd), 100.0)), mouseEnd.x + 10, mouseEnd.y - 25);
        }
    }

    public void setMousePoint(Point point) {
        this.mousePoint = point;
    }

    /**
     * Repaints the panel.
     */
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
