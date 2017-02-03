package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.domain.Ball;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author jkvoipio
 */
public class Canvas extends JPanel {

    private int width;
    private int height;
    private Ball ball;
    private String mouseCoords = "";
    private Point target;

    public Canvas(int width, int height, Ball ball, Point target) {
        this.width = width;
        this.height = height;
        this.ball = ball;
        this.target = target;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(62, 142, 42));
        g.fillRect(0, 0, width, height);

        int ballX = (int) ball.getX();
        int ballY = (int) ball.getY();

        g.setColor(Color.BLACK);
        g.fillOval(target.x - 9, height - target.y - 9, 19, 19); // point is the center

        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString(mouseCoords, 480, 20);

        if (ballX >= 0 && ballY >= 0) {
            g.fillOval(ballX - 6, height - ballY - 6, 13, 13); // point is the center
        } else {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Victory!", 280, 400);
        }
    }

    public void setMouseCoords(String mouseCoords) {
        this.mouseCoords = mouseCoords;
    }

    public void refresh() {
        super.repaint();
//            System.out.println("called canvas");
    }
}
