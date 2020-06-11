package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IProduktBatchKompDAO;
import DAL.Statics;
import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchKompDAO implements IProduktBatchKompDAO {

    /**
     * @Author Christian Pone
     */



    public static void main(String[] args) {

        ProduktBatchKompDAO test = new ProduktBatchKompDAO();
        ProduktBatchKompDTO bruger = new ProduktBatchKompDTO();

        bruger.setPbId(455);
        bruger.setRbId(4);
        bruger.setTara(0.2);
        bruger.setNetto(4.0);
        bruger.setOprId(123);
        try {
            test.createProduktBatchKomp(bruger);
        } catch (DALException e) {
            e.printStackTrace();

        }
    }



    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
        try {

            String sqlManipulation = "SELECT * FROM ProduktBatchKomp WHERE pbId='" + pbId +  "' AND rbId ='" + rbId + "'";

            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            if(resultSet.next()){
                ProduktBatchKompDTO pkBatch = getProductBatchKomp(resultSet);

                return pkBatch;


            }else {
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
            String sqlManipulation = "SELECT * FROM ProduktBatchKomp WHERE pbId='" + pbId +  "'";

            ResultSet resultSet = Statics.DB.get(sqlManipulation);

            while (resultSet.next()){
                ProduktBatchKompDTO pkBatch = getProductBatchKomp(resultSet);

                pkBatchList.add(pkBatch);
            }


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

            String sqlManipulation = "SELECT * FROM ProduktBatchKomp";
            ResultSet resultSet = Statics.DB.get(sqlManipulation);


            while (resultSet.next()){
                ProduktBatchKompDTO pkBatch = getProductBatchKomp(resultSet);

                pkBatchList.add(pkBatch);
            }

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

            String sqlManipulation = "INSERT ProduktBatchKomp VALUES ('" + pkBatch.getPbId() + "', '" + pkBatch.getRbId() + "', '" + pkBatch.getTara() + "', '" + pkBatch.getNetto() + "', '" + pkBatch.getOprId() + "')";
            Statics.DB.update(sqlManipulation);


        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }
    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO pkBatch) throws DALException {

        try {

            String sqlManipulation = "UPDATE ProduktBatchKomp SET pbId = " + pkBatch.getPbId() + ", rbId = '" + pkBatch.getRbId() + "', tara = '" + pkBatch.getTara()
                    + "', netto = '" + pkBatch.getNetto() + "', oprId = '" + pkBatch.getOprId() + "' WHERE pbId = '" + pkBatch.getPbId() + "' AND rbId =" + pkBatch.getRbId();

            Statics.DB.update(sqlManipulation);

        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void deleteProduktBatchKomp(ProduktBatchKompDTO pkBatch) throws DALException {

        try {

            String sqlManipulation = "DELETE FROM ProduktBatchKomp WHERE pbId = '" + pkBatch.getPbId() + "' AND rbId =" + pkBatch.getRbId();
            Statics.DB.update(sqlManipulation);

        }  catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new DALException("Database fejl");

        }
    }
}
