package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IReceptDAO;
import DTO.ReceptDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ismail
 */


public class ReceptDAO implements IReceptDAO {
    private String host = "primary.folkmann.it";
    private String port = "3306";
    private String username = "CDIO";
    private String password = "y1NzaOYI08FrdqzX";
    private String database = "/DTU_CDIOFinal";

    //Do not edit these variables
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://" + this.host + ":" + this.port + this.database + "?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    private ReceptDTO getRecept(ResultSet resultSet) throws SQLException {

        ReceptDTO recept = new ReceptDTO();
        recept.setReceptId(resultSet.getInt("receptId"));
        recept.setReceptNavn(resultSet.getString("receptNavn"));
        return recept;

    }


    @Override
    public ReceptDTO getRecept(int receptId) throws DALException {
        try {
            Class.forName(this.driver);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            String sqlManipulation = "SELECT * FROM Recept WHERE receptId=" + receptId;

            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            resultSet.next();
            ReceptDTO recept = getRecept(resultSet);
            connection.close();

            return recept;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("findes ikke");
            throw new DALException("Database fejl: " + e.getMessage());
        }

    }

    @Override
    public List<ReceptDTO> getReceptList() throws DALException {

        List<ReceptDTO> receptList = new ArrayList<>();

        try {
            Class.forName(this.driver);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            String sqlManipulation = "SELECT * FROM Recept";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);


           while (resultSet.next()) {
               ReceptDTO recept = getRecept(resultSet);

               receptList.add(recept);

           }
            connection.close();

           return receptList;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DALException("Database fejl: " + e.getMessage());
        }

    }


    @Override
    public void createRecept(ReceptDTO recept) throws DALException {
        try {
            Class.forName(this.driver);
            String sqlManipulation = "INSERT Recept VALUES ('" + recept.getReceptId() +  "', '" + recept.getReceptNavn() + "')";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws DALException {

        try {
            Class.forName(this.driver);
            String sqlManipulation = "UPDATE Recept SET receptId = " + recept.getReceptId() + ", receptNavn = '" + recept.getReceptNavn() + "' WHERE receptId = " + recept.getReceptId();
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteRecept(ReceptDTO recept) throws DALException {
        try {
            Class.forName(this.driver);
            String sqlManipulation = "DELETE FROM Recept where receptId =" + recept.getReceptId();
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

}
