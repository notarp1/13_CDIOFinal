package DAL.Classes;

import DAL.DALException;
import DAL.DBTablePrinter;
import DAL.Interfaces.IProduktBatchDAO;
import DTO.ProduktBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchDAO implements IProduktBatchDAO {


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

        try {
            test.getProduktBatchList();
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


            String out = "pbId | status | receptId";
            for(int i = 0; i<produktListe.size(); i++){

                out += "\n" + produktListe.get(i).getPbId() +"\t\t" + produktListe.get(i).getStatus() +"\t\t"+ produktListe.get(i).getReceptId();

            }
            System.out.println(out);


            return produktListe;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }



    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {

        try {
            Class.forName(this.driver);

            String sqlManipulation = "INSERT Userlist VALUES ('" + user.getUserId() + "', '" + user.getUserName() + "', '" + user.getIni() + "', '" + user.getCpr() + "', '" + user.getPassword() + "')";

            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            for (String role : user.getRoles()) {
                sqlManipulation = "INSERT UserRoles VALUES( " + user.getUserId() + " , \"" + role + "\")";
                statement.executeUpdate(sqlManipulation);
            }
            connection.close();


        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {

    }
}
