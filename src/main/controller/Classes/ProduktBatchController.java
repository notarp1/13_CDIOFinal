package controller.Classes;

import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
import controller.ControllerException;
import controller.Interfaces.IProduktBatchController;

import java.util.List;

public class ProduktBatchController implements IProduktBatchController {
    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws ControllerException {
        return null;
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws ControllerException {
        return null;
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException {

    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException {

    }

    @Override
    public void deleteProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException {

    }

    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws ControllerException {
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws ControllerException {
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws ControllerException {
        return null;
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException {

    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException {

    }

    @Override
    public void deleteProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException {

    }
}
