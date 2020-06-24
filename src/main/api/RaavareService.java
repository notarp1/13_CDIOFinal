package api;

import DAL.Classes.RaavareDAO;
import DTO.RaavareDTO;
import controller.Classes.RaavareController;
import controller.ControllerException;
import controller.Interfaces.IRaavareController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Christine
 */

@Path("raaService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareService {
    private IRaavareController raaController = new RaavareController();

    @Path("{raavareId}")
    @GET
    public Response getRaavare(@PathParam("raavareId") int raavareId) {

        try{
            return Response.status(Response.Status.OK).entity(raaController.getRaavare(raavareId)).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("list")
    @GET
    public Response getRaavareList(){
        try {
            return Response.status(Response.Status.OK).entity(raaController.getRaavareList()).build();
        }catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRaavare(RaavareDTO raavare){
        try {
            raaController.createRaavare(raavare);
            return Response.ok().entity("Raavaren er blevet oprettet succesfuldt").build();
        }catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRaavare(RaavareDTO raavare){
        try {
            raaController.updateRaavare(raavare);
            return Response.ok().entity("Raavaren er blevet opdateret succesfuldt").build();
        }catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{raavareId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRaavare(@PathParam("raavareId")int raavareId){
        try {
            raaController.deleteRaavare(raavareId);
            return Response.ok().entity("Raavaren er blevet slettet succesfuldt").build();
        }catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }






    }



