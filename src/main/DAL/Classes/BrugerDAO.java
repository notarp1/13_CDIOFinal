package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IBrugerDAO;
import DTO.BrugerDTO;

import java.util.List;

public class BrugerDAO implements IBrugerDAO {
    @Override
    public BrugerDTO getBruger(int oprId) throws DALException {
        return null;
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        return null;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {

    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {

    }
}
