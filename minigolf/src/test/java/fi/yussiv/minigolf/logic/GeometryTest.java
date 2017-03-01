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
        assertEquals(45, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(-10,10);
        assertEquals(-45, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(-10,-10);
        assertEquals(-135, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(10,-10);
        assertEquals(135, Geometry.calculateAngle(a, b), 0.01);
        
        b = new Point(30,10);
        assertEquals(71.6, Geometry.calculateAngle(a, b), 0.1);
        
        b = new Point(-30,10);
        assertEquals(-71.6, Geometry.calculateAngle(a, b), 0.1);
        
        b = new Point(-30,10);
        assertEquals(-71.6, Geometry.calculateAngle(a, b), 0.1);
    }
    
    @Test
    public void purpendicularCollisionAngleCalculationsOK() {
        
        assertEquals(180, Geometry.calculateRicochetAngle(0,90), 0.001);
        assertEquals(180, Geometry.calculateRicochetAngle(0,-90), 0.001);
        assertEquals(0, Geometry.calculateRicochetAngle(180,90), 0.001);
        assertEquals(0, Geometry.calculateRicochetAngle(180,-90), 0.001);
        
        assertEquals(90, Geometry.calculateRicochetAngle(-90,0), 0.001);
        assertEquals(-90, Geometry.calculateRicochetAngle(90,0), 0.001);
        
        assertEquals(-85, Geometry.calculateRicochetAngle(95,5), 0.001);
    }
    
    @Test
    public void collisionAngleCalculationsOK() {
        
        assertEquals(175, Geometry.calculateRicochetAngle(-175,0), 0.001);
        assertEquals(-175, Geometry.calculateRicochetAngle(175,0), 0.001);
        assertEquals(-75, Geometry.calculateRicochetAngle(75,0), 0.001);
        assertEquals(75, Geometry.calculateRicochetAngle(-75,0), 0.001);
        assertEquals(135, Geometry.calculateRicochetAngle(-135,0), 0.001);
        assertEquals(-135, Geometry.calculateRicochetAngle(135,0), 0.001);
        assertEquals(45, Geometry.calculateRicochetAngle(-45,0), 0.001);
        assertEquals(-45, Geometry.calculateRicochetAngle(45,0), 0.001);
        
        assertEquals(135, Geometry.calculateRicochetAngle(45,90), 0.001);
        assertEquals(-45, Geometry.calculateRicochetAngle(-135,90), 0.001);
        
        assertEquals(-90, Geometry.calculateRicochetAngle(180,45), 0.001);
        assertEquals(90, Geometry.calculateRicochetAngle(180,-45), 0.001);
    }

}
