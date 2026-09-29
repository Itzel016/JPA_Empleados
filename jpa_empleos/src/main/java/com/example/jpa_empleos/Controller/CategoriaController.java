package com.example.jpa_empleos.Controller;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriasRepository categoriasRepo;

    public CategoriaController(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }

    // 1. Consultar todas las categorías
    @GetMapping
    public Iterable<Categoria> obtenerTodas() {
        return categoriasRepo.findAll();
    }

    // 2. Buscar una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {

        return categoriasRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva categoría
    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriasRepo.save(categoria);
    }

    // 4. Actualizar una categoría
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Integer id,
            @RequestBody Categoria datos) {

        return categoriasRepo.findById(id)
                .map(categoria -> {
                    categoria.setNombre(datos.getNombre());
                    categoria.setDescripcion(datos.getDescripcion());

                    Categoria actualizada = categoriasRepo.save(categoria);

                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        if (!categoriasRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        categoriasRepo.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}