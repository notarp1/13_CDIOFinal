package controller.Classes;

import DAL.Classes.BrugerDAO;
import DAL.Interfaces.IBrugerDAO;
import controller.ControllerException;
import controller.Interfaces.IBrugerController;
import org.junit.Test;

public class BrugerControllerTest {
    IBrugerController controller = new BrugerController();
    int testID = 1;

    @Test
    public void getBruger() {
        try {
            controller.getBruger(testID);
            assert true;
        } catch (ControllerException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void getBrugerIDTestLow() {
        try {
            System.out.println(controller.getBruger(0));
            assert false;
        } catch (ControllerException e) {
            assert true;
        }
    }

    @Test
    public void getBrugerIDTestHigh() {
        try {
            System.out.println(controller.getBruger(99999999+1));
            assert false;
        } catch (ControllerException e) {
            assert true;
        }
    }
}
