package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Extension of java.awt.Point that retains floating point values unchanged.
 * The original saves the values as integers, which leads to unwanted
 * flooring when ball movement is slow.
 */
public class Position extends Point {

    private double floatX;
    private double floatY;

    public Position(int x, int y) {
        super(x, y);
        this.floatX = (double) x;
        this.floatY = (double) y;
    }

    @Override
    public void setLocation(double x, double y) {
        super.setLocation(x, y);
        floatX = x;
        floatY = y;
    }

    @Override
    public void setLocation(Point point) {
        super.setLocation(point);
        floatX = (double) point.x;
        floatY = (double) point.y;
    }

    @Override
    public double getY() {
        return floatY;
    }

    @Override
    public double getX() {
        return floatX;
    }

}
