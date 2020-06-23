package DAL.Interfaces;

import DAL.DALException;
import DTO.ReceptDTO;

import java.util.List;

public interface IReceptDAO {
    ReceptDTO getRecept(int receptId) throws DALException;
    List<ReceptDTO> getReceptList() throws DALException;
    void createRecept(ReceptDTO recept) throws DALException;
    void updateRecept(ReceptDTO recept) throws DALException;
    void deleteRecept(int receptId) throws DALException;

}
