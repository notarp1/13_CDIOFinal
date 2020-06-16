package controller.Interfaces;

import DTO.RaavareDTO;
import controller.ControllerException;

import java.util.List;

/**
 * @author Christine
 */

public interface IRaavareController {

    RaavareDTO getRaavare(int raavareId) throws ControllerException;
    List<RaavareDTO> getRaavareList() throws ControllerException;

    void createRaavare(RaavareDTO raavare) throws ControllerException;
    void updateRaavare(RaavareDTO raavare) throws ControllerException;
    void deleteRaavare(int raavareId) throws ControllerException;

}
