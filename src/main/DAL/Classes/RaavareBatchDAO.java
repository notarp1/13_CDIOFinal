package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IRaavareBatchDAO;
import DAL.Statics;
import DTO.RaavareBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahra
 */

public class RaavareBatchDAO implements IRaavareBatchDAO {

    @Override
    public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {

        try {
            String sqlManipulation = "SELECT * FROM RaavareBatch WHERE rbId='" + rbId + "'";
            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            if (resultSet.next()) {
                RaavareBatchDTO batch = getRaavareBatch(resultSet);
                return batch;
            } else {
                throw new DALException("Kunne ikke finde Råvarebatch");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }

    @Override
    public RaavareBatchDTO getRaavareBatchId(int raavareId) throws DALException {

        try {
            String sqlManipulation = "SELECT * FROM RaavareBatch WHERE raavareId='" + raavareId + "'";
            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            if (resultSet.next()) {
                RaavareBatchDTO batch = getRaavareBatch(resultSet);
                return batch;
            } else {
                throw new DALException("Kunne ikke finde Råvarebatch Id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList() throws DALException, ClassNotFoundException {
        List<RaavareBatchDTO> raavareBatchList = new ArrayList<>();

        try {
            String sqlManipulation = "SELECT * FROM RaavareBatch";
            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            while (resultSet.next()) {
                RaavareBatchDTO batch = getRaavareBatch(resultSet);

                raavareBatchList.add(batch);
            }
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
            String sqlManipulation = "SELECT * FROM RaavareBatch WHERE rbId='" + rbId + "'";
            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            while (resultSet.next()) {
                RaavareBatchDTO batch = getRaavareBatch(resultSet);
                raavareBatchList.add(batch);
            }
            return raavareBatchList;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }

    private RaavareBatchDTO getRaavareBatch(ResultSet resultSet) throws SQLException {
        RaavareBatchDTO batch = new RaavareBatchDTO();
        batch.setRbId(resultSet.getInt("rbId"));
        batch.setRaavareId(resultSet.getInt("raavareId"));
        batch.setMaengde(resultSet.getDouble("maengde"));
        return batch;

    }

    @Override
    public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {

        try {

            String sqlManipulation = "INSERT RaavareBatch VALUES ('" + raavarebatch.getRbId() + "','" + raavarebatch.getRaavareId() + "','" + raavarebatch.getMaengde() + "')";
            Statics.DB.update(sqlManipulation);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {

        try {

            String sqlManipulation = "UPDATE RaavareBatch SET raavareId = '" + raavarebatch.getRaavareId() + "', maengde = '" + raavarebatch.getMaengde() + "' WHERE rbId = " + raavarebatch.getRbId();
            Statics.DB.update(sqlManipulation);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void deleteRaavareBatch(int rbId) throws DALException {

        try {

            String sqlManipulation = "DELETE FROM RaavareBatch WHERE rbId = " + rbId;
            Statics.DB.update(sqlManipulation);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }
}
