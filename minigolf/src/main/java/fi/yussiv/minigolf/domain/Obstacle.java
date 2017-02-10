package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Generic obstacle class.
 */
public interface Obstacle {
    public boolean overlaps(Point point);

    public double getAngle();
}
