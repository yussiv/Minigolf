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

    @Before
    public void setUp() {

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

}
