package controller.Classes;

import DAL.Classes.BrugerDAO;
import DTO.BrugerDTO;
import controller.ControllerException;
import controller.Interfaces.IBrugerController;

import java.util.List;

public class BrugerController implements IBrugerController {
    private BrugerDAO dao = new BrugerDAO();

    @Override
    public BrugerDTO getBruger(int oprId) throws ControllerException {
        return null;
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
