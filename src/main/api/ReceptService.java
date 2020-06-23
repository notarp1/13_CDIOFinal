package api;


import DTO.ReceptDTO;
import DTO.ReceptKompDTO;
import controller.Classes.ReceptController;
import controller.ControllerException;
import controller.Interfaces.IReceptController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @Author Ismail
 */

@Path("recept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptService {
    private IReceptController receptController = new ReceptController();

    @Path("{receptId}")
    @GET
    public Response getRecept1(@PathParam("receptId") int receptId){

        try {
            return Response.ok().entity(receptController.getRecept(receptId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("list")
    @GET
    public Response getReceptList1() {

        try {

            return Response.ok().entity(receptController.getReceptList()).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRecept1(ReceptDTO recept) {
        try {
            receptController.createRecept(recept);
            return Response.ok().entity("Recept Oprettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRecept1(ReceptDTO recept){

        try {
            receptController.updateRecept(recept);

            return Response.ok().entity("Recept opdateret").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{receptId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRecept1(@PathParam("receptId") int receptId){
        try {
            receptController.deleteRecept(receptId);

            return Response.ok().entity("Recept slettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("komp/{receptId}/{raavareId}")
    @GET
    public Response getRKomp(@PathParam("receptId") int receptId,
                             @PathParam("raavareId") int raavareId){

        try {
            return Response.ok().entity(receptController.getReceptKomp(receptId,raavareId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("komp/list/{receptId}")
    @GET
    public Response getRKompList(@PathParam("receptId") int receptId){

        try {
            return Response.ok().entity(receptController.getReceptKompList(receptId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("komp/list")
    @GET
    public Response getRkomplist2(){

        try {
            return Response.ok().entity(receptController.getReceptKompList()).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("komp")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRkomp(ReceptKompDTO receptKomp){
        try {
            receptController.createReceptKomp(receptKomp);
            return Response.ok().entity("ReceptKomp oprettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("komp")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRKomp(ReceptKompDTO receptKomp){

        try {
            receptController.updateReceptKomp(receptKomp);
            return Response.ok().entity("ReceptKomp opdateret").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("komp/{receptId}/{raavareId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRKomp(@PathParam("receptId") int receptId,
                                @PathParam("raavareId") int raavareId){

        try {
            receptController.deleteReceptKomp(receptId, raavareId);
            return Response.ok().entity("ReceptKomp slettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
