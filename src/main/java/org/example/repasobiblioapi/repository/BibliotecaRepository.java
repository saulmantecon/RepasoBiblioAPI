package org.example.repasobiblioapi.repository;

import jakarta.persistence.EntityManager;
import org.example.repasobiblioapi.model.Biblioteca;
import org.example.repasobiblioapi.model.Libro;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

    Optional<Biblioteca> findByCategoria(String categoria);

    Optional<Biblioteca> findByLocalidad(String localidad);


        //@Query("SELECT b FROM Biblioteca b WHERE b.idBiblioteca IN (SELECT l.biblioteca.idBiblioteca FROM Libro l WHERE l.autor = :autor)")
     //   List<Biblioteca> findBibliotecasByAutor(@Param("autor") String autor);

    @Query("SELECT DISTINCT b FROM Biblioteca b JOIN b.lista_Libros l WHERE l.autor = :autor")
    List<Biblioteca> findBibliotecasByAutor(@Param("autor") String autor); //encontrar bibliotecas que tengan por lo menos un libro de x autor

    @Query("SELECT COUNT(l) FROM Libro l WHERE l.biblioteca.idBiblioteca = :idBiblioteca")
    long contarLibrosPorBiblioteca(@Param("idBiblioteca") long idBiblioteca);//Obtener la cantidad de libros que tiene una biblioteca en base a su id_biblioteca.

    @Query("SELECT b FROM Biblioteca b WHERE SIZE(b.lista_Libros) > :cantidad")
    List<Biblioteca> findBibliotecasConMasDeXLibros(@Param("cantidad") int cantidad); //Obtener una lista de bibliotecas que tengan al menos X libros.

    @Query("SELECT l FROM Libro l WHERE l.prestado = false")
    List<Libro> findLibrosDisponibles();//Obtener la lista de libros que NO están prestados (prestado = false).

    @Query("SELECT l FROM Libro l WHERE l.anoPublicacion > :ano")
    List<Libro> findLibrosPublicadosDespuesDe(@Param("ano") int ano);//Obtener libros publicados después del año 2000, por ejemplo.


    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo%")
    List<Libro> buscarLibrosPorTitulo(@Param("titulo") String titulo); //Buscar libros cuyo título contenga cierta palabra.


    //@Query("SELECT l FROM Libro l WHERE l.fechaIngreso >= :fecha")
    //List<Libro> findLibrosRecientes(@Param("fecha") LocalDate fecha);  Obtener todos los libros que se agregaron en los últimos 30 días.

   // @Query("SELECT l FROM Libro l WHERE l.anoPublicacion BETWEEN :fechaInicio AND :fechaFin")
   // List<Libro> findLibrosEntreFechas(@Param("fechaInicio") int fechaInicio, @Param("fechaFin") int fechaFin);

  //  @Query("SELECT b FROM Biblioteca b WHERE b.fechaCreacion < :fecha")
   // List<Biblioteca> findBibliotecasAntiguas(@Param("fecha") LocalDate fecha);
 // LocalDate fechaLimite = LocalDate.of(2000, 1, 1);
   // List<Biblioteca> bibliotecasViejas = bibliotecaRepository.findBibliotecasAntiguas(fechaLimite);








}
