package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IReceptKompDAO;
import DTO.ReceptKompDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ismail
 */

public class ReceptKompDAO implements IReceptKompDAO {
    private String host = "primary.folkmann.it";
    private String port = "3306";
    private String username = "CDIO";
    private String password = "y1NzaOYI08FrdqzX";
    private String database = "/DTU_CDIOFinal";

    //Do not edit these variables
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://" + this.host + ":" + this.port + this.database + "?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    private ReceptKompDTO getReceptKomp(ResultSet resultSet) throws SQLException {
        ReceptKompDTO receptKomp =  new ReceptKompDTO();
        receptKomp.setReceptId(resultSet.getInt("receptId"));
        receptKomp.setRaavareId(resultSet.getInt("raavareId"));
        receptKomp.setNomNetto(resultSet.getDouble("nomNetto"));
        receptKomp.setTolerance(resultSet.getDouble("tolerance"));
        return receptKomp;
    }

    @Override
    public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
         try{
            Class.forName(this.driver);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            String sqlManipulation = "SELECT * FROM ReceptKomp WHERE receptId='" + receptId + "' AND raavareId ='" + raavareId + "'";

            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            resultSet.next();
            ReceptKompDTO receptKomp =  getReceptKomp(resultSet);
            connection.close();

            return receptKomp;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("findes ikke");
        }
        return null;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {

        List<ReceptKompDTO> receptKompList = new ArrayList<>();

        try {
            Class.forName(this.driver);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            String sqlManipulation = "SELECT * FROM ReceptKomp where receptId='" + receptId + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            while (resultSet.next()) {
                ReceptKompDTO receptKomp = getReceptKomp(resultSet);

                receptKompList.add(receptKomp);
            }
            connection.close();

            return receptKompList;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("findes ikke");
        }



        return null;
    }


    @Override
    public List<ReceptKompDTO> getReceptKompList() throws DALException {

        List<ReceptKompDTO> receptKompList = new ArrayList<>();

        try {
            Class.forName(this.driver);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            String sqlManipulation = "SELECT * FROM ReceptKomp";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            String DataList = "";

            while (resultSet.next()) {

                ReceptKompDTO receptKomp = getReceptKomp(resultSet);

               receptKompList.add(receptKomp);
            }
            connection.close();

            return receptKompList;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("findes ikke");
        }


        return null;
    }


    @Override
    public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
        try {
            Class.forName(this.driver);
            String sqlManipulation = "INSERT ReceptKomp VALUES ('" + receptkomponent.getReceptId() + "', '" + receptkomponent.getRaavareId() + "', '" + receptkomponent.getNomNetto() + "', '" + receptkomponent.getTolerance() + "')";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
        try {
            Class.forName(this.driver);
            String sqlManipulation = "UPDATE ReceptKomp SET receptId = " + receptkomponent.getReceptId() + ", raavareId = '" + receptkomponent.getRaavareId() + "', nomNetto = '" + receptkomponent.getNomNetto() + "', tolerance = '" + receptkomponent.getTolerance() +"' WHERE receptId = '" + receptkomponent.getReceptId() + "' AND raavareId =" + receptkomponent.getRaavareId();

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReceptKomp(ReceptKompDTO receptKomponent) throws DALException {

        try {
            Class.forName(this.driver);
            String sqlManipulation = "DELETE FROM ReceptKomp where receptId ='" + receptKomponent.getReceptId() + "' AND raavareId=" + receptKomponent.getRaavareId();
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}

