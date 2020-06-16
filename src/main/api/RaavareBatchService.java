package api;

import DTO.RaavareBatchDTO;
import controller.Classes.RaavareBatchController;
import controller.ControllerException;
import controller.Interfaces.IRaavareBatchController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Zahra
 */

@Path("rbService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareBatchService {
    private IRaavareBatchController batchController = new RaavareBatchController();

    @Path("getRB")
    @GET
    public Response getRaavareBatch(@QueryParam("rbId") int rbId) {
        try {
            return Response.status(Response.Status.OK).entity(batchController.getRaavareBatch(rbId)).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("createRB")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRaavareBatch(RaavareBatchDTO rBatchObjekt) {
        try{
            batchController.createRaavareBatch(rBatchObjekt);
            return Response.ok().entity("Raavarebatch oprettet succesfuldt!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("updateRB")
    @PUT
    public Response updateRaavareBatch(RaavareBatchDTO rBatchObjekt) {
        try {
            batchController.updateRaavareBatch(rBatchObjekt);
            return Response.ok().entity("Raavarebatch succesfuldt ændret!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("deleteRB")
    @DELETE
    public Response deleteRaavareBatch(RaavareBatchDTO rBatch) {
        try {
            batchController.deleteRaavareBatch(rBatch);
            return Response.ok().entity("Raavaretbatch succesfuldt slettet!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

}














