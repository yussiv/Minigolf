package fi.yussiv.minigolf.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkvoipio
 */
public class PositionTest {

    public PositionTest() {
    }

    @Test
    public void correctToString() {
        Position p = new Position(38, 10);
        assertEquals("(38, 10)", p.toString());
    }

    @Test
    public void overlappingPositionsDetected() {
        Position p = new Position(38, 10);
        Position o = new Position(40, 6);

        assertTrue(p.overlaps(o, 5));
        assertFalse(p.overlaps(o, 2));
    }

    @Test
    public void positionEqualityDetected() {
        Position position = new Position(38, 10);
        Position same = new Position(38, 10);
        Position different = new Position(38, 9);

        assertEquals(position, same);
        assertNotEquals(position, different);
    }
}
