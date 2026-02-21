package ecoScan.auth;

import ecoscan.security.JwtUtil;
import io.jsonwebtoken.Claims;
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

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        if("OPTIONS".equalsIgnoreCase(crc.getMethod())){
            return;
        }
        
        String path = crc.getUriInfo().getPath();
        
        if(
            path.contains("usuario/login") || 
            path.contains("usuario/register") ||
            path.contains("recicladora/getall") ||
            path.contains("recicladora/activas") ||
            path.contains("recicladora/byid") || 
            path.contains("recicladora/materiales") ||
            path.contains("recicladora/horarios") ||
            path.contains("material/getall")
        ){
            return;
        }
        
        String authHeader = crc.getHeaderString("Authorization");
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            crc.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Token requerido")
                            .build()
            );
            return;
        }
        
        String token = authHeader.substring("Bearer ".length());
        
        try {
            Claims claims = JwtUtil.validarToken(token);
            
            crc.setProperty("idUsuario", claims.get("idUsuario"));
            crc.setProperty("rol", claims.get("rol"));
        } catch (Exception e) {
            crc.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token invalido o expirado")
                    .build()
            );
        }

    }
    
}
