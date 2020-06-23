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

@Path("recept1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptService {
    private IReceptController receptController = new ReceptController();

    @Path("rId")
    @GET
    public Response getRecept1(@QueryParam("receptId") int receptId){

        try {
            return Response.ok().entity(receptController.getRecept(receptId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("rList")
    @GET
    public Response getReceptList1() {

        try {

            return Response.ok().entity(receptController.getReceptList()).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("createR")
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

    @Path("updateR")
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

    @Path("deleteR")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRecept1(ReceptDTO recept){
        try {
            receptController.deleteRecept(recept);

            return Response.ok().entity("Recept slettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("getRKomp")
    @GET
    public Response getRKomp(@QueryParam("receptId") int receptId,
                             @QueryParam("raavareId") int raavareId){

        try {
            return Response.ok().entity(receptController.getReceptKomp(receptId,raavareId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("RKompList1")
    @GET
    public Response getRKompList(@QueryParam("receptId") int receptId){

        try {
            return Response.ok().entity(receptController.getReceptKompList(receptId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("RKompList2")
    @GET
    public Response getRkomplist2(){

        try {
            return Response.ok().entity(receptController.getReceptKompList()).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("createRKomp")
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


    @Path("updateRKomp")
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


    @Path("deleteRkomp")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRKomp(ReceptKompDTO receptkomp){

        try {
            receptController.deleteReceptKomp(receptkomp);
            return Response.ok().entity("ReceptKomp slettet").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
