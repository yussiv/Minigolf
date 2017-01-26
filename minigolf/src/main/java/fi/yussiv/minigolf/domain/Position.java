
package fi.yussiv.minigolf.domain;

/**
 *
 * @author jkvoipio
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean overlaps(Position other, int margin) {
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
