package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.Game;
import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Main class for the graphical user interface.
 */
public class GUI implements Runnable {

    private JFrame frame;
    private Canvas canvas;
    private Ball ball;
    private Game game;

    public GUI(Game game) {
        this.game = game;
        this.ball = game.getPlayer().getBall();
    }

    public void executeCommand(double force, double angle) {
        game.excecutePutt(force, angle);
    }

    @Override
    public void run() {
        frame = new JFrame("Big League Minigolf");

//        frame.setPreferredSize(new Dimension(600, 830));

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
        canvas = new Canvas(area.getWidth(), area.getHeight(), ball, area);
        canvas.setPreferredSize(new Dimension(600,800));
        
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();
        buttons.add(new JButton("Clear"));
        
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

    public JFrame getFrame() {
        return frame;
    }

    public void refresh() {
        canvas.refresh();
    }
    
    /**
     * A boolean to indicate if an animation is still in progress.
     * 
     * @return 
     */
    public boolean isAnimating() {
        return false; //game.getPlayer().getBall().isMoving();
    }
}
