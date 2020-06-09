package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IProduktBatchKompDAO;
import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchKompDAO implements IProduktBatchKompDAO {

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

        ProduktBatchKompDAO test = new ProduktBatchKompDAO();
        ProduktBatchKompDTO bruger = new ProduktBatchKompDTO();

        bruger.setPbId(4);
        bruger.setRbId(4);
        bruger.setTara(0.2);
        bruger.setNetto(4.0);
        bruger.setOprId(123);
        try {
            test.updateProduktBatchKomp(bruger);
        } catch (DALException e) {
            e.printStackTrace();

        }
    }



    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
        try {
            Class.forName(this.driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM ProduktBatchKomp WHERE pbId='" + pbId +  "' AND rbId ='" + rbId + "'";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);

            if(resultSet.next()){

                ProduktBatchKompDTO pkBatch = getProductBatchKomp(resultSet);
                connection.close();

                //Test
                System.out.println("pbID:" + pkBatch.getPbId() + " rbId:" + pkBatch.getRbId() + " Tara:" + pkBatch.getTara() + " Netto:" + pkBatch.getNetto() + " Operator:" + pkBatch.getOprId() );

                return pkBatch;


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
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {

        List<ProduktBatchKompDTO> pkBatchList = new ArrayList<>();

        try {
            Class.forName(this.driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM ProduktBatchKomp WHERE pbId='" + pbId +  "'";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);


            while (resultSet.next()){
                ProduktBatchKompDTO pkBatch = getProductBatchKomp(resultSet);

                pkBatchList.add(pkBatch);
            }
            connection.close();

            // ------------TEST--------------------
            String out = "pbId | rbId | tara | netto | operator";
            for(int i = 0; i<pkBatchList.size(); i++){

                out += "\n" + pkBatchList.get(i).getPbId() +"\t\t" + pkBatchList.get(i).getRbId() +"\t\t"+ pkBatchList.get(i).getTara() +"\t\t"+ pkBatchList.get(i).getNetto() +"\t\t"+ pkBatchList.get(i).getOprId();

            }
            System.out.println(out);
            // ------------TEST--------------------

            return pkBatchList;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }
    }



    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
        List<ProduktBatchKompDTO> pkBatchList = new ArrayList<>();

        try {
            Class.forName(this.driver);

            String sqlManipulation;

            sqlManipulation = "SELECT * FROM ProduktBatchKomp";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlManipulation);


            while (resultSet.next()){
                ProduktBatchKompDTO pkBatch = getProductBatchKomp(resultSet);

                pkBatchList.add(pkBatch);
            }
            connection.close();

            // ------------TEST--------------------
            String out = "pbId | rbId | tara | netto | operator";
            for(int i = 0; i<pkBatchList.size(); i++){

                out += "\n" + pkBatchList.get(i).getPbId() +"\t\t" + pkBatchList.get(i).getRbId() +"\t\t"+ pkBatchList.get(i).getTara() +"\t\t"+ pkBatchList.get(i).getNetto() +"\t\t"+ pkBatchList.get(i).getOprId();

            }
            System.out.println(out);
            // ------------TEST--------------------

            return pkBatchList;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }
    }

    private ProduktBatchKompDTO getProductBatchKomp(ResultSet resultSet) throws SQLException {
        ProduktBatchKompDTO batch = new ProduktBatchKompDTO();
        batch.setPbId(resultSet.getInt("pbId"));
        batch.setRbId(resultSet.getInt("rbId"));
        batch.setTara(resultSet.getDouble("tara"));
        batch.setNetto(resultSet.getDouble("netto"));
        batch.setOprId(resultSet.getInt("oprId"));
        return batch;
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO pkBatch) throws DALException {

        try {
            Class.forName(this.driver);

            String sqlManipulation = "INSERT ProduktBatchKomp VALUES ('" + pkBatch.getPbId() + "', '" + pkBatch.getRbId() + "', '" + pkBatch.getTara() + "', '" + pkBatch.getNetto() + "', '" + pkBatch.getOprId() + "')";

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
    public void updateProduktBatchKomp(ProduktBatchKompDTO pkBatch) throws DALException {


        try {
            Class.forName(this.driver);


            String sqlManipulation = "UPDATE ProduktBatchKomp SET pbId = " + pkBatch.getPbId() + ", rbId = '" + pkBatch.getRbId() + "', tara = '" + pkBatch.getTara()
                    + "', netto = '" + pkBatch.getNetto() + "', oprId = '" + pkBatch.getOprId() + "' WHERE pbId = '" + pkBatch.getPbId() + "' AND rbId =" + pkBatch.getRbId();


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
