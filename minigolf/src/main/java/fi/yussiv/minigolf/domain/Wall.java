package fi.yussiv.minigolf.domain;

import fi.yussiv.minigolf.logic.Geometry;
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
    private int latestPerceivedAngle; // collision angle is saved on calculation

    /**
     * Constructor also initializes helper fields for collision detection.
     *
     * @param start starting point
     * @param angle main angle of wall
     * @param width 
     * @param length 
     */
    public Wall(Point start, int angle, int width, int length) {
        this.start = start;
        this.angle = angle;
        this.width = width;
        this.length = length;
        this.end = calculateEnd();
        this.latestPerceivedAngle = angle;
    }

    @Override
    public boolean overlaps(Point point, int margin) {
        return distanceToWallEdge(point) <= margin;
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

    public int getAngle() {
        return angle;
    }
    
    /**
     * Wall endings have a 90 degree change in angle.
     */
    @Override
    public int getAngle(double ballAngle, Point position, int margin) {
        // the angle is determined during the overlap calculations because it would contain identical conditionals
        return latestPerceivedAngle;
    }

    /**
     * Calculate virtual dimension of wall and return the distance of the point
     * to the edge of the wall
     *
     * @param point the distance of which to calculate
     * @return distance
     */
    private double distanceToWallEdge(Point point) {
        double angleFromStartToPoint = Geometry.calculateAngle(start, point);
        double angleFromEndToPoint = Geometry.calculateAngle(point, end);

        // normalize all angles relative to zero degree wall angle
        angleFromStartToPoint = Geometry.normalizeAngle(angleFromStartToPoint - angle);
        angleFromEndToPoint = Geometry.normalizeAngle(angleFromEndToPoint - angle);
        double angleFromPointToStart = Geometry.normalizeAngle(180 + angleFromStartToPoint);
        double angleFromPointToEnd = Geometry.normalizeAngle(180 + angleFromEndToPoint);

        double distanceToStart = start.distance(point);
        double distanceToEnd = end.distance(point);

        if (Math.abs(angleFromStartToPoint) <= 90 && Math.abs(angleFromEndToPoint) <= 90) {
            // between start and end points i.e. side hit
            latestPerceivedAngle = angle;
            return Math.sin(Math.toRadians(Math.abs(angleFromStartToPoint))) * distanceToStart - width / 2;
        } else if (Math.abs(angleFromPointToStart) <= 90) {
            // hit start
            latestPerceivedAngle = (int) Geometry.normalizeAngle(angle + 90);
            double d = Math.cos(Math.toRadians(Math.abs(angleFromPointToStart))) * distanceToStart;
            double w = Math.sin(Math.toRadians(Math.abs(angleFromPointToStart))) * distanceToStart;

            // the distance is valid only for the width of the wall
            if (w <= width / 2) {
                return d;
            }
        } else if (Math.abs(angleFromPointToEnd) <= 90) {
            // hit end
            latestPerceivedAngle = (int) Geometry.normalizeAngle(angle + 90);
            double d = Math.cos(Math.toRadians(Math.abs(angleFromPointToEnd))) * distanceToEnd;
            double w = Math.sin(Math.toRadians(Math.abs(angleFromPointToEnd))) * distanceToEnd;

            if (w <= width / 2) {
                return d;
            }
        }
        // infinity
        return Double.MAX_VALUE;
    }
}
