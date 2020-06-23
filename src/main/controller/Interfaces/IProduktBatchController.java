package controller.Interfaces;

import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
import controller.ControllerException;

import java.util.List;

public interface IProduktBatchController {



    ProduktBatchDTO getProduktBatch(int pbId) throws ControllerException;

    List<ProduktBatchDTO> getProduktBatchList() throws ControllerException;

    void createProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException;
    void updateProduktBatch(ProduktBatchDTO produktbatch) throws ControllerException;
    void deleteProduktBatch(int pbId) throws ControllerException;



    ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws ControllerException;

    List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws ControllerException;

    List<ProduktBatchKompDTO> getProduktBatchKompList() throws ControllerException;

    void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException;

    void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws ControllerException;

    void deleteProduktBatchKomp(int pbId, int rbId) throws ControllerException;


}
