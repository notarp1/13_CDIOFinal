package DTO;

import java.sql.Date;

public class ProduktBatchDTO {
    /** produkt batch id i området 1-99999999. Vælges af brugerne */
    int pbId;
    /** status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet */
    int status;
    /** recept id i området 1-99999999. Vælges af brugerne */
    int receptId;

    java.sql.Date pStartDato;

    long millis=System.currentTimeMillis();
    java.sql.Date date =new java.sql.Date(millis);


    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public java.sql.Date getDate(){
        return date;
    }

    public void setpStartDato(Date date){
        this.pStartDato = date;
    }
    public  java.sql.Date getpStartDato(){
        return pStartDato;
    }
}
