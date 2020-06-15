package controller.Interfaces;

import DTO.ReceptDTO;
import DTO.ReceptKompDTO;
import controller.ControllerException;

import java.util.List;

public interface IReceptController {
    ReceptDTO getRecept(int receptId) throws ControllerException;
    List<ReceptDTO> getReceptList() throws ControllerException;
    void createRecept(ReceptDTO recept) throws ControllerException;
    void updateRecept(ReceptDTO recept) throws ControllerException;
    void deleteRecept(ReceptDTO recept) throws ControllerException;

    ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws ControllerException;
    List<ReceptKompDTO> getReceptKompList(int receptId) throws ControllerException;
    List<ReceptKompDTO> getReceptKompList() throws ControllerException;
    void createReceptKomp(ReceptKompDTO receptkomponent) throws ControllerException;
    void updateReceptKomp(ReceptKompDTO receptkomponent) throws ControllerException;
    void deleteReceptKomp(ReceptKompDTO receptkomponent) throws ControllerException;

}
