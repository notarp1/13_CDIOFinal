package controller.Classes;

import DAL.Classes.BrugerDAO;
import DAL.DALException;
import DAL.Interfaces.IBrugerDAO;
import DTO.BrugerDTO;
import controller.ControllerException;
import controller.Interfaces.IBrugerController;

import java.util.List;

/**
 * @Author Theis
 */
public class BrugerController implements IBrugerController {
    private IBrugerDAO dao = new BrugerDAO();

    public BrugerController() {}

    @Override
    public BrugerDTO getBruger(int oprId) throws ControllerException {
        validateBrugerId(oprId);

        try {
            return this.dao.getBruger(oprId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws ControllerException {
        try {
            return this.dao.getBrugerList();
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void createBruger(BrugerDTO opr) throws ControllerException {
        validateBruger(opr);

        try {
            this.dao.createBruger(opr);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void updateBruger(BrugerDTO opr) throws ControllerException {
        validateBruger(opr);

        try {
            this.dao.updateBruger(opr);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void deleteBruger(int oprId) throws ControllerException {
        validateBrugerId(oprId);

        try {
            // Tjekker om bruger findes, fordi dette kaster en DALException, hvis brugeren ikke findes
            this.dao.getBruger(oprId);
            // Hvis opslaget ikke fejlede, så kan brugeren "slettes"
            this.dao.deleteBruger(oprId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     * Tester en given BrugerDTO, for at se om parametrene overholder kravene
     *
     * @param opr BrugerDTO, som skal testes
     * @throws ControllerException Hvis der er et parameter som ikke overholdes, kastes en ControllerException
     */
    private void validateBruger(BrugerDTO opr) throws ControllerException {
        validateBrugerId(opr.getOprId());
        if (2 > opr.getIni().length() || opr.getIni().length() > 4)
            throw new ControllerException("INI skal være mindst 2 og max 4 tegn langt");
        if (2 > opr.getOprNavn().length() || opr.getOprNavn().length() > 20)
            throw new ControllerException("oprNavn skal være mindst 2 og max 20 tegn langt");
        if (opr.getCpr().length() != 10)
            throw new ControllerException("CPR skal være 10 tegn langt, er " + opr.getCpr().length());
        if (opr.getRoller().size() == 0)
            throw new ControllerException("Brugeren skal have mindst 1 rolle");
    }

    /**
     * Tester et givent tal, for om det overholder kravet om at oprId skal være mellem 1 og 99999999
     *
     * @param oprId ID der skal testes
     * @throws ControllerException Hvis tallet er for lavt eller stort
     */
    private void validateBrugerId(int oprId) throws ControllerException {
        if (1 > oprId || oprId > 99999999)
            throw new ControllerException("oprId er ikke i den tilladte range 1-99999999");
    }
}
