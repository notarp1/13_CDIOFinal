package controller.Classes;

import DAL.Classes.ProduktBatchDAO;
import DAL.Classes.ProduktBatchKompDAO;
import DAL.DALException;
import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
import controller.ControllerException;
import controller.Interfaces.IProduktBatchController;

import java.util.List;

/**
 * @Author Christian
 */



public class ProduktBatchController implements IProduktBatchController {

    ProduktBatchDAO pBatch = new ProduktBatchDAO();
    ProduktBatchKompDAO pkBatch = new ProduktBatchKompDAO();



    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws ControllerException {

        if(pbId < 1 || pbId > 99999999){
          throw new ControllerException("Produktbatch ID ikke i den tilladte range {1..99999999}");
        }

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

        try {
            pBatch.createProduktBatch(produktbatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Oprettelse produktbatch mislykkedes!");
        }

    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException {

        try {
            pBatch.updateProduktBatch(produktbatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Opdatering af produktbatch mislykkedes!");
        }

    }

    @Override
    public void deleteProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException {


        try {
            pBatch.deleteProduktBatch(produktbatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Produktbatch kunne ikke slettes!");
        }

    }

    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws ControllerException {

        if(pbId < 1 || pbId > 99999999){
            throw new ControllerException("Produktbatch ID ikke i den tilladte range {1..99999999}");
        }
        if(rbId < 1 || rbId > 99999999){
            throw new ControllerException("Råvarebatch ID ikke i den tilladte range {1..99999999}");
        }

        try {
            return pkBatch.getProduktBatchKomp(pbId, rbId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Advarsel: Kunne ikke finde produktbatch-komponent!");
        }

    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws ControllerException {
        if(pbId < 1 || pbId > 99999999){
            throw new ControllerException("Produktbatch ID ikke i den tilladte range {1..99999999}");
        }

        try {
            return  pkBatch.getProduktBatchKompList(pbId);
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

        try {
            pkBatch.createProduktBatchKomp(produktbatchkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Oprettelse produktbatch-komponent mislykkedes!");
        }


    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException {

        try {
            pkBatch.updateProduktBatchKomp(produktbatchkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Opdatering af produktbatch-komponent mislykkedes!");
        }
    }

    @Override
    public void deleteProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException {


        try {
            pkBatch.deleteProduktBatchKomp(produktbatchkomponent);
        } catch (DALException e) {
            e.printStackTrace();
            throw  new ControllerException("Advarsel: Produktbatch-komponent kunne ikke slettes!");
        }

    }
}
