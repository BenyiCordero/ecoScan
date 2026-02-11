package ecoScan.auth;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author Benyi Uriel
 */

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter {
    
}
