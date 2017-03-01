package fi.yussiv.minigolf.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * A class to display rectangular objects.
 */
public class Rectangle implements Paintable {

    private Point upLeft;
    private Point upRight;
    private Point bottomRight;
    private Point bottomLeft;
    private static final Color BROWN = new Color(101, 79, 14);

    /**
     * The constructor takes in four corners of the rectangle.
     *
     * @param upLeft 
     * @param upRight 
     * @param bottomRight 
     * @param bottomLeft 
     */
    public Rectangle(Point upLeft, Point upRight, Point bottomRight, Point bottomLeft) {
        this.upLeft = upLeft;
        this.upRight = upRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    /**
     * Sets all four corner point in one fell swoop.
     *
     * @param upLeft 
     * @param upRight 
     * @param bottomRight 
     * @param bottomLeft 
     */
    public void setCorners(Point upLeft, Point upRight, Point bottomRight, Point bottomLeft) {
        this.upLeft = upLeft;
        this.upRight = upRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    /**
     * Draw the rectangle.
     *
     * @param g2d target
     */
    @Override
    public void paint(Graphics2D g2d) {
        int[] x = new int[]{
            upLeft.x,
            upRight.x,
            bottomRight.x,
            bottomLeft.x
        };
        int[] y = new int[]{
            upLeft.y,
            upRight.y,
            bottomRight.y,
            bottomLeft.y
        };
        g2d.setColor(BROWN);
        g2d.setStroke(new BasicStroke(1));
        g2d.fillPolygon(x, y, 4);
    }
}
