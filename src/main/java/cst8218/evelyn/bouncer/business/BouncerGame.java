/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.evelyn.bouncer.business;

import cst8218.evelyn.bouncer.BouncerFacade;
import cst8218.evelyn.bouncer.entity.Bouncer;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

/**
 * BouncerGame.java
 * BouncerGame is a Singleton Session bean. 
 * It is part of the Business Layer.
 * It runs the Bouncer game in an infinite loop until the user ends the session.
 * @author Evelyn O'Driscoll
 */
@Singleton
@Startup
public class BouncerGame {
    
@Inject
private BouncerFacade bouncerFacade;
    
private static final double CHANGE_RATE = 100;
    
    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            public void run() {
                // the game runs in an infinite loop
                while (true) {                   
                    List<Bouncer> bouncers = bouncerFacade.findAll();
                    for (Bouncer bouncer : bouncers) {
                        bouncer.advanceOneFrame();
                        bouncerFacade.edit(bouncer);
                    }
                    // sleep while waiting to load next frame
                    try {
                        Thread.sleep((long)(1.0/CHANGE_RATE*1000));
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }).start();
    }
}


