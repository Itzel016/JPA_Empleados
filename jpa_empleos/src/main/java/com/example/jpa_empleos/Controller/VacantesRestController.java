
package com.example.jpa_empleos.Controller;

import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRespository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa-vacante")
public class VacantesRestController {

    private final VacantesRespository vacantesRepo;

    public VacantesRestController(
            VacantesRespository vacantesRepo) {
        this.vacantesRepo = vacantesRepo;
    }

    // 1. Varios estatus
    // GET /api/jpa-vacante/estatus-varios?estatus=Eliminada,Creada
    @GetMapping("/estatus-varios")
    public ResponseEntity<?> buscarPorVariosEstatus(
            @RequestParam("estatus") String estatus) {

        try {
            String[] nombres = estatus.split(",");
            EstatusVacante[] listaEstatus =
                    new EstatusVacante[nombres.length];

            for (int i = 0; i < nombres.length; i++) {
                listaEstatus[i] =
                        EstatusVacante.valueOf(nombres[i].trim());
            }

            return ResponseEntity.ok(
                    vacantesRepo.findByEstatusIn(listaEstatus)
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    "Estatus no válido: " + estatus
            );
        }
    }

    // 2. Rango de salario
    // GET /api/jpa-vacante/salario?minimo=7000&maximo=14000
    @GetMapping("/salario")
    public ResponseEntity<?> buscarPorSalario(
            @RequestParam("minimo") Double minimo,
            @RequestParam("maximo") Double maximo) {

        if (minimo > maximo) {
            return ResponseEntity.badRequest().body(
                    "El mínimo no puede superar al máximo."
            );
        }

        return ResponseEntity.ok(
                vacantesRepo.findBySalarioBetween(minimo, maximo)
        );
    }

    // 3. Rango de salario descendente
    // GET /api/jpa-vacante/salario/desc?minimo=7000&maximo=14000
    @GetMapping("/salario/desc")
    public ResponseEntity<?> buscarPorSalarioDesc(
            @RequestParam("minimo") Double minimo,
            @RequestParam("maximo") Double maximo) {

        if (minimo > maximo) {
            return ResponseEntity.badRequest().body(
                    "El mínimo no puede superar al máximo."
            );
        }

        return ResponseEntity.ok(
                vacantesRepo.findBySalarioBetweenOrderBySalarioDesc(
                        minimo, maximo
                )
        );
    }

    // 4. Destacadas por estatus
    // GET /api/jpa-vacante/destacadas?destacado=1&estatus=Aprobada
    @GetMapping("/destacadas")
    public ResponseEntity<?> buscarDestacadasPorEstatus(
            @RequestParam("destacado") int destacado,
            @RequestParam("estatus") String estatus) {

        try {
            EstatusVacante estado =
                    EstatusVacante.valueOf(estatus.trim());

            return ResponseEntity.ok(
                    vacantesRepo
                            .findByDestacadoAndEstatusOrderByIdDesc(
                                    destacado, estado
                            )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    "Estatus no válido: " + estatus
            );
        }
    }

    // 5. Buscar por un estatus
    // GET /api/jpa-vacante/estatus?estatus=Aprobada
    @GetMapping("/estatus")
    public ResponseEntity<?> buscarPorEstatus(
            @RequestParam("estatus") String estatus) {

        try {
            EstatusVacante estado =
                    EstatusVacante.valueOf(estatus.trim());

            List<Vacante> vacantes =
                    vacantesRepo.findByEstatus(estado);

            return ResponseEntity.ok(vacantes);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    "Estatus no válido: " + estatus
            );
        }
    }
}
