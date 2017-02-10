package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Player;
import fi.yussiv.minigolf.domain.Position;
import fi.yussiv.minigolf.gui.GUI;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * The game's main class.
 */
public class Game extends Timer implements ActionListener {

    private static final double RESISTANCE_COEFFICIENT = 0.99;
    private Player player;
    private LevelArea area;
    private GUI gui;

    public Game() {
        super(15, null);
        this.area = new LevelArea(600, 800);
        
        Random rand = new Random();
        this.area.setStart(new Point(rand.nextInt(250) + 25, rand.nextInt(150) + 25));
        this.area.setTarget(new Point(rand.nextInt(250) + 25, rand.nextInt(150) + 625));

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
     * Sets the balls movement parameters for the next hit, 
     * effectively putting the ball in motion.
     * 
     * @param force 
     * @param angle -180 to 180 degrees. 0 degrees means straight up.
     */
    public void excecutePutt(double force, double angle) {
        Ball ball = player.getBall();
        ball.setVelocity(force / 10);
        ball.setAngle(angle);
    }

    /**
     * Checks whether the ball has hit something
     * 
     * @param ball 
     */
    public void maybeCollision(Ball ball) {

        if (targetReached()) {
            ball.setPosition(new Position(-999, -999));
        }

        if (ball.getX() > area.getWidth() - 5 || ball.getX() < 5) {
            ball.setAngle(-1 * ball.getAngle());
        }

        if (ball.getY() < 5 || ball.getY() > area.getHeight() - 5) {
            ball.setAngle(180 - ball.getAngle());
        }
    }

    public boolean targetReached() {
        return player.getBall().getPosition().overlaps(area.getTarget(), 8);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Ball ball = player.getBall();
        if (ball.isMoving() && !targetReached()) {
            double dx = 0, dy = 0, x, y;
            dx = Math.round(ball.getVelocity() * Math.sin(ball.getAngle() * Math.PI / 180));
            dy = Math.round(ball.getVelocity() * Math.cos(ball.getAngle() * Math.PI / 180));
            x = ball.getX() + dx;
            y = ball.getY() + dy;
            ball.setVelocity(ball.getVelocity() * RESISTANCE_COEFFICIENT);
            ball.setPosition(new Position(x, y));

            maybeCollision(ball);
        }
        gui.refresh();
    }
}
