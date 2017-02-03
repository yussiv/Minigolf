package fi.yussiv.minigolf.domain;

public class Ball {
    private double x;
    private double y;
    private double velocity;
    private double angle;
    private Player player;

    public Ball() {
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPosition(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }
    
    public boolean isMoving() {
        return Math.abs(velocity) >= 0.3;
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

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f) velocity:%.3f, angle:%.1f", x,y,velocity,angle);
    }
}
