package ecoScan.auth;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * @author Benyi Uriel
 */

@AdminOnly
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AdminFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String rol = (String) crc.getProperty("rol");
        
        if(rol == null || !rol.equals("ADMINISTRADOR")){
            crc.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                    .entity("Acceson restringido")
                    .build()
            );
        }
    }
    
}
