package DTO;

public class RaavareDTO {
    /** raavare id i området 1-99999999 vælges af brugerne */
    int raavareId;
    /** min. 2 max. 20 karakterer */
    String raavareNavn;
    /** min. 2 max. 20 karakterer */
    String leverandoer;

    public int getRaavareId() {
        return raavareId;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

    public String getRaavareNavn() {
        return raavareNavn;
    }

    public void setRaavareNavn(String raavareNavn) {
        this.raavareNavn = raavareNavn;
    }

    public String getLeverandoer() {
        return leverandoer;
    }

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }
}
