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

    public static void main(String[] args) {
        RaavareBatchController test = new RaavareBatchController();

        try {
            test.getRaavareBatch(100);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

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
    public List<RaavareBatchDTO> getRavvareBatchList() throws ControllerException {
        try {
            return rBatch.getRaavareBatchList();
        } catch (DALException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ControllerException("Kunne ikke hente Råvarebatch list");
        }
    }

    @Override
    public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws ControllerException {
        rangeConfirmRB(raavarebatch);
        try {
            rBatch.createRaavareBatch(raavarebatch);
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
    public void deleteRaavareBatch(RaavareBatchDTO raavarebatch) throws ControllerException {
        try {
            this.getRaavareBatch(raavarebatch.getRbId());
            rBatch.deleteRaavareBatch(raavarebatch);
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












