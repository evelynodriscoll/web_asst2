/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.evelyn.bouncer;

import cst8218.evelyn.bouncer.entity.Bouncer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * BouncerFacade
 * This class extends AbstractFacade.
 * It is Stateless (does not maintain conversation between client) and contains reference to the persistence unit and the EntityManager.
 * It is part of the Business Layer.
 * @author Evelyn O'Driscoll
 */
@Stateless
public class BouncerFacade extends AbstractFacade<Bouncer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BouncerFacade() {
        super(Bouncer.class);
    }
    
}
