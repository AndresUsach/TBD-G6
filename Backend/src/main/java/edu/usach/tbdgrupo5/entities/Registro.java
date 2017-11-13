package edu.usach.tbdgrupo5.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name="Registro.findAll", query="SELECT r FROM Registro r")
public class Registro implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idregistro;

    private int comentariosNegativos;

    private int comentariosPositivos;

    private int comentariosNeutros;

    private String nombre;

    private String fecha;

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public int getComentariosNegativos() {
        return comentariosNegativos;
    }

    public void setComentariosNegativos(int comentariosNegativos) {
        this.comentariosNegativos = comentariosNegativos;
    }

    public int getComentariosPositivos() {
        return comentariosPositivos;
    }

    public void setComentariosPositivos(int comentariosPositivos) {
        this.comentariosPositivos = comentariosPositivos;
    }

    public int getComentariosNeutros() {
        return comentariosNeutros;
    }

    public void setComentariosNeutros(int comentariosNeutros) {
        this.comentariosNeutros = comentariosNeutros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Registro() {
    }
}
