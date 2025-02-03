package org.example.repasobiblioapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.repasobiblioapi.model.Biblioteca;
import org.example.repasobiblioapi.model.Libro;
import org.example.repasobiblioapi.service.BibliotecaService;
import org.example.repasobiblioapi.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/libro")
@Tag(name = "libros", description = "API de libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // ✅ Guardar un nuevo libro
    @PostMapping("/save")
    @Operation(summary = "Guardar un nuevo libro", description = "Guarda un nuevo libro en la base de datos")
    public ResponseEntity<Void> saveLibro(@RequestBody Libro libro) {
        try {
            libroService.saveLibro(libro);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // ✅ Eliminar un libro por ID
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un libro", description = "Eliminar un libro de la base de datos")
    public ResponseEntity<Void> deleteLibro(@PathVariable int id) {
        try {
            libroService.deleteLibro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // ✅ Marcar un libro como prestado
    @PutMapping("/{id}/prestar")
    @Operation(summary = "Marcar un libro como prestado", description = "Cambia la variable de prestado a true")
    public ResponseEntity<Void> cambiarLibroAPrestado(@PathVariable Long id) {
        try {
            libroService.libroPrestado(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // ✅ Buscar libros por biblioteca y género
    @GetMapping("/buscarLibros")
    @Operation(summary = "Buscar libros por biblioteca y género", description = "Busca libros en la base de datos")
    public ResponseEntity<List<Libro>> buscarLibrosPorXCosas(
            @RequestParam Long idBiblioteca,
            @RequestParam String genero) {
        try {
            List<Libro> libros = libroService.findByBibliotecaAndAutor(idBiblioteca, genero);
            return ResponseEntity.ok(libros);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
