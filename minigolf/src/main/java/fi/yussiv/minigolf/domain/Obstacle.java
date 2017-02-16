package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Generic obstacle class.
 */
public interface Obstacle {
    public boolean overlaps(Point point);

    /**
     * Takes the point of impact as an argument for elements in order 
     * to calculate the angle correctly in edge cases (i.e. the ends of walls)
     * 
     * @param hitLocation
     * @return angle of element
     */
    public double getAngle(Point hitLocation);
}
