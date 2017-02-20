package fi.yussiv.minigolf.domain;

import java.awt.Point;

/**
 * Class containing information of the players golf ball location and movement.
 */
public class Ball {

    private double velocity;
    private double angle;
    private boolean isVisible = true;
    private Position position;
    private int radius = 6;

    /**
     * The constructor. Default location is set to (0,0).
     */
    public Ball() {
        this.position = new Position(0, 0);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setPosition(double x, double y) {
        this.position.setLocation(x, y);
    }

    public void setPosition(Point p) {
        this.position = new Position(p.x, p.y);
    }

    public Point getPosition() {
        return position;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    /**
     * Method to determine if the ball is still moving
     *
     * @return boolean
     */
    public boolean isMoving() {
        return Math.abs(velocity) > 0.3;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getAngle() {
        return angle;
    }

    /**
     * Sets the ball's movement angle. The angle is normalized between -180 and
     * 180 degrees.
     *
     * @param angle in degrees
     */
    public void setAngle(double angle) {
        while (angle > 180) {
            angle -= 360;
        }
        while (angle <= -180) {
            angle += 360;
        }
        this.angle = angle;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f) velocity:%.3f, angle:%.1f", position.x, position.y, velocity, angle);
    }
}
