package fi.yussiv.minigolf.domain;

import java.awt.Point;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkvoipio
 */
public class LevelAreaTest {

    private LevelArea area;

    public LevelAreaTest() {
    }

    @Before
    public void setUp() {
        this.area = new LevelArea(400, 400);
    }

    @Test
    public void canAddObstacle() {
        Obstacle o = new Wall(new Point(0,0),0,0,0);
        area.addObstacle(o);
        List<Obstacle> obstacles = area.getObstacles();
        assertTrue(obstacles.contains(o));
    }

    @Test
    public void areaDimensionsAreCorrect() {
        assertEquals(400, area.getHeight());
        assertEquals(400, area.getWidth());
    }

    @Test
    public void canSetTarget() {
        area.setTarget(new Point(66, 99));
        assertEquals(66, area.getTarget().getX(), 0.1);
        assertEquals(99, area.getTarget().getY(), 0.1);
    }

}
