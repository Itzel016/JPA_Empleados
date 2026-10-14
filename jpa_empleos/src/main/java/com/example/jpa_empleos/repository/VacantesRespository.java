package com.example.jpa_empleos.repository;



import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRespository
        extends JpaRepository<Vacante, Integer> {

    // Buscar vacantes por estatus
    List<Vacante> findByEstatus(EstatusVacante estatus);

    // Buscar vacantes destacadas y aprobadas, por ID descendente
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(
            int destacado,
            EstatusVacante estatus
    );

    // Buscar vacantes por rango de salarios
    List<Vacante> findBySalarioBetween(
            Double salarioInicial,
            Double salarioFinal
    );

    // Buscar vacantes por rango de salarios, ordenadas de mayor a menor
    List<Vacante> findBySalarioBetweenOrderBySalarioDesc(
            Double salarioInicial,
            Double salarioFinal
    );
}
