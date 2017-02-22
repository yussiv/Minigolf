package fi.yussiv.minigolf.domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Class portraying the current level. Contains information of starting and
 * ending locations and possible obstacles.
 */
public class LevelArea {

    private List<Obstacle> obstacles;
    private Point start;
    private Point target;
    private int width;
    private int height;

    /**
     * The constructor.
     * 
     * @param width 
     * @param height 
     */
    public LevelArea(int width, int height) {
        this.obstacles = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Adds an obstacle to level.
     * 
     * @param obstacle to add
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    public Point getStart() {
        return start;
    }

    public Point getTarget() {
        return target;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
