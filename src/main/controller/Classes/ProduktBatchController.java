package controller.Classes;

import DAL.Classes.ProduktBatchDAO;
import DAL.Classes.ProduktBatchKompDAO;
import DAL.DALException;
import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
import controller.ControllerException;
import controller.Interfaces.IProduktBatchController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Christian
 */



public class ProduktBatchController implements IProduktBatchController {

    ProduktBatchDAO pBatch = new ProduktBatchDAO();
    ProduktBatchKompDAO pkBatch = new ProduktBatchKompDAO();




    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws ControllerException {

        rangeConfirmPB(pbId);
        try {
            return pBatch.getProduktBatch(pbId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke finde produkt");
        }

    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws ControllerException {

        try {
            return  pBatch.getProduktBatchList();
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Advarsel: Kunne ikke hente produktbatch liste!");
        }

    }

    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException {
        rangeConfirmStatus(produktbatch.getStatus());
        rangeConfirmPB(produktbatch.getPbId());
        try {
            pBatch.createProduktBatch(produktbatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Oprettelse produktbatch mislykkedes!");
        }

    }



    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException {

        rangeConfirmStatus(produktbatch.getStatus());
        rangeConfirmPB(produktbatch.getPbId());
        try {
            this.getProduktBatch(produktbatch.getPbId());
            pBatch.updateProduktBatch(produktbatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Opdatering af produktbatch mislykkedes!");
        }

    }

    @Override
    public void deleteProduktBatch(int pbId) throws ControllerException {

        try {

            this.getProduktBatch(pbId);
            pBatch.deleteProduktBatch(pbId);

        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Produktbatch kunne ikke slettes!");
        }

    }

    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws ControllerException {

        rangeConfirmPBRB(pbId, rbId);

        try {
            return pkBatch.getProduktBatchKomp(pbId, rbId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Advarsel: Kunne ikke finde produktbatch-komponent!");
        }

    }


    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws ControllerException {
        rangeConfirmPB(pbId);

        try {
            List<ProduktBatchKompDTO> list = new ArrayList<>();
            list = pkBatch.getProduktBatchKompList(pbId);

            if(list.isEmpty()){
                throw new ControllerException("Advarsel: Kunne ikke finde produktbatch-komponent, tjek om det indtastede pbID er korrekt.");
            } else return list;


        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Advarsel: Kunne ikke finde produktbatch-komponent, tjek om det indtastede pbID er korrekt.");
        }

    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws ControllerException {
        try {
            return  pkBatch.getProduktBatchKompList();
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Advarsel: Kunne ikke finde produktbatch-komponent, tjek om det indtastede pbID er korrekt.");
        }
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException {

        rangeConfirmPBRB(produktbatchkomponent.getPbId(), produktbatchkomponent.getRbId());

        try {
            pkBatch.createProduktBatchKomp(produktbatchkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Oprettelse produktbatch-komponent mislykkedes!");
        }


    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException {

       rangeConfirmPBRB(produktbatchkomponent.getPbId(), produktbatchkomponent.getRbId());

        try {
            this.getProduktBatchKomp(produktbatchkomponent.getPbId(), produktbatchkomponent.getRbId());
            pkBatch.updateProduktBatchKomp(produktbatchkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Opdatering af produktbatch-komponent mislykkedes!");
        }
    }

    @Override
    public void deleteProduktBatchKomp(int pbId, int rbId) throws ControllerException {


        try {

            this.getProduktBatchKomp(pbId, rbId);
            pkBatch.deleteProduktBatchKomp(pbId, rbId);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Produktbatch-komponent kunne ikke slettes!");
        }

    }
    private void rangeConfirmPBRB(int pbId, int rbId) throws ControllerException {
        if (pbId < 1 || pbId > 99999999) {
            throw new ControllerException("Produktbatch ID ikke i den tilladte range{1..99999999}");
        }
        if (rbId < 1 || rbId > 99999999) {
            throw new ControllerException("Råvarebatch ID ikke i den tilladte range{1..99999999}");
        }
    }

    private void rangeConfirmPB(int pbId) throws ControllerException {
        if (pbId < 1 || pbId > 99999999) {
            throw new ControllerException("Produktbatch ID ikke i den tilladte range{1..99999999}");
        }

    }

    private void rangeConfirmStatus(int status) throws ControllerException {
        if(status < 0 || status > 2){
            throw new ControllerException("Produktbatch STATUS ikke i den tilladte range{0..2}");
        }
    }


}
