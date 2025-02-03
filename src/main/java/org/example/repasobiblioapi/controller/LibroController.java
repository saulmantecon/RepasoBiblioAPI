package org.example.repasobiblioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.repasobiblioapi.model.Biblioteca;
import org.example.repasobiblioapi.model.Libro;
import org.example.repasobiblioapi.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/libro")
@Tag(name = "libros", description = "api de libros")
public class LibroController {

    private final LibroService libroService;


    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping("saveLibro")
    @Operation(summary = "Guardar un nuevo libro",
            description = "Guardar un nuevo libro en la base de datos")
    public void saveLibro(@RequestBody Libro libro) {
        try {
            libroService.save(libro);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//saveLibro

    @DeleteMapping("deleteLibro/{id}")
    @Operation(summary = "elimina un nuevo libro",
            description = "elimina un nuevo libro en la base de datos")
    public void deleteLibro(@PathVariable long id) {
        try {
            libroService.delete(id);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//deleteLibro

    @GetMapping("libroPorBiblioYAutor/{idBiblioteca}/{autor}")
    @Operation(summary = "Buscar libro por biblioteca y autor",
            description = "Buscar libro por biblioteca y autor en la base de datos que no esté prestado")
    public List<Libro> getLibroPorBiblioYAutor(@PathVariable long idBiblioteca, @PathVariable String autor) {
        try {
            return libroService.findLibroPorBibliotecaYAutor(idBiblioteca, autor);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//getlibroPorBiblioYAutor


    @PostMapping("cambiarEstadoPrestado/{idLibro}")
    @Operation(summary = "cambiarEstadoPrestado de  un libro",
            description = "cambiarEstadoPrestado de un  libro en la base de datos")
    public Libro cambiarEstadoPrestado(@PathVariable long idLibro){
        try {
            return libroService.cambiarEstadoPrestado(idLibro);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//cambiarEstadoPrestado
}
