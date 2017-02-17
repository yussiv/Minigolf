/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.yussiv.minigolf.domain;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkvoipio
 */
public class PositionTest {
    
    private Position pos;
    
    @Before
    public void setUp() {
        pos = new Position(10,20);
    }
    
    @Test
    public void settingValuesOK() {
        assertEquals(10, pos.getX(), 0.01);
        assertEquals(20, pos.getY(), 0.01);
        
        pos.setLocation(new Point(30,70));
        assertEquals(30, pos.getX(), 0.01);
        assertEquals(70, pos.getY(), 0.01);
        
        pos.setLocation(124.0,81.0);
        assertEquals(124, pos.getX(), 0.01);
        assertEquals(81, pos.getY(), 0.01);
    }
}
