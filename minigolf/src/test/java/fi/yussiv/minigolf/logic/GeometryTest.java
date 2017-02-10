package fi.yussiv.minigolf.logic;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkvoipio
 */
public class GeometryTest {

    public GeometryTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void angleCalculatedCorrectlyFromTwoPoints() {
        Point a = new Point(0,0);
        Point b = new Point(0,10);
        assertEquals(0, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(0,-10);
        assertEquals(180, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(10,10);
        assertEquals(-45, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(-10,10);
        assertEquals(45, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(-10,-10);
        assertEquals(135, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(10,-10);
        assertEquals(-135, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(30,10);
        assertEquals(-71.6, Geometry.calculateAngle(a, b), 0.1);
        
        b = new Point(-30,10);
        assertEquals(71.6, Geometry.calculateAngle(a, b), 0.1);
    }

}
