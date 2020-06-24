package api;


import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
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


    @Path("{id}")
    @GET
    public Response getProduktBatch(@PathParam("id") int pbId) {

        try {

            return Response.status(Response.Status.OK).entity(pController.getProduktBatch(pbId)).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("list")
    @GET
    public Response getProduktBatchList() {

        try {
            return Response.status(Response.Status.OK).entity(pController.getProduktBatchList()).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduktBatch(ProduktBatchDTO pBatch) {

        try {
            pController.createProduktBatch(pBatch);

            return Response.ok().entity("Produktbatch oprettet succesfuldt!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProduktBatch(ProduktBatchDTO pBatch) {
        try {
            pController.updateProduktBatch(pBatch);

            return Response.ok().entity("Produktbatch succesfuldt ændret!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProduktBatch(@PathParam("id") int pbId) {
        try {
            pController.deleteProduktBatch(pbId);

            return Response.ok().entity("Produktbatch succesfuldt slettet!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }


    @Path("PBK/{pbId}/{rbId}")
    @GET
    public Response getProduktBatchKomponent(@PathParam("pbId") int pbId, @PathParam("rbId") int rbId) {

        try {
            return Response.status(Response.Status.OK).entity(pController.getProduktBatchKomp(pbId, rbId)).build();

        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("PBK/list")
    @GET
    public Response getProduktBatchKomponentList(){

        try {
            return Response.status(Response.Status.OK).entity(pController.getProduktBatchKompList()).build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("PBK/list/{pbId}")
    @GET
    public Response getProduktBatchKomponentList(@PathParam("pbId") int pbId){

        try {
            return Response.status(Response.Status.OK).entity(pController.getProduktBatchKompList(pbId)).build();
        } catch (ControllerException e) {
            e.printStackTrace();
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("PBK")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduktBatchKomp(ProduktBatchKompDTO pkBatch) {

        try {
            pController.createProduktBatchKomp(pkBatch);

            return Response.ok().entity("Produktbatch-komponent oprettet succesfuldt!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("PBK")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProduktBatchKomp(ProduktBatchKompDTO pkBatch) {
        try {
            pController.updateProduktBatchKomp(pkBatch);

            return Response.ok().entity("Produktbatch-komponent succesfuldt ændret!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("PBK/{pbId}/{rbId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProduktBatchKomp(@PathParam("pbId") int pbId, @PathParam("rbId") int rbId) {
        try {
            pController.deleteProduktBatchKomp(pbId, rbId);

            return Response.ok().entity("Produktbatch-komponent succesfuldt slettet!").build();
        } catch (ControllerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }


}
