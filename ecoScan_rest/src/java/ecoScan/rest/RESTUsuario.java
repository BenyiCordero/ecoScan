package ecoScan.rest;

import ecoScan.auth.AdminOnly;
import ecoscan.controller.UsuarioController;
import ecoscan.model.Usuario;
import ecoscan.model.dto.LoginRequest;
import ecoscan.model.dto.LoginResponse;
import ecoscan.model.dto.MessageResponse;
import ecoscan.model.dto.RegisterRequest;
import ecoscan.model.dto.UsuarioResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Benyi Uriel
 */

@Path("usuario")
public class RESTUsuario {
    
    private UsuarioController controller = new UsuarioController();
    
    /*
    * Metodo para hacer el registro del usuario
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("register")
    public Response register(RegisterRequest registerRequest) {
        try {
            String resultado = controller.register(registerRequest);
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    
    /*
    * Metodo para hacer el login del usuario
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(LoginRequest loginRequest){
        try {
            String token = controller.login(loginRequest);
            return Response.ok(new LoginResponse(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    
    /*
    * Metodo para retornar a un usuario mediante el email
    */
    @GET
    @AdminOnly
    @Path("getbyemail")
    public Response getByEmail(@QueryParam("email") String email){
        try {
            if(email == null || email.isEmpty()){
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new MessageResponse("El email es obligatorio"))
                        .build();
            }
            
            Usuario usuario = controller.getByEmail(email);
            return Response.ok(new UsuarioResponse(
                    usuario.getEmail(),
                    usuario.getNombre(),
                    usuario.getPrimerApellido(),
                    usuario.getSegundoApellido(),
                    usuario.getRol().name()
            )).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    
}
