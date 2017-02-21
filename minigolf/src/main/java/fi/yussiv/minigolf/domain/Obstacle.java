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
     * @param margin
     * @return 
     */
    public boolean overlaps(Point point, int margin);

    /**
     * The angle of the obstacle may differ depending on point of impact,
     * therefore we pass in information about the ball's position and angle.
     *
     * @param inboundAngle angle in which the ball is moving
     * @param hitPosition where the collision happens
     * @param margin distance from hitPosition to include in calculation
     * @return angle of wall at given point
     */
    public int getAngle(double inboundAngle, Point hitPosition, int margin);
}
