package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Player;
import fi.yussiv.minigolf.domain.Position;
import fi.yussiv.minigolf.gui.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Game extends Timer implements ActionListener {

    private static final double RESISTANCE_COEFFICIENT = 0.99;
    private Player player;
    private LevelArea area;
    private GUI gui;
    private int timeout = 0;

    public Game() {
        super(15, null);
        this.area = new LevelArea(600, 800);
        this.area.setStart(new Position(300, 50));
        this.area.setTarget(new Position(500, 700));

        this.player = new Player();
        this.player.setBall(new Ball());
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
        gui = new GUI(this);
        SwingUtilities.invokeLater(gui);
        addActionListener(this);
        start();
    }

    /**
     *
     * @param velocity
     * @param angle -180 to 180, 0 == up
     */
    public void excecutePutt(double velocity, double angle) {
        Ball ball = player.getBall();
        ball.setVelocity(velocity / 2);
        ball.setAngle(angle);
    }

    public void maybeCollision(Ball ball) {
        if (timeout > 0) {
            timeout--;
            return;
        }
        if (ball.getX() > 600 || ball.getX() < 0) {
            ball.setAngle(-1 * ball.getAngle());
            System.out.println("collision x, " + ball);
            timeout = 10;
        }

        if (ball.getY() < 0 || ball.getY() > 800) {
            ball.setAngle(180 - ball.getAngle());
            System.out.println("collision y, " + ball);
            timeout = 10;
        }
    }

    public boolean targetReached() {
        return player.getBall().getPosition().overlaps(area.getTarget(), 5);
    }

    private double calculateAngle(double objectAngle, double ballAngle) {
        return ballAngle - 180 + objectAngle;
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
