package api;


import DTO.ProduktBatchDTO;
import controller.Classes.ProduktBatchController;
import controller.ControllerException;
import controller.Interfaces.IProduktBatchController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @Author Christian
 */


@Path("pbService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduktBatchService {

    private IProduktBatchController pController = new ProduktBatchController();


    @Path("getPB")
    @GET
    public Response getProduktBatch(@QueryParam("pbId") int pbId){

        try {

            return Response.status(Response.Status.OK).entity(pController.getProduktBatch(pbId)).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }


    }




}
