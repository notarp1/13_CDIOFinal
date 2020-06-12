package api;

import DTO.BrugerDTO;
import controller.Classes.BrugerController;
import controller.ControllerException;
import controller.Interfaces.IBrugerController;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @Author Theis
 */
@Path("bruger")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrugerService {
    private IBrugerController controller = new BrugerController();

    @GET
    @Path("{id}")
    public Response getBruger(@PathParam("id") int oprId) {
        try {
            return Response.ok().entity(controller.getBruger(oprId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("all")
    public Response getBrugerList() {
        try {
            return Response.ok().entity(controller.getBrugerList()).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBruger(BrugerDTO bruger) {
        try {
            controller.createBruger(bruger);
            return Response.ok().entity("Bruger oprettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBruger(BrugerDTO bruger) {
        try {
            controller.updateBruger(bruger);
            return Response.ok().entity("Bruger rettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBruger(@PathParam("id") int oprId) {
        try {
            controller.deleteBruger(oprId);
            return Response.ok().entity("Bruger slettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
