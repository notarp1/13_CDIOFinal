package controller.Classes;

import DAL.Classes.ReceptDAO;
import DAL.Classes.ReceptKompDAO;
import DAL.DALException;
import DTO.ReceptDTO;
import DTO.ReceptKompDTO;
import controller.ControllerException;
import controller.Interfaces.IReceptController;

import java.util.List;

/**
 * @Author Ismail
 */


public class ReceptController implements IReceptController {

    private ReceptDAO recpetData = new ReceptDAO();
    private ReceptKompDAO receptKompD = new ReceptKompDAO();

    @Override
    public ReceptDTO getRecept(int receptId) throws ControllerException {

        if(receptId > 99999999 || receptId < 1) {
            throw new ControllerException("Ugyldig ID");
        }
        try {
                return recpetData.getRecept(receptId);

        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Eksistere ikke");
        }
    }

    @Override
    public List<ReceptDTO> getReceptList() throws ControllerException {
        try {
            return recpetData.getReceptList();
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Eksistere ikke");
        }
    }

    @Override
    public void createRecept(ReceptDTO recept) throws ControllerException {
        try {
            recpetData.createRecept(recept);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke oprettes");
        }

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws ControllerException {
        try {
            recpetData.updateRecept(recept);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke opdateres");
        }

    }

    @Override
    public void deleteRecept(ReceptDTO recept) throws ControllerException {
        try {
            recpetData.deleteRecept(recept);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke slettes");
        }

    }

    @Override
    public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws ControllerException {
        if(receptId < 1 || receptId > 99999999 ){
            throw new ControllerException("Ugyldig receptId");
        }
        if(raavareId < 1 || raavareId > 99999999 ){
            throw new ControllerException("Ugyldig råvareID");
        }

        try {
            return  receptKompD.getReceptKomp(receptId,raavareId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Eksistere ikke");
        }

    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(int receptId) throws ControllerException {
        if(receptId < 1 || receptId > 99999999 )
            throw new ControllerException("Ugyldig ID");

        try {
            return receptKompD.getReceptKompList(receptId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Eksistere ikke");
        }

    }

    @Override
    public List<ReceptKompDTO> getReceptKompList() throws ControllerException {
        try {
            return receptKompD.getReceptKompList();
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke hente liste");
        }

    }

    @Override
    public void createReceptKomp(ReceptKompDTO receptkomponent) throws ControllerException {
        try {
            receptKompD.createReceptKomp(receptkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke oprettes");
        }

    }

    @Override
    public void updateReceptKomp(ReceptKompDTO receptkomponent) throws ControllerException {
        try {
            receptKompD.updateReceptKomp(receptkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke opdatere");
        }

    }

    @Override
    public void deleteReceptKomp(ReceptKompDTO receptkomponent) throws ControllerException {
        try {
            receptKompD.deleteReceptKomp(receptkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke slette");
        }

    }

}
