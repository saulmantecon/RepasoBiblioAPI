package org.example.repasobiblioapi.service;

import org.example.repasobiblioapi.model.Biblioteca;
import org.example.repasobiblioapi.model.Libro;
import org.example.repasobiblioapi.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    public void delete(long  id) {
        libroRepository.deleteById(id);
    }

    public List<Libro> findLibroPorBibliotecaYAutor(long idBiblioteca, String autor) {
        return libroRepository.findByPrestadoIsFalseAndBiblioteca_IdBibliotecaAndAutor(idBiblioteca, autor);
    }

    public Libro cambiarEstadoPrestado(long idLibro) {
        Optional<Libro> libroOptional = libroRepository.findById(idLibro);

        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            libro.setPrestado(!libro.isPrestado());  // Invierte el estado
            return libroRepository.save(libro);  // Guarda y devuelve el libro actualizado
        } else {
           return null;
        }
    }

}
