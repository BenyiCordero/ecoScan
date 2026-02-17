package ecoScan.rest;

import ecoscan.controller.MaterialController;
import ecoscan.model.dto.MaterialRequest;
import ecoscan.model.dto.MaterialUpdate;
import ecoscan.model.dto.MessageResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Benyi Uriel
 */

@Path("material")
public class RESTMaterial {
    
    private MaterialController controller = new MaterialController();
    
    
    /*
    * Metodo para hacer el insert de material
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMaterial(MaterialRequest materialRequest) {
        try {
            String resultado = controller.createMaterial(materialRequest);
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    
    /*
    * Metodo para retornar todos los materiales
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getall")
    public Response getAll(){
        try {
            return Response.ok(controller.getAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    
    /*
    * Metodo para retornar un material por su id
    */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@QueryParam("idMaterial") Integer id) {
        try {
            return Response.ok(controller.getById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    
    /*
    * Metodo para actualizar un material
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(MaterialUpdate materialUpdate) {
        try {
            return Response.ok(controller.update(materialUpdate)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    
    /*
    * Metodo para retornar un material por su id
    */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("idMaterial") Integer id) {
        try {
            return Response.ok(controller.delete(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
}
