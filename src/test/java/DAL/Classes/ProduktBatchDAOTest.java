package DAL.Classes;

import DAL.DALException;
import DTO.ProduktBatchDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ProduktBatchDAOTest {

    /**
     * @Author Christian Pone
     */


    private void createBatch(ProduktBatchDTO batch) {
        batch.setPbId(99999999);
        batch.setStatus(3);
        batch.setReceptId(13);
    }


    @Test
    public void getProduktBatch() {

        ProduktBatchDAO test = new ProduktBatchDAO();

        try {
            ProduktBatchDTO batch = new ProduktBatchDTO();

            createBatch(batch);

            test.deleteProduktBatch(batch);

            batch = test.getProduktBatch(99999999);

            if(batch.getPbId() == 99999999){
                assert false;
            }

        } catch (DALException e) {
            assert true;
        }


        ProduktBatchDTO batch = new ProduktBatchDTO();

        createBatch(batch);
        try {
            test.createProduktBatch(batch);
            assert true;
            test.getProduktBatch(99999999);
            assert true;
            test.deleteProduktBatch(batch);
            assert true;
        } catch (DALException e) {
            e.printStackTrace();
            assert false;
        }


    }



    @Test
    public void getProduktBatchList() {

        ProduktBatchDAO test = new ProduktBatchDAO();
        ProduktBatchDTO batch = new ProduktBatchDTO();
        Boolean contains = false;

        createBatch(batch);


        List<ProduktBatchDTO> produktListe = new ArrayList<>();
        try {
            test.deleteProduktBatch(batch);
            test.createProduktBatch(batch);
            produktListe = test.getProduktBatchList();

            for(int i = 0; i<produktListe.size(); i++){

                if(produktListe.get(i).getPbId() == batch.getPbId()){
                    contains = true;
                }

            }

            test.deleteProduktBatch(batch);

            if(contains){
                assert true;
            } else  assert false;


        } catch (DALException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createProduktBatch() {
    }

    @Test
    public void updateProduktBatch() {

        ProduktBatchDAO test = new ProduktBatchDAO();
        ProduktBatchDTO batch = new ProduktBatchDTO();

        createBatch(batch);

        try {
            test.createProduktBatch(batch);
            assert true;
        } catch (DALException e) {
            e.printStackTrace();
            assert false;
        }

        batch.setStatus(2);

        try {
            test.updateProduktBatch(batch);
            assert true;

            ProduktBatchDTO testBruger = test.getProduktBatch(99999999);

            if(testBruger.getStatus() == 2){
                assert  true;
            } else assert false;

            test.deleteProduktBatch(testBruger);
            assert true;

        } catch (DALException e) {
            e.printStackTrace();
            assert false;
        }


    }

    @Test
    public void deleteProduktBatch() {

        ProduktBatchDAO test = new ProduktBatchDAO();
        ProduktBatchDTO batch = new ProduktBatchDTO();

        createBatch(batch);

        try {
            test.createProduktBatch(batch);
            assert true;
            test.deleteProduktBatch(batch);
            assert true;

        } catch (DALException e) {
            e.printStackTrace();
            assert false;
        }



    }
}
