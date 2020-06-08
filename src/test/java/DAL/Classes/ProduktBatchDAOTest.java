package DAL.Classes;

import DAL.DALException;
import DTO.ProduktBatchDTO;
import org.junit.Test;



public class ProduktBatchDAOTest {


    @Test
    public void getProduktBatch() {

        ProduktBatchDAO test = new ProduktBatchDAO();

        try {
            ProduktBatchDTO unit = test.getProduktBatch(23);

            if(unit.getPbId()==23){
                assert true;
            } else assert false;

        } catch (DALException e) {
            assert false;

        }
    }

    @Test
    public void getProduktBatchList() {
    }
}
