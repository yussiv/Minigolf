package fi.yussiv.minigolf.logic;

import java.awt.Point;

/**
 * Utility class containing angle calculations for collision handling.
 */
public class Geometry {

    /**
     * Calculate the angle of the line created by two end points.
     *
     * @param p1 starting point
     * @param p2 ending point
     * @return angle of line
     */
    public static double calculateAngle(Point p1, Point p2) {
        int vertical = p2.y - p1.y;
        int horizontal = p2.x - p1.x;

        double angle = Math.toDegrees(Math.atan2(Math.abs(horizontal), Math.abs(vertical)));

        if (horizontal <= 0 && vertical >= 0) {
            angle = -angle;
        } else if (horizontal < 0 && vertical < 0) {
            angle = angle - 180;
        } else if (horizontal >= 0 && vertical <= 0) {
            angle = 180 - angle;
        }

        return angle;
    }

    /**
     * Calculate how the angle of an object changes when colliding with another.
     *
     * @param incomingAngle 
     * @param objectAngle 
     * @return angle of ricochet
     */
    public static double calculateRicochetAngle(double incomingAngle, double objectAngle) {
        // calculate new angle difference by setting object angle to zero
        double normalizedIncomingAngle = incomingAngle - objectAngle;

        double ret = objectAngle - normalizedIncomingAngle;
        if (ret > 180) {
            ret -= 360;
        } else if (ret <= -180) {
            ret += 360;
        }
        return ret;
    }

    /**
     * Calculates a point at a specified distance and angle from the origin
     * point.
     *
     * @param origin 
     * @param angle 
     * @param distance 
     * @return point offset by parameters
     */
    public static Point getOffsetPoint(Point origin, double angle, int distance) {
        angle = normalizeAngle(angle);

        return new Point((int) Math.round(Math.sin(Math.toRadians(angle)) * distance) + origin.x, (int) Math.round(Math.cos(Math.toRadians(angle)) * distance) + origin.y);
    }

    /**
     * Normalizes given angle to be in between -180 to 180 degrees.
     *
     * @param angle the angle to be translated
     * @return angle between -180 to 180 degrees
     */
    public static double normalizeAngle(double angle) {
        while (angle > 180) {
            angle -= 360;
        }
        while (angle <= -180) {
            angle += 360;
        }
        return angle;
    }
}
