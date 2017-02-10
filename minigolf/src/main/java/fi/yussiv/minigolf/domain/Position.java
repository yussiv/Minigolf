package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Coordinate class using floating point values.
 */
public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Method to determine if position overlaps a point within given margin.
     * 
     * @param point the possible overlapping point
     * @param margin how far from the point can be to still be considered overlapping
     */
    public boolean overlaps(Point point, int margin) {
        return Math.abs(x - point.x) < margin && Math.abs(y - point.y) < margin;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(Position.class)) {
            return false;
        }

        Position other = (Position) obj;
        return other.x == x && other.y == y;
    }

}
