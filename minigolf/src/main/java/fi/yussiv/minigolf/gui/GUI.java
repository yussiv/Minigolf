package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.Game;
import fi.yussiv.minigolf.domain.LevelArea;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Main class for the graphical user interface.
 */
public class GUI implements Runnable {

    private JFrame frame;
    private Canvas canvas;
    private final Game game;

    public GUI(Game game) {
        this.game = game;
    }

    public void executeCommand(double force, double angle) {
        game.excecutePutt(force, angle);
    }

    @Override
    public void run() {
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
        canvas.setPreferredSize(new Dimension(600,800));
        
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ButtonListener(this));
        buttons.add(reset);
        
        canvas.addMouseMotionListener(new MinigolfMouseListener(this));
        canvas.addMouseListener(new MinigolfMouseListener(this));
        
        container.add(canvas);
        container.add(buttons);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Game getGame() {
        return game;
    }

    /**
     * A boolean to indicate if an animation is still in progress.
     * 
     * @return 
     */
    public boolean isAnimating() {
        return false; //game.getPlayer().getBall().isMoving();
    }

    void reset() {
        game.initializeLevel();
    }

    void animate() {
        game.simulateRound();
        canvas.refresh();
    }
}
