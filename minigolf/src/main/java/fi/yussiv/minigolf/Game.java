package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Obstacle;
import fi.yussiv.minigolf.domain.Wall;
import fi.yussiv.minigolf.logic.Geometry;
import java.awt.Point;

/**
 * The game's main class.
 */
public class Game {

    private static final double RESISTANCE_COEFFICIENT = 0.99;
    private static final double MAX_VELOCITY = 100;
    private Ball ball;
    private LevelArea area;
    private boolean gameOver = false;

    /**
     * Constructor including a couple of walls.
     */
    public Game() {
        this.area = new LevelArea(600, 800);
        this.area.addObstacle(new Wall(new Point(100, 50), 90, 10, 400));
        this.area.addObstacle(new Wall(new Point(100, 50), 0, 10, 600));
        this.area.addObstacle(new Wall(new Point(500, 50), 0, 10, 600));
        this.area.addObstacle(new Wall(new Point(110, 500), 90, 20, 150));
        this.area.addObstacle(new Wall(new Point(230, 140), 45, 10, 100));
        this.area.addObstacle(new Wall(new Point(303, 211), 135, 10, 100));
        this.area.addObstacle(new Wall(new Point(361, 360), 60, 20, 150));

        this.area.setStart(new Point(300, 650));
        this.area.setTarget(new Point(300, 150));

        this.ball = new Ball();
        initializeLevel();
    }

    public LevelArea getLevelArea() {
        return area;
    }

    public void initializeLevel() {
        ball.setPosition(area.getStart());
        ball.setVisible(true);
        ball.setAngle(0);
        ball.setVelocity(0);
        gameOver = false;
    }

    /**
     * Sets the balls movement parameters. Used to simulate hitting the ball.
     *
     * @param velocity between 0 and MAX_VELOCITY
     * @param angle -180 to 180 degrees. 0 degrees means straight up.
     */
    public void setBallMovement(double velocity, double angle) {
        if (velocity > MAX_VELOCITY) {
            velocity = MAX_VELOCITY;
        }
        ball.setVelocity(velocity / 5);
        ball.setAngle(angle + 180);
    }

    public void simulateRound() {
        if (ball.isMoving() && !gameOver) {
            double dx = 0, dy = 0, x, y;
            dx = ball.getVelocity() * Math.sin(Math.toRadians(ball.getAngle()));
            dy = ball.getVelocity() * Math.cos(Math.toRadians(ball.getAngle()));
            x = ball.getX() + dx;
            y = ball.getY() + dy;

            // simulate rolling resistance (=slow ball down)
            ball.setVelocity(ball.getVelocity() * RESISTANCE_COEFFICIENT);
            ball.setPosition(x, y);

            evaluateGameState();
        }
    }

    /**
     * Runs subroutines that check and handle changes in the game state.
     */
    public void evaluateGameState() {
        maybeCollision();
        maybeReachedTarget();
    }

    /**
     * Checks whether the ball has hit something.
     */
    public void maybeCollision() {
        // hit top or bottom
        if (ball.getX() > area.getWidth() - 5 || ball.getX() < 5) {
            ball.setAngle(Geometry.calculateRicochetAngle(ball.getAngle(), 0));
        }

        // hit left or right edge
        if (ball.getY() < 5 || ball.getY() > area.getHeight() - 5) {
            ball.setAngle(Geometry.calculateRicochetAngle(ball.getAngle(), 90));
        }

        // hit an obstacle
        for (Obstacle o : area.getObstacles()) {
            if (o.overlaps(ball.getPosition(), ball.getRadius())) {
                double obstacleAngle = o.getAngle(ball.getAngle(), ball.getPosition(), ball.getRadius());
                double newBallAngle = Geometry.calculateRicochetAngle(ball.getAngle(), obstacleAngle);

                ball.setAngle(newBallAngle);
            }
        }
    }

    /**
     * Check if ball is over the target location. Sets boolean indicating game
     * is over and if so, sets a flag that the ball should not be drawn by the
     * canvas.
     */
    private void maybeReachedTarget() {
        gameOver = ball.getPosition().distance(area.getTarget()) < 8;
        if (gameOver) {
            ball.setVisible(false);
        }
    }

    public boolean gameIsOver() {
        return gameOver;
    }

    public Ball getBall() {
        return ball;
    }
}
