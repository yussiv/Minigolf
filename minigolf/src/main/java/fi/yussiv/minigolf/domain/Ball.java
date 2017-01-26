package fi.yussiv.minigolf.domain;

public class Ball {
    private int x;
    private int y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }
    
    public boolean isMoving() {
        return Math.abs(velocity) >= 1;
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
}
