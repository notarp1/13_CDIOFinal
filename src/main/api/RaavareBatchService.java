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

    @Path("{rbId}")
    @GET
    public Response getRaavareBatch(@PathParam("rbId") int rbId) {

        try {
            return Response.status(Response.Status.OK).entity(batchController.getRaavareBatch(rbId)).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @Path("raavare/{raavareId}")
    @GET
    public Response getRaavareBatchId(@PathParam("raavareId") int raavareId) {

        try {
            return Response.status(Response.Status.OK).entity(batchController.getRaavareBatchId(raavareId)).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("list")
    @GET
    public Response getRaavareBatchList() {

        try {
            return Response.status(Response.Status.OK).entity(batchController.getRavvareBatchList()).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("list/{rbId}")
    @GET
    public Response getRaavareBatchList(@PathParam("rbId") int rbId) {

        try {
            return Response.status(Response.Status.OK).entity(batchController.getRavvareBatchList(rbId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

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

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRaavareBatch(RaavareBatchDTO rBatchObjekt) {
        try {
            batchController.updateRaavareBatch(rBatchObjekt);
            return Response.ok().entity("Raavarebatch succesfuldt ændret!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRaavareBatch(@PathParam("id") int rbId) {
        try {
            batchController.deleteRaavareBatch(rbId);
            return Response.ok().entity("Raavaretbatch succesfuldt slettet!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

}














