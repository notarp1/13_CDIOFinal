package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IProduktBatchDAO;
import DTO.ProduktBatchDTO;

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


    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
        return null;
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
        return null;
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {



    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {

    }
}
