package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IReceptDAO;
import DTO.ReceptDTO;

import java.util.List;

public class ReceptDAO implements IReceptDAO {
    @Override
    public ReceptDTO getRecept(int receptId) throws DALException {
        return null;
    }

    @Override
    public List<ReceptDTO> getReceptList() throws DALException {
        return null;
    }

    @Override
    public void createRecept(ReceptDTO recept) throws DALException {

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws DALException {

    }
}
