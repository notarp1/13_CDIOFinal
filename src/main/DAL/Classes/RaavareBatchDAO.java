package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IRaavareBatchDAO;
import DAL.Statics;
import DTO.RaavareBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

/**
 * @author Zahra
 */

public class RaavareBatchDAO implements IRaavareBatchDAO {

    private String host = "primary.folkmann.it";
    private String port = "3306";
    private String username = "CDIO";
    private String password = "y1NzaOYI08FrdqzX";
    private String database = "/DTU_CDIOFinal";

    //Do not edit these variables
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://" + this.host + ":" + this.port + this.database + "?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void main(String[] args) {
        RaavareBatchDAO test = new RaavareBatchDAO();

        try {
            test.getRaavareBatchList(100);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }


    @Override
    public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {

        try {
            forName(this.driver);
            String sqlManipulation;
            sqlManipulation = "SELECT * FROM RaavareBatch WHERE rbId='" + rbId + "'";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            if (resultSet.next()) {
                RaavareBatchDTO batch = new RaavareBatchDTO();
                batch.setRbId(resultSet.getInt("rbId"));
                batch.setRaavareId(resultSet.getInt("raavareId"));
                batch.setMaengde(resultSet.getDouble("maengde"));
                connection.close();


                //Test
               System.out.println("RbId:" + batch.getRbId() + "RaavareId:" + batch.getRaavareId() + "Maengde:" + batch.getMaengde());

                return batch;
            } else {
                connection.close();
                throw new DALException("Kunne ikke finde raavaren");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }

    }


    @Override
    public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
        List<RaavareBatchDTO> raavareBatchList = new ArrayList<>();

        try {
            Class.forName(this.driver);
            String sqlManipulation;

            sqlManipulation = "SELECT * FROM RaavareBatch";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            while (resultSet.next()) {
                RaavareBatchDTO batch = new RaavareBatchDTO();
                batch.setRbId(resultSet.getInt("rbId"));
                batch.setRaavareId(resultSet.getInt("raavareId"));
                batch.setMaengde(resultSet.getDouble("maengde"));

                raavareBatchList.add(batch);

            }
            connection.close();

            //Test
            String out = "RbId | RaavareId | Maengde";
            for (int i = 0; i < raavareBatchList.size(); i++) {
                out += "\n" + raavareBatchList.get(i).getRaavareId() + "\t\t" + raavareBatchList.get(i).getRbId() + "\t\t" + raavareBatchList.get(i).getMaengde();
            }
            System.out.println(out);

            return raavareBatchList;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }


    @Override
    public List<RaavareBatchDTO> getRaavareBatchList(int rbId) throws DALException {

        List<RaavareBatchDTO> raavareBatchList = new ArrayList<>();

        try {
            Class.forName(this.driver);
            String sqlManipulation;

            sqlManipulation = "SELECT * FROM RaavareBatch WHERE rbId=" + rbId;

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            while (resultSet.next()) {
                RaavareBatchDTO batch = new RaavareBatchDTO();
                batch.setRbId(resultSet.getInt("rbId"));
                batch.setRaavareId(resultSet.getInt("raavareId"));
                batch.setMaengde(resultSet.getDouble("maengde"));

                raavareBatchList.add(batch);

            }
            connection.close();

            //Test
            String out = "RbId | RaavareId | Maengde";
            for (int i = 0; i < raavareBatchList.size(); i++) {
                out += "\n" + raavareBatchList.get(i).getRaavareId() + "\t\t" + raavareBatchList.get(i).getRbId() + "\t\t" + raavareBatchList.get(i).getMaengde();
            }
            System.out.println(out);

            return raavareBatchList;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }


    @Override
    public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
        System.out.println(raavarebatch);

        try {
            Class.forName(this.driver);

            String sqlManipulation = "INSERT RaavareBatch VALUES ('" + raavarebatch.getRbId() + "','" + raavarebatch.getRaavareId() + "','" + raavarebatch.getMaengde() + "')";
            Statics.DB.update(sqlManipulation);


            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            connection.close();



        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }


    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
        try {
            forName(this.driver);

            String sqlManipulation = "UPDATE RaavareBatch SET raavareId = '" + raavarebatch.getRaavareId() + "', Maengde = '" + raavarebatch.getMaengde() + "' WHERE rbId = " + raavarebatch.getRbId();
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void deleteRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
        try {
            Class.forName(this.driver);

            String sqlManipulation = "DELETE FROM RaavareBatch WHERE rbId = " + raavarebatch.getRbId();

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            connection.close();

        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }

    }
}