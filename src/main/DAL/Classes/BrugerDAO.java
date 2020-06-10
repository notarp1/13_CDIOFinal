package DAL.Classes;

import DAL.DALException;
import DAL.Interfaces.IBrugerDAO;
import DAL.Statics;
import DTO.BrugerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Theis
 */
public class BrugerDAO implements IBrugerDAO {
    public BrugerDAO() {}

    @Override
    public BrugerDTO getBruger(int oprId) throws DALException {
        try {
            ResultSet resultSet = Statics.DB.get("SELECT * FROM Bruger WHERE oprId LIKE (" + oprId + ") AND active = 1");
            if (resultSet.next()) {
                BrugerDTO bruger = new BrugerDTO();
                bruger.setOprId(resultSet.getInt("oprId"));
                bruger.setOprNavn(resultSet.getString("oprNavn"));
                bruger.setIni(resultSet.getString("ini"));
                bruger.setCpr(resultSet.getString("cpr"));

                ResultSet roles = Statics.DB.get("SELECT * FROM BrugerRoller WHERE oprId LIKE (" + oprId + ")");
                List<String> roller = new ArrayList<String>();
                while (roles.next()) {
                    roller.add(roles.getString("rolle"));
                }
                bruger.setRoller(roller);

                return bruger;
            }
            else {
                throw new DALException("Kunne ikke finde bruger");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl: " + e.getMessage());
        }
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        List<BrugerDTO> liste = new ArrayList<BrugerDTO>();
        try {
            ResultSet brugere = Statics.DB.get("SELECT * FROM Bruger ORDER BY oprId");
            ResultSet brugerRoller = Statics.DB.get("SELECT * FROM BrugerRoller ORDER BY oprId ASC");
            brugerRoller.next();
            while (brugere.next()) {
                BrugerDTO bruger = new BrugerDTO();
                bruger.setOprId(brugere.getInt("oprId"));
                bruger.setOprNavn(brugere.getString("oprNavn"));
                bruger.setIni(brugere.getString("ini"));
                bruger.setCpr(brugere.getString("cpr"));

                List<String> roller = new ArrayList<String>();
                if (brugerRoller.getInt("oprId") == bruger.getOprId()) {
                    roller.add(brugerRoller.getString("rolle"));
                    brugerRoller.next();
                }
                bruger.setRoller(roller);

                liste.add(bruger);
            }
            return liste;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl: " + e.getMessage());
        }
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {
        try {
            Statics.DB.update("INSERT INTO Bruger VALUES (" + opr.getOprId() + ", \"" + opr.getOprNavn() + "\", \"" + opr.getIni() + "\", \"" + opr.getCpr() + "\", 1)");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl: " + e.getMessage());
        }
    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {
        try {
            Statics.DB.update("UPDATE Bruger SET oprNavn = \"" + opr.getOprNavn() + "\", ini = \"" + opr.getIni() + "\", cpr = \"" + opr.getCpr() + "\" WHERE oprId LIKE (" + opr.getOprId() + ")");
            Statics.DB.update("DELETE FROM BrugerRoller WHERE oprId LIKE (" + opr.getOprId() + ")");
            for (int i = 0; i < opr.getRoller().size(); i++) {
                Statics.DB.update("INSERT INTO BrugerRoller VALUES (" + opr.getOprId() + ", \"" + opr.getRoller().get(i) + "\")");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl: " + e.getMessage());
        }
    }

    @Override
    public void deleteBruger(int oprId) throws DALException {
        try {
            Statics.DB.update("UPDATE Bruger SET active = 0 WHERE oprId LIKE (" + oprId + ")");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DALException("Database fejl: " + e.getMessage());
        }
    }

    @Override
    public void deleteBruger(BrugerDTO opr) throws DALException {
        this.deleteBruger(opr.getOprId());
    }
}
