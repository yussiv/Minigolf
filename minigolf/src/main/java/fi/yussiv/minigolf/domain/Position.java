
package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Coordinate class using floating point values
 * 
 * @author jkvoipio
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

    public boolean overlaps(Point other, int margin) {
        return Math.abs(x - other.x) < margin && Math.abs(y - other.y) < margin;
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
