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
            BrugerDTO bruger = controller.getBruger(oprId);
            return Response.ok().entity(bruger).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("all")
    public Response getBrugerList() {
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @POST
    public Response createBruger(String body) {
        JSONObject jsonObject = new JSONObject(body);
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @PUT
    public Response updateBruger(String body) {
        JSONObject jsonObject = new JSONObject(body);
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBruger(@PathParam("id") int oprId) {
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }
}
