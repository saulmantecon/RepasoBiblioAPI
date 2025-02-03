package org.example.repasobiblioapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.repasobiblioapi.model.Biblioteca;
import org.example.repasobiblioapi.service.BibliotecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/biblioteca")
@Tag(name = "Bibliotecas", description = "Api de bibliotecas")
public class BibliotecaController {
    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @PostMapping("saveBiblioteca")
    @Operation(summary = "Guardar una nueva biblioteca",
            description = "Guarda una nueva biblioteca en la base de datos")
    public void saveBiblioteca(@RequestBody Biblioteca biblioteca) {
        try {
            bibliotecaService.saveBiblioteca(biblioteca);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//saveBiblioteca

    @GetMapping("bibliotecaPorCategoria/{categoria}")
    @Operation(summary = "Buscar biblioteca por categoria",
            description = "Busca una biblioteca en la base de datos por su categoria")
    public Optional<Biblioteca> getBibliotecaPorCategoria(@PathVariable String categoria) {
        try {
            return bibliotecaService.findByCategoria(categoria);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//getBibliotecaPorCategoria

    @GetMapping("bibliotecaPorLocalidad/{localidad}")
    @Operation(summary = "Buscar biblioteca por Localidad",
            description = "Busca una biblioteca en la base de datos por su Localidad")
    public Optional<Biblioteca> getBibliotecaPorLocalidad(@PathVariable String localidad) {
        try {
            return bibliotecaService.findByLocalidad(localidad);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//getBibliotecaPorCategoria

    @GetMapping("bibliotecaPorAutor/{autor}")
    @Operation(summary = "Buscar biblioteca por autor",
            description = "Busca una  o mas bibliotecas en la base de datos por su autor y que en esa biblio tengan un libro de ese autor")
    public List<Biblioteca> bibliotecaPorAutor(@PathVariable String autor) {
        try {
            return bibliotecaService.listarBibliotecasQueTenganUnLibroDeUnAutor(autor);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }//getbibliotecaPorAutor
}
