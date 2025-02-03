package org.example.repasobiblioapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    long idLibro;

    @Column(name = "titulo")
    String titulo;

    @Column(name = "autor")
    String autor;

    @Column(name = "ano_publicacion")
    int anoPublicacion;

    @Column(name = "prestado")
    boolean prestado;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_biblioteca", referencedColumnName = "id_biblioteca")
    Biblioteca biblioteca;

    public Libro() {
    }

    public Libro(long id_libro, String titulo, String autor, int ano_publicacion, boolean prestado, Biblioteca biblioteca) {
        this.idLibro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = ano_publicacion;
        this.prestado = prestado;
        this.biblioteca = biblioteca;
    }

    public long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(long id_libro) {
        this.idLibro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int ano_publicacion) {
        this.anoPublicacion = ano_publicacion;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
