package DAL.Interfaces;

import DAL.DALException;
import DTO.RaavareBatchDTO;

import java.util.List;

public interface IRaavareBatchDAO {
    RaavareBatchDTO getRaavareBatch(int rbId) throws DALException;
    List<RaavareBatchDTO> getRaavareBatchList() throws DALException, ClassNotFoundException;
    List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException;
    void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
    void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;

}
