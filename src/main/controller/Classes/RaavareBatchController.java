package controller.Classes;

import DAL.Classes.RaavareBatchDAO;
import DAL.DALException;
import DTO.RaavareBatchDTO;
import controller.ControllerException;
import controller.Interfaces.IRaavareBatchController;

import java.util.List;

/**
 * @author Zahra
 */
public class RaavareBatchController implements IRaavareBatchController {
    static RaavareBatchDAO rBatch = new RaavareBatchDAO();

    @Override
    public RaavareBatchDTO getRaavareBatch(int rbId) throws ControllerException {

        rangeConfirmRB(rbId);
        try {
            return rBatch.getRaavareBatch(rbId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke finde produkt");
        }
    }

    @Override
    public RaavareBatchDTO getRaavareBatchId(int raavareId) throws ControllerException {
        try {
            return rBatch.getRaavareBatchId(raavareId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke finde produkt");
        }
    }

    @Override
    public List<RaavareBatchDTO> getRavvareBatchList() throws ControllerException {
        try {
            return rBatch.getRaavareBatchList();
        } catch (DALException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke hente Råvarebatch list");
        }
    }

    @Override
    public List<RaavareBatchDTO> getRavvareBatchList(int rbId) throws ControllerException {
        try {
            return rBatch.getRaavareBatchList(rbId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke hente Råvarebatch list");
        }
    }

    @Override
    public void createRaavareBatch(RaavareBatchDTO rBatchObjekt) throws ControllerException {
        rangeConfirmRB(rBatchObjekt);
        try {
            rBatch.createRaavareBatch(rBatchObjekt);


        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke oprette Råvarebatch");
        }

    }


    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws ControllerException {

        rangeConfirmRB(raavarebatch.getRaavareId());
        try {
            this.getRaavareBatch(raavarebatch.getRbId());
            rBatch.updateRaavareBatch(raavarebatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke opdatte Råvarebatch");
        }
    }

    @Override
    public void deleteRaavareBatch(int rbId) throws ControllerException {
        try {
            this.getRaavareBatch(rbId);
            rBatch.deleteRaavareBatch(rbId);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke slette Råvarebatch");
        }
    }


    private void rangeConfirmRB(RaavareBatchDTO raavarebatch) {
    }

    private void rangeConfirmRB(int rbId) throws ControllerException {
        if (rbId < 1 || rbId > 99999999) {
            throw new ControllerException("Råvarebatch ID ikke i den tilladte range{1..99999999}");
        }
    }
}