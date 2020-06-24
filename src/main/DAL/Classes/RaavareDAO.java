package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IRaavareDAO;
import DAL.Statics;
import DTO.RaavareDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christine
 */

public class RaavareDAO implements IRaavareDAO {

    @Override
    public RaavareDTO getRaavare(int raavareId) throws DALException {

        try {

            String sqlManipulation = "SELECT * FROM Raavare WHERE raavareId='" + raavareId + "'";
             ResultSet resultSet = Statics.DB.get(sqlManipulation);

             if (resultSet.next()){

                 RaavareDTO varer = new RaavareDTO();
                 varer.setRaavareId(resultSet.getInt("raavareId"));
                 varer.setRaavareNavn(resultSet.getString("raavareNavn"));
                 varer.setLeverandoer(resultSet.getString("leverandoer"));
                 return varer;

             } else {
                 throw new DALException("Kunne ikke finde raavaren");
             }

         } catch (SQLException | ClassNotFoundException e ) {
             e.printStackTrace();
             throw new DALException("Database fejl");
        }

    }



    @Override
    public List<RaavareDTO> getRaavareList() throws DALException {

        List<RaavareDTO> raavareListe = new ArrayList<>();

        try {

            String sqlManipulation = "SELECT * FROM Raavare";
            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            while (resultSet.next()) {
                RaavareDTO varer = new RaavareDTO();
                varer.setRaavareId(resultSet.getInt("raavareId"));
                varer.setRaavareNavn(resultSet.getString("raavareNavn"));
                varer.setLeverandoer(resultSet.getString("leverandoer"));

                raavareListe.add(varer);
            }

            return raavareListe;

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }

    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException {

        try {

            String sqlManipulation = "INSERT Raavare VALUES ('" + raavare.getRaavareId() + "', '" + raavare.getRaavareNavn() + "', '" + raavare.getLeverandoer() + "')";
            Statics.DB.update(sqlManipulation);


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }


    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {
        try {

            String sqlManipulation = "UPDATE Raavare SET raavareId = " + raavare.getRaavareId() + ", raavareNavn = '" + raavare.getRaavareNavn() + "', leverandoer = '" + raavare.getLeverandoer() + "' WHERE raavareId = " + raavare.getRaavareId();
            Statics.DB.update(sqlManipulation);

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }

    @Override
    public void deleteRaavare(int raavareId) throws DALException{
        try {
            String sqlManipulation = "DELETE FROM Raavare WHERE raavareId = " + raavareId;
            Statics.DB.update(sqlManipulation);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }

    }

}
