/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.evelyn.bouncer;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.core.Application;

/**
 *
 * @author alexa
 */
@Named
@BasicAuthenticationMechanismDefinition
@ApplicationScoped
@DatabaseIdentityStoreDefinition(
   dataSourceLookup = "${'java:app/MariaDB'}",
   callerQuery = "#{'select password from CST8218.appuser where userid = ?'}",
   groupsQuery = "select groupname from CST8218.appuser where userid = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
)
public class ApplicationConfig extends Application {
    
}
