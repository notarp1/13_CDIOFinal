package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IProduktBatchDAO;
import DAL.Statics;
import DTO.ProduktBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchDAO implements IProduktBatchDAO {

    /**
     * @Author Christian Pone
     */

    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {


        try {

            String sqlManipulation = "SELECT * FROM ProduktBatch WHERE pbID='" + pbId + "'";

            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            if(resultSet.next()){

                ProduktBatchDTO batch = new ProduktBatchDTO();
                batch.setPbId(resultSet.getInt("pbId"));
                batch.setReceptId(resultSet.getInt("receptId"));
                batch.setStatus(resultSet.getInt("status"));
                batch.setDate(resultSet.getDate("dato"));
                batch.setpStartDato(resultSet.getDate("pStartDato"));

                return batch;

            }else {
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

            String sqlManipulation = "SELECT * FROM ProduktBatch ";

            ResultSet resultSet = Statics.DB.get(sqlManipulation);


            while (resultSet.next()){
                ProduktBatchDTO batch = new ProduktBatchDTO();
                batch.setPbId(resultSet.getInt("pbId"));
                batch.setReceptId(resultSet.getInt("receptId"));
                batch.setStatus(resultSet.getInt("status"));
                batch.setDate(resultSet.getDate("dato"));
                batch.setpStartDato(resultSet.getDate("pStartDato"));

                produktListe.add(batch);
            }

            return produktListe;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }



    @Override
    public void createProduktBatch(ProduktBatchDTO pBatch) throws DALException {

        try {
            String sqlManipulation = "INSERT ProduktBatch VALUES ('" + pBatch.getPbId() + "', '" + pBatch.getReceptId() + "', '" + pBatch.getStatus() + "', '" + pBatch.getDate() + "', " + "null" + ")";
            Statics.DB.update(sqlManipulation);


        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }

    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO pBatch) throws DALException {
        try {

            String sqlManipulation = "UPDATE ProduktBatch SET pbId = '" + pBatch.getPbId() + "', receptId = '" + pBatch.getReceptId() +  "', status = '" + pBatch.getStatus() + "', pStartDato = '" + pBatch.getpStartDato() + "' WHERE pbId = " + pBatch.getPbId();
            Statics.DB.update(sqlManipulation);

        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }


    }

    @Override
    public void deleteProduktBatch(ProduktBatchDTO pBatch) throws DALException {

        try {
            String sqlManipulation = "DELETE FROM ProduktBatch WHERE pbId = " + pBatch.getPbId();
            Statics.DB.update(sqlManipulation);


        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }


    }
}
