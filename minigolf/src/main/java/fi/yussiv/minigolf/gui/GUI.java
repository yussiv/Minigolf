package fi.yussiv.minigolf.gui;

import fi.yussiv.minigolf.Game;
import fi.yussiv.minigolf.domain.Ball;
import fi.yussiv.minigolf.domain.LevelArea;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
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
        System.out.println("got command!");
    }

    @Override
    public void run() {
        frame = new JFrame("Big League Minigolf");

        frame.setPreferredSize(new Dimension(620, 850));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        LevelArea area = game.getLevelArea();
        canvas = new Canvas(area.getWidth(), area.getHeight(), ball, area);

        container.add(canvas);
        container.addMouseMotionListener(new MinigolfMouseListener(this));
        container.addMouseListener(new MinigolfMouseListener(this));
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
}
