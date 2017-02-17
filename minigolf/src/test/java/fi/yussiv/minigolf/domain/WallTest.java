package fi.yussiv.minigolf.domain;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkvoipio
 */
public class WallTest {

    private Wall wall;

    @Test
    public void overlappingElementsFoundOnHorizontalWall() {
        wall = new Wall(new Point(0, 0), 90, 10, 100);
        assertEquals(new Point(100, 0), wall.getEnd());
        assertTrue(wall.overlaps(new Point(100, 0), 10));
        assertTrue(wall.overlaps(new Point(110, 0), 10));
        assertTrue(wall.overlaps(new Point(114, 0), 10));
        assertFalse(wall.overlaps(new Point(115, 0), 10));
        
        
        assertTrue(wall.overlaps(new Point(0, 0), 1));
        assertTrue(wall.overlaps(new Point(-1, 0), 1));
        assertTrue(wall.overlaps(new Point(-5, 0), 1));
        assertFalse(wall.overlaps(new Point(-6, 0), 1));
    }

    @Test
    public void overlappingElementsFoundOnVerticalWall() {
        wall = new Wall(new Point(0, 0), 0, 10, 100);
        assertTrue(wall.overlaps(new Point(0, 100), 10));
        assertTrue(wall.overlaps(new Point(0, 110), 10));
        assertTrue(wall.overlaps(new Point(0, 114), 10));
        assertFalse(wall.overlaps(new Point(0, 115), 10));
        
        
        assertTrue(wall.overlaps(new Point(0, 0), 1));
        assertTrue(wall.overlaps(new Point(0, -1), 1));
        assertTrue(wall.overlaps(new Point(0, -5), 1));
        assertFalse(wall.overlaps(new Point(0, -6), 1));
    }

    @Test
    public void endPointCalculationOK() {
        wall = new Wall(new Point(0, 0), 90, 10, 100);
        assertEquals(new Point(100, 0), wall.getEnd());

        wall = new Wall(new Point(0, 0), 0, 10, 100);
        assertEquals(new Point(0, 100), wall.getEnd());

        wall = new Wall(new Point(0, 0), -90, 10, 100);
        assertEquals(new Point(-100, 0), wall.getEnd());

        wall = new Wall(new Point(0, 0), -180, 10, 100);
        assertEquals(new Point(0, -100), wall.getEnd());
    }

    @Test
    public void wallAngleCorrect() {
        wall = new Wall(new Point(0, 0), 174, 10, 100);
        assertEquals(174, wall.getAngle(new Ball()), 0.01);
    }

    @Test
    public void horizontalWallAngleCorrectAtEnds() {
        wall = new Wall(new Point(10, 10), 90, 10, 100);
        Ball ball = new Ball();
        ball.setPosition(10, 10);
        ball.setAngle(90);
        assertEquals(180, wall.getAngle(ball), 0.01);

        ball.setAngle(-90);
        assertEquals(90, wall.getAngle(ball), 0.01);

        ball.setPosition(110, 10);
        assertEquals(180, wall.getAngle(ball), 0.01);

        ball.setAngle(-179.9);
        assertEquals(180, wall.getAngle(ball), 0.01);

        ball.setAngle(-0.1);
        assertEquals(180, wall.getAngle(ball), 0.01);

        ball.setAngle(90);
        assertEquals(90, wall.getAngle(ball), 0.01);
    }

    @Test
    public void verticalWallAngleCorrectAtEnds() {
        wall = new Wall(new Point(10, 10), 0, 10, 100);
        Ball ball = new Ball();
        ball.setPosition(10, 5);
        ball.setAngle(0);
        assertEquals(90, wall.getAngle(ball), 0.01);

        ball.setAngle(90);
        assertEquals(0, wall.getAngle(ball), 0.01);

    }

    @Test
    public void attributesOK() {
        wall = new Wall(new Point(10, 10), 0, 10, 100);
        assertEquals(10, wall.getWidth());
        assertEquals(new Point(10, 10), wall.getStart());
        assertEquals(new Point(10, 110), wall.getEnd());
    }
}
