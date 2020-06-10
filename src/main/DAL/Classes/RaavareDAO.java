package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IRaavareDAO;
import DTO.RaavareDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaavareDAO implements IRaavareDAO {

    private String host = "primary.folkmann.it";
    private String port = "3306";
    private String username = "CDIO";
    private String password = "y1NzaOYI08FrdqzX";
    private String database = "/DTU_CDIOFinal";

    //Do not edit these variables
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://" + this.host + ":" + this.port + this.database + "?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    @Override
    public RaavareDTO getRaavare(int raavareId) throws DALException {

        try
        {
         Class.forName(this.driver);
         String sqlManipulation;
         sqlManipulation = "SELECT * FROM Raavarer WHERE raavareId='" + raavareId + "'";

         Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sqlManipulation);

         if (resultSet.next()){

             RaavareDTO varer = new RaavareDTO();
             varer.setRaavareId(resultSet.getInt("raavareId"));
             varer.setRaavareNavn(resultSet.getString("raavareNavn"));
             varer.setLeverandoer(resultSet.getString("leverandoer"));
             connection.close();

             return varer;


         }else {
             connection.close();
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
            Class.forName(this.driver);
            String sqlManipulation;

            sqlManipulation = "SELECT * FROM Raavare";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            while (resultSet.next()) {
                RaavareDTO varer = new RaavareDTO();
                varer.setRaavareId(resultSet.getInt("raavareId"));
                varer.setRaavareNavn(resultSet.getString("raavareNavn"));
                varer.setLeverandoer(resultSet.getString("leverandoer"));

                raavareListe.add(varer);
            }
            connection.close();

            return raavareListe;

            } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException {

        try {
            Class.forName(this.driver);

            String sqlManipulation = "INSERT Raavare VALUES ('" + raavare.getRaavareId() + "', '" + raavare.getRaavareNavn() + "', '" + raavare.getLeverandoer() + "')";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            connection.close();

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }



    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {
        try {
            Class.forName(this.driver);

            String sqlManipulation = "UPDATE Raavare SET raavareId = " + raavare.getRaavareId() + ", raavareNavn = " + raavare.getRaavareNavn() + ", leverandør = " + raavare.getLeverandoer() + " WHERE raavareId = " + raavare.getRaavareId();
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            connection.close();


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }
}
