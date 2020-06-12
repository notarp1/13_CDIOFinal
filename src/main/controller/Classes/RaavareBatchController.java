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
    private RaavareBatchDTO raavarebatch;

    @Override
    public RaavareBatchDTO getRaavareBatch(int rbId) throws ControllerException {
        try {
            return rBatch.getRaavareBatch(rbId);
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
    public void createRaavareBatch(RaavareBatchDTO raavareBatchDTO) throws ControllerException {
        rangeConfirmRB();
        try {
            rBatch.createRaavareBatch(raavarebatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke oprette Råvarebatch");
        }

    }

    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavareBatchDTO) throws ControllerException {

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
    public void deleteRaavareBatch(RaavareBatchDTO raavareBatchDTO) throws ControllerException {
        try {
            this.getRaavareBatch(raavarebatch.getRbId());
            rBatch.deleteRaavareBatch(raavarebatch);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke slette Råvarebatch");
        }
    }

    private void rangeConfirmRB() {
    }

    private void rangeConfirmRB(int rbId) throws ControllerException {
        if (rbId < 1 || rbId > 99999999) {
            throw new ControllerException("Råvarebatch ID ikke i den tilladte range{1..99999999}");
        }
    }
}












