
package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Player;
import fi.yussiv.minigolf.domain.Position;


public class Game {
    private static final double RESISTANCE_COEFFICIENT = 0.8;
    private Player player;
    private LevelArea area;

    public Game(LevelArea area) {
        this.area = area;
    }

    LevelArea getLevelArea() {
        return area;
    }
    
    public void addPlayer(Player player) {
        this.player = player;
        Ball ball = new Ball();
        player.setBall(ball);
        ball.setPlayer(player);
    }
    
    public void startGame() {
        player.getBall().setPosition(area.getStart());
    }
    
    /**
     * 
     * @param velocity
     * @param angle -180 to 180, 0 == up
     */
    public void excecutePutt(double velocity, double angle) {
        Ball ball = player.getBall();
        ball.setVelocity(velocity);
        ball.setAngle(angle);
        int dx = 0, dy = 0;
        while (ball.isMoving() && !targetReached()) {
            int x = ball.getX() + (int) Math.round(ball.getVelocity() * Math.sin(angle * Math.PI / 180));
            int y = ball.getY() + (int) Math.round(ball.getVelocity() * Math.cos(angle * Math.PI / 180));
            
            ball.setVelocity(ball.getVelocity() * RESISTANCE_COEFFICIENT);
            ball.setPosition(new Position(x, y));
        }
    }

    public boolean targetReached() {
        return player.getBall().getPosition().overlaps(area.getTarget(), 5);
    }
}
