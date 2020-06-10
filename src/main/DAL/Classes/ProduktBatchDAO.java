package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IProduktBatchDAO;
import DTO.ProduktBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchDAO implements IProduktBatchDAO {

    /**
     * @Author Christian Pone
     */


    private String host = "primary.folkmann.it";
    private String port = "3306";
    private String username = "CDIO";
    private String password = "y1NzaOYI08FrdqzX";
    private String database = "/DTU_CDIOFinal";

    //Do not edit these variables
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://" + this.host + ":" + this.port + this.database + "?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public static void main(String[] args) {
        ProduktBatchDAO test = new ProduktBatchDAO();
        ProduktBatchDTO bruger = new ProduktBatchDTO();

        bruger.setPbId(12);
        bruger.setStatus(3);
        bruger.setReceptId(13);
        try {
            test.deleteProduktBatch(bruger);
        } catch (DALException e) {
            e.printStackTrace();

        }
    }


    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {

        try {
            Class.forName(this.driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM ProduktBatch WHERE pbID='" + pbId + "'";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            if(resultSet.next()){

                ProduktBatchDTO batch = new ProduktBatchDTO();
                batch.setPbId(resultSet.getInt("pbId"));
                batch.setStatus(resultSet.getInt("status"));
                batch.setReceptId(resultSet.getInt("receptId"));
                connection.close();

                //Test
                System.out.println("pbID:" + batch.getPbId() + " Status:" + batch.getStatus() + " ReceptId:" + batch.getReceptId());
                return batch;


            }else {
                connection.close();
                throw new DALException("Kunne ikke finde produkt batch");
            }

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {

            List<ProduktBatchDTO> produktListe = new ArrayList<>();

        try {
            Class.forName(this.driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM ProduktBatch ";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);


            while (resultSet.next()){
                ProduktBatchDTO batch = new ProduktBatchDTO();
                batch.setPbId(resultSet.getInt("pbId"));
                batch.setStatus(resultSet.getInt("status"));
                batch.setReceptId(resultSet.getInt("receptId"));

                produktListe.add(batch);
            }
            connection.close();

            // ------------TEST--------------------
            String out = "pbId | status | receptId";
            for(int i = 0; i<produktListe.size(); i++){

                out += "\n" + produktListe.get(i).getPbId() +"\t\t" + produktListe.get(i).getStatus() +"\t\t"+ produktListe.get(i).getReceptId();

            }
            System.out.println(out);
            // ------------TEST--------------------

            return produktListe;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }



    @Override
    public void createProduktBatch(ProduktBatchDTO pBatch) throws DALException {

        try {
            Class.forName(this.driver);

            String sqlManipulation = "INSERT ProduktBatch VALUES ('" + pBatch.getPbId() + "', '" + pBatch.getStatus() + "', '" + pBatch.getReceptId() + "')";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            connection.close();


        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO pBatch) throws DALException {

        try {
            Class.forName(this.driver);


            String sqlManipulation = "UPDATE ProduktBatch SET pbId = " + pBatch.getPbId() + ", status = '" + pBatch.getStatus() + "', receptId = '" + pBatch.getReceptId() + "' WHERE pbId = " + pBatch.getPbId();


            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            connection.close();


        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }


    }

    @Override
    public void deleteProduktBatch(ProduktBatchDTO pBatch) throws DALException {

        try {
            Class.forName(this.driver);


            String sqlManipulation = "DELETE FROM ProduktBatch WHERE pbId = " + pBatch.getPbId();


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
