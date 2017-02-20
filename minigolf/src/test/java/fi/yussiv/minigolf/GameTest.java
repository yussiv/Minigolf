package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.LevelArea;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkvoipio
 */
public class GameTest {

    private Game game;
    private LevelArea area;

    public GameTest() {
    }

    @Before
    public void setUp() {
        game = new Game();
        area = game.getLevelArea();
        // set up starting situation
    }

    @Test
    public void ballStartLocationCorrect() {
        area.setStart(new Point(200, 10));
        game.startGame();
        
        assertEquals(10, game.getBall().getY(), 0.01);
        assertEquals(200, game.getBall().getX(), 0.01);
    }

    @Test
    public void reachingTargetDetected() {
        area.setTarget(new Point(90, 140));
        game.startGame();

        game.getBall().setPosition(83, 140);
        game.evaluateGameState();
        assertTrue(game.gameIsOver());
        game.getBall().setPosition(90, 147);
        game.evaluateGameState();
        assertTrue(game.gameIsOver());
        game.getBall().setPosition(82, 140);
        game.evaluateGameState();
        assertFalse(game.gameIsOver());
        game.getBall().setPosition(90, 148);
        game.evaluateGameState();
        assertFalse(game.gameIsOver());
    }

}
