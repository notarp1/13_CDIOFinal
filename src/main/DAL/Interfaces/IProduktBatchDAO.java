package DAL.Interfaces;

import DAL.DALException;
import DTO.ProduktBatchDTO;

import java.util.List;

public interface IProduktBatchDAO {
    ProduktBatchDTO getProduktBatch(int pbId) throws DALException;

    List<ProduktBatchDTO> getProduktBatchList() throws DALException;

    void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
    void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
    void deleteProduktBatch(int pbId) throws DALException;

}
