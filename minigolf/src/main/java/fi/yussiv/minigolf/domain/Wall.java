package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Class representing a straight wall
 */
public class Wall implements Obstacle {

    private final Point start;
    private final Point end;
    private final double angle;
    private final int width;
    private final int length;

    public Wall(Point start, double angle, int width, int length) {
        this.start = start;
        this.angle = angle;
        this.width = width;
        this.length = length;
        this.end = calculateEnd();
    }

    @Override
    public boolean overlaps(Point point, int margin) {

        // handle zero and 90 degree angles
        if (Math.abs(angle) < 0.1) {
            // vertical wall
            return point.y > start.y - width / 2 - margin && point.y < end.y + width / 2 + margin && Math.abs(point.x - start.x) < width / 2 + margin;
        } else if (Math.abs(angle - 90) < 0.1) {
            // horizontal wall
            return point.x > start.x - width / 2 - margin && point.x < end.x + width / 2 + margin && Math.abs(point.y - start.y) < width / 2 + margin;
        }

        return false;
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

    @Override
    public double getAngle(Ball ball) {
        
        // hit start 
        if (ball.getPosition().distance(start) < width / 2 + ball.getRadius()) {
            if ((Math.abs(angle % 180) < 0.01 && Math.abs(ball.getAngle()) < 90) 
                    || (Math.abs(angle) - 90 < 0.01 && ball.getAngle() > 0)){
                return (this.angle + 90) % 90;
            }
        }

        // hit end
        if (ball.getPosition().distance(end) < width / 2 + ball.getRadius()) {
            if ((Math.abs(angle) - 90 < 0.01 && Math.abs(ball.getAngle()) > 90) 
                    || (Math.abs(angle) - 90 < 0.01 && ball.getAngle() < 0)) {
                return (this.angle + 90) % 90;
            }
        }

        return this.angle;
    }
}
