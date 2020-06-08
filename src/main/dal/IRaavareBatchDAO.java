package dal;

import dto.RaavareBatchDTO;

import java.util.List;

public interface IRaavareBatchDAO {
    RaavareBatchDTO getRaavareBatch(int rbId) throws DALException;
    List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
    List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException;
    void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
    void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;

}
