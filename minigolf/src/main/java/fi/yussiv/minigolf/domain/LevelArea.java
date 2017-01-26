package fi.yussiv.minigolf.domain;

import java.util.ArrayList;
import java.util.List;

public class LevelArea {

    private List<Obstacle> obstacles;
    private Position start;
    private Position target;
    private int width;
    private int height;

    public LevelArea(int width, int height) {
        this.obstacles = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    public void addObstacle(Obstacle o) {
        obstacles.add(o);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public Position getStart() {
        return start;
    }

    public Position getTarget() {
        return target;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
