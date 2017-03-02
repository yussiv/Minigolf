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

    public static final double ROLLING_RESISTANCE_COEFF = 0.995;
    public static final double FORCE_SCALE_COEFF = 0.05;
    private static final double MAX_VELOCITY = 100;
    private Ball ball;
    private LevelArea levelArea;
    private boolean gameIsOver = false;

    /**
     * Constructor including a couple of walls.
     */
    public Game() {
        this.levelArea = new LevelArea(600, 800);
        this.levelArea.addObstacle(new Wall(new Point(100, 50), 90, 16, 408));
        this.levelArea.addObstacle(new Wall(new Point(108, 50), 0, 16, 600));
        this.levelArea.addObstacle(new Wall(new Point(500, 50), 0, 16, 600));
        this.levelArea.addObstacle(new Wall(new Point(110, 500), 90, 16, 158));
        this.levelArea.addObstacle(new Wall(new Point(236, 140), 45, 16, 100));
        this.levelArea.addObstacle(new Wall(new Point(296, 211), 135, 16, 100));
        this.levelArea.addObstacle(new Wall(new Point(361, 360), 60, 16, 158));

        this.levelArea.setStart(new Point(300, 650));
        this.levelArea.setTarget(new Point(300, 150));

        this.ball = new Ball();
        initializeLevel();
    }

    public LevelArea getLevelArea() {
        return levelArea;
    }

    /**
     * Sets beginning attributes for ball and game status.
     */
    public void initializeLevel() {
        ball.setPosition(levelArea.getStart());
        ball.setVisible(true);
        ball.setAngle(0);
        ball.setVelocity(0);
        gameIsOver = false;
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
        ball.setVelocity(FORCE_SCALE_COEFF * velocity);
        ball.setAngle(angle + 180);
    }

    /**
     * Moves ball and checks for collisions and whether the game is over.
     */
    public void simulateRound() {
        if (ball.isMoving() && !gameIsOver) {
            double dx = 0, dy = 0, x, y;
            dx = ball.getVelocity() * Math.sin(Math.toRadians(ball.getAngle()));
            dy = ball.getVelocity() * Math.cos(Math.toRadians(ball.getAngle()));
            x = ball.getX() + dx;
            y = ball.getY() + dy;

            // simulate rolling resistance (=slow ball down)
            ball.setVelocity(ball.getVelocity() * ROLLING_RESISTANCE_COEFF);
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
        if (ball.getX() > levelArea.getWidth() - ball.getRadius() || ball.getX() < ball.getRadius()) {
            ball.setAngle(Geometry.calculateRicochetAngle(ball.getAngle(), 0));
        }

        // hit left or right edge
        if (ball.getY() < ball.getRadius() || ball.getY() > levelArea.getHeight() - ball.getRadius()) {
            ball.setAngle(Geometry.calculateRicochetAngle(ball.getAngle(), 90));
        }

        // hit an obstacle
        for (Obstacle o : levelArea.getObstacles()) {
            if (o.overlaps(ball.getPosition(), ball.getRadius() + 1)) {
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
        gameIsOver = ball.getPosition().distance(levelArea.getTarget()) < 8;
        if (gameIsOver) {
            ball.setVisible(false);
        }
    }

    /**
     * Truth value of game state.
     * @return true if game is over
     */
    public boolean gameIsOver() {
        return gameIsOver;
    }

    public Ball getBall() {
        return ball;
    }
}
