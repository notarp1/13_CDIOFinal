package controller.Classes;

import DTO.RaavareBatchDTO;
import controller.ControllerException;
import org.junit.Test;



public class RaavareBatchControllerTest {

    @Test
    public void getRaavareBatch() {
    }

    @Test
    public void getRavvareBatchList() {
    }

    @Test
    public void createRaavareBatch() {


        RaavareBatchController rBatch = new RaavareBatchController();

        RaavareBatchDTO raavareObjekt = new RaavareBatchDTO();

        raavareObjekt.setRbId(9999);
        raavareObjekt.setRaavareId(13);
        raavareObjekt.setMaengde(3.2);


        try {
            rBatch.createRaavareBatch(raavareObjekt);
            assert true;

            rBatch.getRaavareBatch(raavareObjekt.getRbId());
            assert true;

            rBatch.deleteRaavareBatch(raavareObjekt);

           try {
               rBatch.getRaavareBatch(raavareObjekt.getRbId());
               assert false;
           } catch (ControllerException e){
               assert true;
           }

        } catch (ControllerException e) {
                e.printStackTrace();
                assert false;
        }


    }

    @Test
    public void updateRaavareBatch() {
    }

    @Test
    public void deleteRaavareBatch() {
    }
}
