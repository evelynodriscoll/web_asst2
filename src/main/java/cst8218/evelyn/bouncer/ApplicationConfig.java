/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.evelyn.bouncer;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;

/**
 *
 * @author alexa
 */
@Named
//@BasicAuthenticationMechanismDefinition
@DatabaseIdentityStoreDefinition(
   dataSourceLookup = "${'java:app/MariaDB'}",
   callerQuery = "#{'select password from app.appuser where userid = ?'}",
   groupsQuery = "select groupname from app.appuser where userid = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
)
@ApplicationScoped
public class ApplicationConfig {
    
}
