package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Player;
import fi.yussiv.minigolf.domain.Position;
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
    }

    @Test
    public void ballStartLocationCorrect() {
        area.setStart(new Position(200, 10));
        Player player = new Player();

        game.addPlayer(player);
        game.startGame();

        assertEquals(10, player.getBall().getY());
        assertEquals(200, player.getBall().getX());
    }

    @Test
    public void reachingTargetDetected() {
        area.setTarget(new Position(90, 140));
        Player player = new Player();

        game.addPlayer(player);
        player.getBall().setPosition(new Position(86, 144));
        assertTrue(game.targetReached());
        player.getBall().setPosition(new Position(85, 145));
        assertFalse(game.targetReached());
    }

}
