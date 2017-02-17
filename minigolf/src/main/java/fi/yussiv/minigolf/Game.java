package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Obstacle;
import fi.yussiv.minigolf.domain.Player;
import fi.yussiv.minigolf.domain.Wall;
import fi.yussiv.minigolf.gui.GUI;
import fi.yussiv.minigolf.logic.Geometry;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * The game's main class.
 */
public class Game extends Timer implements ActionListener {

    private static final double RESISTANCE_COEFFICIENT = 0.99;
    private static final double MAX_FORCE = 100;
    private Player player;
    private LevelArea area;
    private GUI gui;
    private boolean gameOver = false;

    public Game() {
        super(15, null);
        this.area = new LevelArea(600, 800);
        this.area.addObstacle(new Wall(new Point(100, 50), 90, 10, 400));
        this.area.addObstacle(new Wall(new Point(100, 50), 0, 10, 600));
        this.area.addObstacle(new Wall(new Point(500, 50), 0, 10, 600));
        this.area.addObstacle(new Wall(new Point(250, 150), 90, 20, 100));

        this.area.setStart(new Point(300, 650));
        this.area.setTarget(new Point(300, 100));

        this.player = new Player("Player 1");
        this.player.setBall(new Ball());
    }

    public LevelArea getLevelArea() {
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
        gui = new GUI(this);
        SwingUtilities.invokeLater(gui);
        addActionListener(this);
        start();
    }

    /**
     * Sets the balls movement parameters for the next hit, effectively putting
     * the ball in motion.
     *
     * @param force between 0 and 100
     * @param angle -180 to 180 degrees. 0 degrees means straight up.
     */
    public void excecutePutt(double force, double angle) {
        Ball ball = player.getBall();
        if (force > MAX_FORCE) {
            force = MAX_FORCE;
        }
        ball.setVelocity(force / 5);
        ball.setAngle(angle + 180);
    }

    /**
     * Checks whether the ball has hit something
     *
     * @param ball
     */
    public void maybeCollision() {

        Ball ball = player.getBall();

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
                double obstacleAngle = o.getAngle(ball);
                double newBallAngle = Geometry.calculateRicochetAngle(ball.getAngle(), obstacleAngle);

                ball.setAngle(newBallAngle);
            }
        }
    }

    private void maybeReachedTarget() {
        gameOver = player.getBall().getPosition().distance(area.getTarget()) < 8;
        if (gameOver) {
            player.getBall().setVisible(false);
        }
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Ball ball = player.getBall();
        if (ball.isMoving() && !gameOver) {
            double dx = 0, dy = 0, x, y;
            dx = ball.getVelocity() * Math.sin(Math.toRadians(ball.getAngle()));
            dy = ball.getVelocity() * Math.cos(Math.toRadians(ball.getAngle()));
            x = ball.getX() + dx;
            y = ball.getY() + dy;
            ball.setVelocity(ball.getVelocity() * RESISTANCE_COEFFICIENT);
            ball.setPosition(x, y);

            maybeCollision();
            maybeReachedTarget();
        }
        gui.refresh();
    }

    public boolean gameIsOver() {
        return gameOver;
    }

}
