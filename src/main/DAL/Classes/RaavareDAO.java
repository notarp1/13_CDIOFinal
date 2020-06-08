package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IRaavareDAO;
import DTO.RaavareDTO;

import java.util.List;

public class RaavareDAO implements IRaavareDAO {
    @Override
    public RaavareDTO getRaavare(int raavareId) throws DALException {
        return null;
    }

    @Override
    public List<RaavareDTO> getRaavareList() throws DALException {
        return null;
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException {

    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {

    }
}
