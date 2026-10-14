package com.example.jpa_empleos.Controller;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.CategoriasRepository;
import com.example.jpa_empleos.repository.VacantesRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacantes")
public class VacantesController {

    private final VacantesRepository vacantesRepo;
    private final CategoriasRepository categoriasRepo;

    public VacantesController(
            VacantesRepository vacantesRepo,
            CategoriasRepository categoriasRepo) {
        this.vacantesRepo = vacantesRepo;
        this.categoriasRepo = categoriasRepo;
    }

    /**
     * API: buscarVacantes
     * Consulta todas las vacantes registradas.
     */
    @GetMapping
    public ResponseEntity<List<Vacante>> buscarVacantes() {
        List<Vacante> vacantes = vacantesRepo.findAll();
        return ResponseEntity.ok(vacantes);
    }

    /**
     * API: guardarVacante
     * Registra una vacante recibida mediante JSON.
     */
    @PostMapping
    public ResponseEntity<?> guardarVacante(
            @RequestBody Vacante vacante) {

        // La vacante nueva debe generar su propio ID.
        vacante.setId(null);

        // Validar que se haya enviado una categoría.
        if (vacante.getCategoria() == null
                || vacante.getCategoria().getId() == null) {
            return ResponseEntity.badRequest()
                    .body("Debes indicar el ID de una categoría existente.");
        }

        // Buscar la categoría real en la base de datos.
        Integer idCategoria = vacante.getCategoria().getId();

        Categoria categoria = categoriasRepo.findById(idCategoria)
                .orElse(null);

        if (categoria == null) {
            return ResponseEntity.badRequest()
                    .body("No existe la categoría con ID " + idCategoria);
        }

        // Asociar la categoría existente.
        vacante.setCategoria(categoria);

        // Guardar la vacante en MySQL.
        Vacante guardada = vacantesRepo.save(vacante);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }
}
