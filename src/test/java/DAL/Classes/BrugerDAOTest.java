package DAL.Classes;

import DAL.DALException;
import DAL.Statics;
import DTO.BrugerDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

/**
 * @Author Theis
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BrugerDAOTest {
    BrugerDAO dao = new BrugerDAO();
    int testID = 100000000; // ID, som ikke bruges normalt 1-99999999

    @Test
    public void AcreateBruger() {
        try{
            BrugerDTO bruger = new BrugerDTO();
            bruger.setOprId(testID);
            bruger.setOprNavn("Unit Tester");
            bruger.setIni("TEST");
            bruger.setCpr("0000000000");
            dao.createBruger(bruger);
            assert true;
        } catch (DALException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void BgetBruger() {
        try {
            BrugerDTO bruger = dao.getBruger(testID);
            if (bruger.getOprId() == testID) {
                System.out.println(bruger);
                assert true;
            } else {
                assert false;
            }
        } catch (DALException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void CupdateBruger() {
        try{
            BrugerDTO bruger = dao.getBruger(testID);
            bruger.setOprNavn("Tester Unit");
            dao.updateBruger(bruger);
            bruger = dao.getBruger(testID);
            if (bruger.getOprNavn().equals("Tester Unit")) {
                assert true;
            } else {
                assert false;
            }
        } catch (DALException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void DdeleteBruger() {
        try{
            dao.deleteBruger(testID);
            BrugerDTO bruger = dao.getBruger(testID);
            assert false;
        } catch (DALException e) {
            if (e.getMessage().equals("Kunne ikke finde bruger")) {
                assert true;
            }
        }
        try {
            Statics.DB.update("DELETE FROM Bruger WHERE oprId LIKE (" + testID + ")");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
