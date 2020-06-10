package controller.Interfaces;

import DTO.BrugerDTO;
import controller.ControllerException;

import java.util.List;

public interface IBrugerController {
    BrugerDTO getBruger(int oprId) throws ControllerException;
    List<BrugerDTO> getBrugerList() throws ControllerException;
    void createBruger(BrugerDTO opr) throws ControllerException;
    void updateBruger(BrugerDTO opr) throws ControllerException;
    void deleteBruger(int oprId) throws  ControllerException;
}
