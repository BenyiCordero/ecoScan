package ecoscan.rest;

import ecoscan.controller.RecicladoraController;
import ecoscan.model.dto.HorarioRequest;
import ecoscan.model.dto.HorarioUpdate;
import ecoscan.model.dto.MaterialRecicladoraRequest;
import ecoscan.model.dto.RecicladoraRequest;
import ecoscan.model.dto.RecicladoraUpdate;
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
 * @author emont
 */
@Path("Recicladora")
public class RESTRecicladora {

    private RecicladoraController controller = new RecicladoraController();

    /* ===================== Recicladora ====================================== */
    // dar de alta
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRecicladora(RecicladoraRequest request) {
        try {
            String resultado = controller.registrarRP(
                    request.nombreRecicladora(),
                    request.direccion(),
                    request.latitud(),
                    request.longitud()
            );
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // traer todas las recicladoras
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getall")
    public Response getAll() {
        try {
            return Response.ok(controller.obtenerTodasRP()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // traer rp por us estado (activas)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("activas")
    public Response getActivas() {
        try {
            return Response.ok(controller.obtenerPorEstado(true)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // traer rp por us estado (inactivas)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("inactivas")
    public Response getInactivas() {
        try {
            return Response.ok(controller.obtenerPorEstado(false)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // traer rp por su id
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@QueryParam("idRecicladora") Integer id) {
        try {
            return Response.ok(controller.obtenerPorIdRP(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // actualizar rp
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(RecicladoraUpdate request) {
        try {
            String resultado = controller.actualizar(
                    request.idRecicladora(),
                    request.nombreRecicladora(),
                    request.direccion(),
                    request.latitud(),
                    request.longitud()
            );
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // eliminar rp logico (desactivar)
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("idRecicladora") Integer id) {
        try {
            String resultado = controller.desactivar(id);
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // reactivar recicladora
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("reactivar")
    public Response reactivar(@QueryParam("idRecicladora") Integer id) {
        try {
            String resultado = controller.activar(id);
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    /* =================== Materiales de recicladora ======================= */
    // traer todos los materialees aceptados por la recicladora
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("materiales")
    public Response getMateriales(@QueryParam("idRecicladora") Integer idRecicladora) {
        try {
            return Response.ok(controller.obtenerMateriales(idRecicladora)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // dar de alta materiales que acepte la recicladora
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("materiales")
    public Response addMaterial(MaterialRecicladoraRequest request) {
        try {
            String resultado = controller.agregarMaterial(
                    request.idRecicladora(),
                    request.idMaterial()
            );
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // eliminar material ligado a la recicladora
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("materiales")
    public Response deleteMaterial(MaterialRecicladoraRequest request) {
        try {
            String resultado = controller.eliminarMaterial(
                    request.idRecicladora(),
                    request.idMaterial()
            );
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    /* ==================== Horarios de recicladora ==================== */
    // traer horario de rp
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("horarios")
    public Response getHorarios(@QueryParam("idRecicladora") Integer idRecicladora) {
        try {
            return Response.ok(controller.obtenerHorarios(idRecicladora)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // dar de alta horario de la recicladora
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("horarios")
    public Response addHorario(@QueryParam("idRecicladora") Integer idRecicladora,
            HorarioRequest request) {
        try {
            String resultado = controller.agregarHorario(
                    idRecicladora,
                    request.diaSemana(),
                    request.horaApertura(),
                    request.horaCierre()
            );
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // actualizar horarios
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("horarios")
    public Response updateHorario(HorarioUpdate request) {
        try {
            String resultado = controller.actualizarHorario(
                    request.idHorario(),
                    request.diaSemana(),
                    request.horaApertura(),
                    request.horaCierre()
            );
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    // eliminar horario
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("horarios")
    public Response deleteHorario(@QueryParam("idHorario") Integer idHorario) {
        try {
            String resultado = controller.eliminarHorario(idHorario);
            return Response.ok(new MessageResponse(resultado)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
}
