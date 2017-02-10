package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Class representing a straight wall
 */
public class Wall implements Obstacle {

    private Point start;
    private Point end;
    private double angle;
    private int width;
    private int length;

    public Wall(Point start, double angle, int width, int length) {
        this.start = start;
        this.angle = angle;
        this.width = width;
        this.length = length;
        this.end = calculateEnd();
    }

    @Override
    public boolean overlaps(Point point) {
        if (Math.abs(angle) < 0.1) {
            return point.y < start.y + length && Math.abs(point.x - start.x) < width;
        } else if (Math.abs(angle - 180) < 0.1) {
            return point.y > start.y - length && Math.abs(point.x - start.x) < width;
        } else if (Math.abs(angle - 90) < 0.1) {
            return point.x < start.x + length && Math.abs(point.y - start.y) < width;
        } else {
            return false;
        }
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    private Point calculateEnd() {
        int x = (int) Math.round(start.x + Math.sin(Math.toRadians(angle)) * length);
        int y = (int) Math.round(start.y + Math.cos(Math.toRadians(angle)) * length);

        return new Point(x, y);
    }

    public int getWidth() {
        return width;
    }

    @Override
    public double getAngle() {
        return angle;
    }
}
