package controller.Classes;

import DAL.Classes.BrugerDAO;
import DAL.DALException;
import DAL.Interfaces.IBrugerDAO;
import DTO.BrugerDTO;
import controller.ControllerException;
import controller.Interfaces.IBrugerController;

import java.util.List;

public class BrugerController implements IBrugerController {
    private IBrugerDAO dao = new BrugerDAO();

    public BrugerController() {}

    @Override
    public BrugerDTO getBruger(int oprId) throws ControllerException {
        if (1 > oprId || oprId > 99999999)
            throw new ControllerException("oprId er ikke i den tilladte range 1-99999999");

        try {
            return dao.getBruger(oprId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws ControllerException {
        return null;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws ControllerException {

    }

    @Override
    public void updateBruger(BrugerDTO opr) throws ControllerException {

    }

    @Override
    public void deleteBruger(int oprId) throws ControllerException {

    }
}
