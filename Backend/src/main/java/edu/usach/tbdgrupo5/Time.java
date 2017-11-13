package edu.usach.tbdgrupo5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    private static Time instance = null;

    private String artistas;
    private String generos;
    private String grafo;
    private String mapa;

    protected Time() {
        // Exists only to defeat instantiation.
        this.artistas = this.getCurrentDateTime();
        this.generos = this.getCurrentDateTime();
        this.grafo = this.getCurrentDateTime();
        this.mapa = this.getCurrentDateTime();
    }

    public static Time getInstance() {
        if(instance == null) {
            instance = new Time();
        }
        return instance;
    }

    public String getCurrentDateTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now)); //2016/11/16 12:08:43
        return dtf.format(now).toString();
    }

    public String getCurrentDate()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now)); //2016/11/16
        return dtf.format(now).toString();
    }

    public String getArtistas() {
        return artistas;
    }

    public void setArtistas() {
        this.artistas = this.getCurrentDateTime();
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros() {
        this.generos = this.getCurrentDateTime();
    }

    public String getGrafo() {
        return grafo;
    }

    public void setGrafo() {
        this.grafo = this.getCurrentDateTime();
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa() {
        this.mapa = this.getCurrentDateTime();
    }
}
