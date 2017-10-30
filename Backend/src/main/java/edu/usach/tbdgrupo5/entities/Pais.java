package edu.usach.tbdgrupo5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private int idpais;

    private String nombre;

    private int comentariosPositivos;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="idartista", nullable = true)
    private Artista artista;

    public int getIdPais() {
        return idpais;
    }

    public void setIdPais(int idPais) {
        this.idpais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getComentariosPositivos() {
        return comentariosPositivos;
    }

    public void setComentariosPositivos(int comentariosPositivos) {
        this.comentariosPositivos = comentariosPositivos;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
