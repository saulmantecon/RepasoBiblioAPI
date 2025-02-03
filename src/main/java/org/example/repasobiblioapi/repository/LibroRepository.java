package org.example.repasobiblioapi.repository;

import org.example.repasobiblioapi.model.Biblioteca;
import org.example.repasobiblioapi.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByIdLibro(Long idLibro);

    List<Libro> findByPrestadoIsFalseAndBiblioteca_IdBibliotecaAndAutor(long biblioteca_idBiblioteca, String autor);

}
