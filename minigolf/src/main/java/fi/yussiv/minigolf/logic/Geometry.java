package fi.yussiv.minigolf.logic;

import java.awt.Point;

/**
 * Utility class containing angle calculations for collision handling.
 */
public class Geometry {

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

    public static double calculateRicochetAngle(double incomingAngle, double objectAngle) {
//        double ret;

        // perpendicular hit
//        if (Math.abs(Math.abs(incomingAngle - objectAngle) - 90) < 0.01) {
//            ret = incomingAngle + 180;
//            if (ret > 180) {
//                ret -= 360;
//            }
//            return ret;
//        }
        // calculate new angle difference by setting object angle to zero
        double normalizedIncomingAngle = incomingAngle - objectAngle;

        double ret = objectAngle - normalizedIncomingAngle;
        if (ret > 180) {
            ret -= 360;
        } else if (ret <= -180) {
            ret += 360;
        }
        return ret;
//        }

//        return 0.0;
    }
}
