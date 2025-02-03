package org.example.repasobiblioapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "bibliotecas")
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_biblioteca")
    long idBiblioteca;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "localidad")
    String localidad;

    @Column(name = "categoria")
    String categoria;

    @JsonManagedReference
    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL)
    List<Libro> lista_Libros;

    public Biblioteca() {
    }

    public Biblioteca(long id_biblioteca, String nombre, String localidad, String categoria, List<Libro> lista_Libros) {
        this.idBiblioteca = id_biblioteca;
        this.nombre = nombre;
        this.localidad = localidad;
        this.categoria = categoria;
        this.lista_Libros = lista_Libros;
    }

    public long getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(long id_biblioteca) {
        this.idBiblioteca = id_biblioteca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Libro> getLista_Libros() {
        return lista_Libros;
    }

    public void setLista_Libros(List<Libro> lista_Libros) {
        this.lista_Libros = lista_Libros;
    }
}
