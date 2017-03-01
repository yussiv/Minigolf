package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.Ball;
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
    }

    @Test
    public void ballStartParametersCorrect() {
        area.setStart(new Point(200, 10));
        game.initializeLevel(); // needed to re-place ball
        assertEquals(10, game.getBall().getY(), 0.01);
        assertEquals(200, game.getBall().getX(), 0.01);
        assertEquals(0, game.getBall().getAngle(), 0.01);
        assertEquals(0, game.getBall().getVelocity(), 0.01);
        assertTrue(game.getBall().isVisible());
        assertFalse(game.getBall().isMoving());
    }
    @Test
    public void ballReinitializationWorks() {
        Ball ball = game.getBall();
        ball.setVelocity(13);
        ball.setAngle(52);
        ball.setPosition(42, 12);
        ball.setVisible(false);
        
        game.initializeLevel(); 
        assertEquals(game.getLevelArea().getStart().y, ball.getY(), 0.01);
        assertEquals(game.getLevelArea().getStart().x, ball.getX(), 0.01);
        assertEquals(0, ball.getAngle(), 0.01);
        assertEquals(0, ball.getVelocity(), 0.01);
        assertTrue(ball.isVisible());
    }

    @Test
    public void reachingTargetDetected() {
        area.setTarget(new Point(90, 140));

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
    
    @Test
    public void ballVelocityCannotGoOverMaximum() {
        game.setBallMovement(100, 0);
        assertEquals(10, game.getBall().getVelocity(), 0.01);
        game.setBallMovement(100.1, 0);
        assertEquals(10, game.getBall().getVelocity(), 0.01);
        game.setBallMovement(251, 0);
        assertEquals(10, game.getBall().getVelocity(), 0.01);
    }
    
    @Test
    public void ballAngleReversed() {
        game.setBallMovement(0, 0);
        assertEquals(180, game.getBall().getAngle(), 0.01);
        
        game.setBallMovement(0, 90);
        assertEquals(-90, game.getBall().getAngle(), 0.01);
    }
    
    @Test
    public void movementSimulationWorks() {
        Ball ball = game.getBall();
        ball.setPosition(0, 0);
        ball.setAngle(0);
        ball.setVelocity(100);
        game.simulateRound();
        
        assertEquals(100, ball.getY(), 0.001);
        assertEquals(0, ball.getX(), 0.001);
        assertEquals(99.6, ball.getVelocity(), 0.01);
        
        ball.setVelocity(0);
        game.simulateRound();
        
        assertEquals(100, ball.getY(), 0.001);
        assertEquals(0, ball.getX(), 0.001);
        
        ball.setVelocity(100);
        ball.setPosition(game.getLevelArea().getTarget().x, game.getLevelArea().getTarget().y - 100); // target reached -> game over
        game.simulateRound();
        
        assertEquals(game.getLevelArea().getTarget().y, ball.getY(), 0.001);
        assertEquals(game.getLevelArea().getTarget().x, ball.getX(), 0.001);
        
        game.simulateRound();
        // should not change
        assertEquals(game.getLevelArea().getTarget().y, ball.getY(), 0.001);
        assertEquals(game.getLevelArea().getTarget().x, ball.getX(), 0.001);
    }

}
