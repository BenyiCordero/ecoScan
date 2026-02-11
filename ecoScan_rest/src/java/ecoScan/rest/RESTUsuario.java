package ecoScan.rest;

import ecoscan.controller.UsuarioController;
import ecoscan.model.dto.LoginRequest;
import ecoscan.model.dto.RegisterRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Benyi Uriel
 */

@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTUsuario {
    
    private UsuarioController controller = new UsuarioController();
    
    /*
    * Metodo para hacer el registro del usuario
    */
    @POST
    @Path("register")
    public Response register(RegisterRequest registerRequest) {
        try {
            String resultado = controller.register(registerRequest);
            return Response.ok(resultado).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
    
    /*
    * Metodo para hacer el login del usuario
    */
    @POST
    @Path("login")
    public Response login(LoginRequest loginRequest){
        try {
            String token = controller.login(loginRequest);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }
    
}
