package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Generic obstacle class.
 */
public interface Obstacle {
    /**
     * Determines if an obstacle overlaps a given point.
     * 
     * @param point
     * @param margin distance from the point that should be considered a hit
     * @return 
     */
    public boolean overlaps(Point point, int margin);

    /**
     * The angle of the obstacle may differ depending on point of impact, therefore 
     * we pass in information about the ball.
     * 
     * @param ball
     * @return angle of element
     */
    public double getAngle(Ball ball);
}
