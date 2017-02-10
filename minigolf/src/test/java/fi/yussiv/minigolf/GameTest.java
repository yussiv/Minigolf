package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Player;
import fi.yussiv.minigolf.domain.Position;
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
    private Player player;

    public GameTest() {
    }

    @Before
    public void setUp() {
        game = new Game();
        area = game.getLevelArea();
        player = new Player("");
    }

    @Test
    public void ballStartLocationCorrect() {
        area.setStart(new Point(200, 10));

        game.addPlayer(player);
        game.startGame();

        assertEquals(10, player.getBall().getY(), 0.01);
        assertEquals(200, player.getBall().getX(), 0.01);
    }

    @Test
    public void reachingTargetDetected() {
        area.setTarget(new Point(90, 140));

        game.addPlayer(player);
        player.getBall().setPosition(new Position(83, 147));
        assertTrue(game.targetReached());
        player.getBall().setPosition(new Position(82, 148));
        assertFalse(game.targetReached());
    }

}
