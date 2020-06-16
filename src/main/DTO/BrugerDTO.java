package DTO;

import java.util.List;

public class BrugerDTO {
    /** Bruger id i området 1-99999999. Vælges af brugerne */
    int oprId;
    /** Bruger navn (opr_navn) min. 2 max. 20 karakterer */
    String oprNavn;
    /** Bruger initialer min. 2 max. 4 karakterer */
    String ini;
    /** Bruger cpr-nr 10 karakterer */
    String cpr;
    /** Liste over roller */
    List<String> roller;


    public int getOprId() {
        return oprId;
    }

    public String getOprNavn() {
        return oprNavn;
    }

    public String getIni() {
        return ini;
    }

    public String getCpr() {
        return cpr;
    }

    public List<String> getRoller() {
        return roller;
    }

    public void setOprId(int oprId) {
        this.oprId = oprId;
    }

    public void setOprNavn(String oprNavn) {
        this.oprNavn = oprNavn;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public void setRoller(List<String> roller) {
        this.roller = roller;
    }

    public boolean isAdministrator() {
        return this.roller.contains("Administrator");
    }

    public boolean isFarmaceut() {
        return this.roller.contains("Farmaceut");
    }

    public boolean isProduktionsleder() {
        return this.roller.contains("Produktionsleder");
    }

    public boolean isLaborant() {
        return this.roller.contains("Laborant");
    }

    @Override
    public String toString() {
        return "Bruger: (" + this.oprId + ") [" + this.ini + "] " + this.oprNavn + ", CPR: " + this.cpr + ", Roller: " + this.roller.toString();
    }
}
