package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Class representing a straight wall.
 */
public class Wall implements Obstacle {

    private final Point start;
    private final Point end;
    private final int angle;
    private final int width;
    private final int length;
    private final int angledDistance; // helper value for collision detection

    /**
     * Constructor also initializes helper fields for collision detection.
     * 
     * @param start 
     * @param angle 
     * @param width 
     * @param length 
     */
    public Wall(Point start, int angle, int width, int length) {
        this.start = start;
        this.angle = angle;
        this.width = width;
        this.length = length;
        this.end = calculateEnd();
        this.angledDistance = calculateAngledDistance();
    }

    @Override
    public boolean overlaps(Point point, int margin) {

        switch (angle) {
            case 0:
                // vertical wall
                return point.y > start.y - width / 2 - margin && point.y < end.y + width / 2 + margin && Math.abs(point.x - start.x) < width / 2 + margin;
            case 90:
                // horizontal wall
                return point.x > start.x - width / 2 - margin && point.x < end.x + width / 2 + margin && Math.abs(point.y - start.y) < width / 2 + margin;
            default:
                // all other angles
                double distance = point.distance(calculateWallPoint(point));
                return distance < angledDistance + margin;
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

    /**
     * Wall endings have a 90 degree change in angle.
     *
     * @param ball
     * @return
     */
    @Override
    public int getAngle(double ballAngle, Point position, int margin) {

        // hit start 
        if (position.distance(start) < width / 2 + margin) {
            if ((Math.abs(angle % 180) == 0 && Math.abs(ballAngle) < 90)
                    || (Math.abs(angle) == 90 && ballAngle > 0)) {
                return this.angle + 90;
            }
        }

        // hit end
        if (position.distance(end) < width / 2 + margin) {
            if ((Math.abs(angle) == 90 && Math.abs(ballAngle) > 90)
                    || (Math.abs(angle) == 90 && ballAngle < 0)) {
                return this.angle + 90;
            }
        }

        return this.angle;
    }

    /**
     * Calculates point distance from the center of the wall. Assumes that wall
     * angle is positive and under 180 degrees.
     *
     * @return
     */
    private Point calculateWallPoint(Point point) {
        if (point.x < start.x) {
            return start;
        }

        if (point.x > end.x) {
            return end;
        }

        int y;
        int dx = Math.abs(start.x - point.x);

        if (angle < 90) {
            // 0 < x < 90
            y = start.y + (int) Math.round(1.0 * dx * Math.tan(Math.toRadians(90 - angle)));
        } else {
            // 90 < x < 180
            y = start.y - (int) Math.round(1.0 * dx / Math.tan(Math.toRadians(180 - angle)));
        }
        return new Point(point.x, y);
    }

    /**
     * Calculates the distance from the edge of the wall to the center in a
     * horizontal direction.
     *
     * @return
     */
    private int calculateAngledDistance() {
        if (angle < 90) {
            // 0 < x < 90
            return (int) Math.round(0.5 * width / Math.sin(Math.toRadians(angle)));
        } else {
            // 90 < x < 180
            return (int) Math.round(0.5 * width / Math.cos(Math.toRadians(180 - angle)));
        }
    }
}
