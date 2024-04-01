/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.evelyn.bouncer.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bouncer.java
 * Bouncer is the Entity class. It is part of the Business Layer.
 * It holds the properties of a Bouncer (id, x, y, yVelocity), the setters and getters, and the primary methods, advanceOneFrame and updateBouncer.
 * @author Evelyn O'Driscoll
 */
@Entity
@XmlRootElement
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private int x;

    @NotNull
    private int y;

    @NotNull
    private int yVelocity;
    
    private static final int GRAVITY_ACCEL = 1;
    private static final int DECAY_RATE = 1;
    //private static final int F_WIDTH = 800;
    private static final int F_HEIGHT = 600;
    
    /**
     * advanceOneFrame()
     * This method increases the Bouncer's yValue by its yVelocity. The yVelocity increases according to gravity, but decreases by the decay rate when it bounces off one of the walls.
     * The yVelocity (and thus the yValue) reverses direction when it bounces off the bottom or top wall (y = 0 or y = 600).
     */
    public void advanceOneFrame() {
        
        if (yVelocity >= 0 && y < F_HEIGHT) {
            yVelocity += GRAVITY_ACCEL; // if velocity is not zero, accelerate
        }
        
        y += yVelocity; //increment the y axis of the bouncer by yVelocity

        if (y >= F_HEIGHT) {                        //when the bouncer hits the bottom wall
            yVelocity = -yVelocity + DECAY_RATE;    //reverse velocity and add decay
            y += yVelocity;
        } else if (y <= 0) {
            yVelocity = -yVelocity - DECAY_RATE;    //when the bouncer hits the top wall
            y += yVelocity;                        //reverse velocity and add decay
    }
}
    
    /**
     * updateBouncer method takes in the previous version of the Bouncer, plus the updated version, compares them and makes changes accordingly
     * @param oldBouncer - the before image version of the Bouncer
     * @param newBouncer - the updated Bouncer
     * @param mode - different behavior if PUT or POST request
     * @return oldBouncer - once updated
     */
    public Bouncer updateBouncer(Bouncer oldBouncer, Bouncer newBouncer, String mode) {
        
       switch(mode) {
           case "POST":                                         //only change attributes that are different
            if (oldBouncer.getX() != newBouncer.getX() && newBouncer.getX() != 0) {
                oldBouncer.setX(newBouncer.getX());
                }
            if (oldBouncer.getY() != newBouncer.getY()&& newBouncer.getX() != 0) {
                oldBouncer.setY(newBouncer.getY());
            }
            if (oldBouncer.getYVelocity() != newBouncer.getYVelocity()&& newBouncer.getYVelocity() != 0) {
                oldBouncer.setYVelocity(newBouncer.getYVelocity());
            }
            break;
            
           case "PUT":
               oldBouncer.setX(newBouncer.getX());                 //overwrite the Bouncer completely
               oldBouncer.setY(newBouncer.getY());
               oldBouncer.setYVelocity(newBouncer.getYVelocity());
               break;
       }
    
           
        return oldBouncer;
  
    }
       

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouncer)) {
            return false;
        }
        Bouncer other = (Bouncer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.evelyn.bouncer.entity.Bouncer[ id=" + id + " ]";
    }
    
}
