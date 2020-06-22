package controller.Classes;

import DAL.Classes.RaavareDAO;
import DAL.DALException;
import DTO.RaavareDTO;
import controller.ControllerException;
import controller.Interfaces.IRaavareController;

import javax.naming.ContextNotEmptyException;
import java.util.List;

/**
 * @author Christine
 */

public class RaavareController implements IRaavareController {


    private RaavareDAO raavarec = new RaavareDAO();






    @Override
    public RaavareDTO getRaavare(int raavareId) throws ControllerException{
        validRaaId(raavareId);
        try {
            return this.raavarec.getRaavare(raavareId);

        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Raavaren kunne ikke findes");
        }
    }

    @Override
    public List<RaavareDTO> getRaavareList() throws ControllerException{
        try {
            return this.raavarec.getRaavareList();

        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Raavarelisten kunne ikke hentes");

        }
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws ControllerException{
        validRaaId(raavare.getRaavareId());
        validRaaNavn(raavare.getRaavareNavn());
        validRaaLev(raavare.getLeverandoer());
        try {
            raavarec.createRaavare(raavare);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Raavaren kunne ikke oprettes i systemet");
        }


    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws ControllerException{
        validRaaId(raavare.getRaavareId());
        validRaaNavn(raavare.getRaavareNavn());
        validRaaLev(raavare.getLeverandoer());
        try {
            this.getRaavare(raavare.getRaavareId());
            raavarec.updateRaavare(raavare);
        } catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Raavaren kunne ikke opdateres");
        }


    }

    @Override
    public void deleteRaavare(int raavareId) throws ControllerException{
        validRaaId(raavareId);
        try {
            this.raavarec.getRaavare(raavareId);
            this.raavarec.deleteRaavare(raavareId);

        }catch (DALException e) {
            e.printStackTrace();
            throw new ControllerException("Raavaren kunne ikke slettes");
        }
    }


    private void validRaaId(int raavareId) throws ControllerException{

        if(raavareId < 1 || raavareId > 99999999){
            throw new ControllerException("Raavare Id'et er ikke inden for det tilladte range, som er 1 til 99999999");
        }
    }

    private void validRaaNavn(String raavareName) throws ControllerException{

        if ( raavareName.length() < 2 || raavareName.length() > 20 ){
            throw new ControllerException("Raavare navnet skal være mindst 2 tegn og højst 20 tegn");
        }
    }

    private void validRaaLev(String raavareLev) throws ControllerException {

        if (raavareLev.length() < 2 ||  raavareLev.length() > 20) {
            throw new ControllerException("Leverandoren skal være mindst 2 tegn og højst 20 tegn");

        }
    }





}
