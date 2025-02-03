package org.example.repasobiblioapi.service;

import org.example.repasobiblioapi.model.Biblioteca;
import org.example.repasobiblioapi.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {
    private final BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public void saveBiblioteca(Biblioteca biblioteca) {
        bibliotecaRepository.save(biblioteca);
    }

    public Optional<Biblioteca> findByCategoria(String categoria) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findByCategoria(categoria);
        return bibliotecaOptional.map(item -> new Biblioteca(
                item.getIdBiblioteca(),
                item.getNombre(),
                item.getLocalidad(),
                item.getCategoria(),
                item.getLista_Libros()));
    }

    public Optional<Biblioteca> findByLocalidad(String localidad) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findByLocalidad(localidad);
        return bibliotecaOptional.map(item ->new Biblioteca(
                item.getIdBiblioteca(),
                item.getNombre(),
                item.getLocalidad(),
                item.getCategoria(),
                item.getLista_Libros()));

    }

    public List<Biblioteca> listarBibliotecasQueTenganUnLibroDeUnAutor(String autor) {
        return bibliotecaRepository.findBibliotecasByAutor(autor);
    }
}
