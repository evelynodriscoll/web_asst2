/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.evelyn.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cst8218.evelyn.bouncer.entity.Bouncer;


/**
 *
 * @author farooq
 */
public class TestBouncer {

    @Test
    public void testId() {
        Bouncer bouncer = new Bouncer();
        Long idValue = 1L;
        bouncer.setId(idValue);
        assertEquals(idValue, bouncer.getId(), "Failed to set Id");
    }

    @Test
    public void testX() {
        Bouncer bouncer = new Bouncer();
        int xValue = 10;
        bouncer.setX(xValue);
        assertEquals(xValue, bouncer.getX(), "Failed to set X");
    }

    @Test
    public void testY() {
        Bouncer bouncer = new Bouncer();
        int yValue = 20;
        bouncer.setY(yValue);
        assertEquals(yValue, bouncer.getY(), "Failed to set Y");
    }
}
