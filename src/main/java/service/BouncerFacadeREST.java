/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cst8218.evelyn.bouncer.entity.Bouncer;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *BouncerFacadeREST.java
 * @author Evelyn O'Driscoll
 * This class holds the HTTP methods from the REST API that allow the user to get information about the entity or create, delete and update objects.
 */

@DeclareRoles({"Admin", "ApiGroup"})
@Stateless
@Path("cst8218.evelyn.bouncer.entity.bouncer")
public class BouncerFacadeREST extends AbstractFacade<Bouncer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BouncerFacadeREST() {
        super(Bouncer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    /**
     * removeBouncer
     * This method deletes a single Bouncer after finding it by id.
     * @param id
     * @return HTTP Response 204 - NO CONTENT (indicates success w/ no body)
     */
    @DELETE
    @Path("{id}")
    @RolesAllowed({"Admin", "ApiGroup"})
    public Response removeBouncer(@PathParam("id") Long id) {  //delete a Bouncer
        super.remove(super.find(id));
        return Response.noContent().build();
    }

    /**
     * findBouncer
     * This method finds a single Bouncer by id and returns it in the response body.
     * @param id
     * @return HTTP Response 200 OK (success) with the requested Bouncer in the body or 404 Not Found if unsuccessful.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed({"Admin", "ApiGroup"})
    public Response findBouncer(@PathParam("id") Long id) {                //find a Bouncer by ID
        Bouncer bouncer = super.find(id);
        if (bouncer != null) {
            return Response.ok(bouncer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

     /**
     * findAllBouncers
     * This method finds a list of Bouncers within a range of ids and returns it in the response body.
     * @return HTTP Response 200 OK (success) with the requested list of Bouncers in the body or 204 NO CONTENT if there are no Bouncers.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})      //get a list of all Bouncers
    @RolesAllowed({"Admin", "ApiGroup"})
    public Response findAllBouncers() {
        List<Bouncer> bouncers = super.findAll();
        if (!bouncers.isEmpty()) {
            return Response.ok(bouncers).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    /**
     * findRangeBouncers
     * This method finds a list of Bouncers within a range of ids and returns it in the response body.
     * @param from - first id in range
     * @param to - last id in range
     * @return HTTP Response 200 OK (success) with the requested list of Bouncers in the body or 204 NO CONTENT if there are no Bouncers in that range.
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  
    @RolesAllowed({"Admin", "ApiGroup"})//find all Bouncers within a range of IDs
    public Response findRangeBouncers(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<Bouncer> bouncers = super.findRange(new int[]{from, to});
        if (!bouncers.isEmpty()) {
            return Response.ok(bouncers).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
    
    /**
     * countBouncers
     * This method counts how many Bouncers there are and returns it in the response body.
     * @return HTTP Response 200 OK (success) with the count of bouncers 
     */
    @GET
    @Path("countBouncers")               //return number of Bouncers
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"Admin", "ApiGroup"})
    public Response countBouncers() {
        int count = super.count();
        return Response.ok(count).build();
    }
    
    /**
     * createBouncer
     * This method creates a single Bouncer from either an XML or JSON request body.
     * @param b - new Bouncer to create
     * @return HTTP Response 201 CREATED (with Bouncer in the body) or 400 BAD REQUEST if the id is not null.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed({"Admin", "ApiGroup"})
    public Response createBouncer(Bouncer b) {                           //create a new bouncer
        if (b.getId() == null) { 
          
            if (b.getYVelocity() == 0) {
                b.setYVelocity(10); //default value if null
            }

            super.create(b);
 
            return Response.status(Response.Status.CREATED).entity(b).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST").build(); //return bad request if could not create
        }
    }
    
    /**
     * updateBouncer
     * This method updates some fields of the the Bouncer with new data (if the incoming data is not null);
     * @param id
     * @param newBouncer - new version of the requested bouncer
     * @return HTTP Response 200 OK (with updated Bouncer in the body) if successful, 404 NOT FOUND if previous Bouncer was null, 400 BAD REQUEST if the two Bouncers have different ids.
     */
    @POST
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed({"Admin", "ApiGroup"})
    public Response updateBouncer(@PathParam("id") Long id, Bouncer newBouncer) {              //update some fields of an existing Bouncer
        Bouncer oldBouncer = super.find(id);
        if (oldBouncer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("404 NOT FOUND").build();
        }
        if (!id.equals(newBouncer.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST").build();
        }
        newBouncer= oldBouncer.updateBouncer(oldBouncer, newBouncer, "POST");
        super.edit(newBouncer);
        return Response.ok(newBouncer).build();
    }
    
    /**
     * updateBouncerPUT
     * This method completely overwrites the old Bouncer regardless of what the incoming values are.
     * @param id
     * @param newBouncer - updated version of the Bounder
     * @return HTTP Response 200 OK (with updated Bouncer in the body) if successful, 404 NOT FOUND if previous Bouncer was null, 400 BAD REQUEST if the two Bouncers have different ids.
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed({"Admin", "ApiGroup"})
    public Response updateBouncerPUT(@PathParam("id") Long id, Bouncer newBouncer) { //overwrite an old Bouncer completely
        Bouncer oldBouncer = super.find(id);
        
        if (oldBouncer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        if (!oldBouncer.getId().equals(newBouncer.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
       
        newBouncer = oldBouncer.updateBouncer(oldBouncer, newBouncer, "PUT");
        
        super.edit(newBouncer);
        return Response.ok(newBouncer).build();
    }
}
    
    