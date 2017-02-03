package fi.yussiv.minigolf.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkvoipio
 */
public class BallTest {

    private Ball ball;

    public BallTest() {
    }

    @Before
    public void setUp() {
        ball = new Ball();
    }

    @Test
    public void canSetVelocity() {
        ball.setVelocity(100);
        assertEquals(100, ball.getVelocity(), 0.001);
    }

    @Test
    public void canSetAngle() {
        ball.setAngle(-120);
        assertEquals(-120, ball.getAngle(), 0.001);
    }

    @Test
    public void velocityCutoffWorks() {
        ball.setVelocity(0.31);
        assertTrue(ball.isMoving());
        ball.setVelocity(0.3);
        assertFalse(ball.isMoving());
    }

}