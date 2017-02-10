package fi.yussiv.minigolf.logic;

import java.awt.Point;

/**
 * Utility class containing angle calculations for collision handling.
 */
public class Geometry {

    public static double calculateAngle(Point p1, Point p2) {
        int vertical = p2.y - p1.y;
        int horizontal = p2.x - p1.x;

        if (horizontal == 0) {
            return vertical < 0 ? 180 : 0;
        }
        double angle = Math.toDegrees(Math.atan2(Math.abs(horizontal), Math.abs(vertical)));

        if (vertical < 0) {
            angle += 90;
        } 

        if (horizontal > 0) {
            angle *= -1.0;
        }

        return angle;
    }
}
