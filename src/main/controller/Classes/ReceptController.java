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

        rangeLimitR(receptId);
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
        rangeLimitR(recept.getReceptId());
        RNavnLimit(recept.getReceptNavn());
        try {
            recpetData.createRecept(recept);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke oprettes");
        }

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws ControllerException {
        rangeLimitR(recept.getReceptId());
        RNavnLimit(recept.getReceptNavn());
        try {
            recpetData.updateRecept(recept);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke opdateres");
        }

    }

    @Override
    public void deleteRecept(int receptId) throws ControllerException {
        try {
            recpetData.deleteRecept(receptId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke slettes");
        }

    }

    @Override
    public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws ControllerException {
        rangeLimitRKomp1(receptId, raavareId);
        try {
            return  receptKompD.getReceptKomp(receptId,raavareId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Eksistere ikke");
        }

    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(int receptId) throws ControllerException {
        rangeLimitR(receptId);

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
        rangeLimitRKomp2(receptkomponent.getReceptId(), receptkomponent.getRaavareId(), receptkomponent.getNomNetto(), receptkomponent.getTolerance());
        try {
            receptKompD.createReceptKomp(receptkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke oprettes");
        }

    }

    @Override
    public void updateReceptKomp(ReceptKompDTO receptkomponent) throws ControllerException {
        rangeLimitRKomp2(receptkomponent.getReceptId(), receptkomponent.getRaavareId(), receptkomponent.getNomNetto(), receptkomponent.getTolerance());
        try {
            receptKompD.updateReceptKomp(receptkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke opdatere");
        }

    }

    @Override
    public void deleteReceptKomp(int receptId, int raavaareId) throws ControllerException {
        try {
            receptKompD.deleteReceptKomp(receptId, raavaareId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke slette");
        }

    }




    private void rangeLimitR(int receptId) throws ControllerException {
        if(receptId < 1 || receptId > 99999999 )
            throw new ControllerException("uden for range 1-99999999");
    }

    private void RNavnLimit(String receptNavn) throws ControllerException {
        if(receptNavn.length() < 2 || receptNavn.length() > 20 )
            throw new ControllerException("Recept Navn limit er 2-20");
    }



    private void rangeLimitRKomp1(int receptId, int raavareId) throws ControllerException {
        if (receptId < 1 || receptId > 99999999)
            throw new ControllerException("uden for receptID range 1-99999999");
        if (raavareId < 1 || raavareId > 99999999) {
            throw new ControllerException("Uden for raavareID range 1-99999999");
        }

    }

    private void rangeLimitRKomp2(int receptId, int raavareId, double nomNetto, double tolerance) throws ControllerException {
        if(receptId < 1 || receptId > 99999999 )
            throw new ControllerException("uden for receptID range 1-99999999");
        if(raavareId < 1 || raavareId > 99999999 ){
            throw new ControllerException("Uden for raavareID range 1-99999999");
        }
        if(nomNetto < 0.05 || nomNetto > 20.0 ){
            throw new ControllerException("Uden for raavareID range 1-99999999");
        }
        if(tolerance < 0.1 || tolerance > 10 ){
            throw new ControllerException("Uden for raavareID range 1-99999999");
        }
    }
}
