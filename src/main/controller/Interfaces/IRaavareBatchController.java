package controller.Interfaces;

import DAL.DALException;
import DTO.RaavareBatchDTO;
import controller.ControllerException;

import java.util.List;

public interface IRaavareBatchController {

    RaavareBatchDTO getRaavareBatch(int rbId) throws ControllerException;
    RaavareBatchDTO getRaavareBatchId(int raavareId) throws ControllerException;
    List<RaavareBatchDTO> getRavvareBatchList() throws ControllerException;
    List<RaavareBatchDTO> getRavvareBatchList(int rbId) throws ControllerException;

    void createRaavareBatch(RaavareBatchDTO raavareBatchDTO) throws ControllerException;
    void updateRaavareBatch(RaavareBatchDTO raavareBatchDTO) throws ControllerException;
    void deleteRaavareBatch(RaavareBatchDTO raavareBatchDTO) throws ControllerException;

}
