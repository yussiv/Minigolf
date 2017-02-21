package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.Game;
import fi.yussiv.minigolf.domain.LevelArea;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * Main class for the graphical user interface.
 */
public class GUI extends Timer implements ActionListener, Runnable {

    private JFrame frame;
    private Canvas canvas;
    private final Game game;

    public GUI(Game game, int refreshInterval) {
        super(refreshInterval, null);
        this.game = game;
    }

    public void hitBall(double force, double angle) {
        game.setBallMovement(force, angle);
    }

    @Override
    public void run() {
        this.addActionListener(this);
        this.start();
        frame = new JFrame("Big League Minigolf");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Add panel contents and listeners.
     *
     * @param container
     */
    public void createComponents(Container container) {
        LevelArea area = game.getLevelArea();
        canvas = new Canvas(area.getWidth(), area.getHeight(), game.getBall(), area);
        canvas.setPreferredSize(new Dimension(600, 800));

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ButtonListener(this));
        buttons.add(reset);

        canvas.addMouseMotionListener(new MinigolfMouseListener(this, canvas));
        canvas.addMouseListener(new MinigolfMouseListener(this, canvas));

        container.add(canvas);
        container.add(buttons);
    }

    /**
     * A boolean to indicate if an animation is still in progress.
     *
     * @return
     */
    public boolean isAnimating() {
        return false; //game.getPlayer().getBall().isMoving();
    }

    /**
     * Tells if the ball has reached the target and the game is won.
     * @return 
     */
    public boolean gameIsOver() {
        return game.gameIsOver();
    }

    /**
     * Reset game state to initial values.
     */
    public void reset() {
        game.initializeLevel();
    }

    /**
     * Timer action listener method. Runs ball movement calculations and
     * refreshes the screen. Effectively the game loop.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        game.simulateRound();
        canvas.refresh();
    }
}
