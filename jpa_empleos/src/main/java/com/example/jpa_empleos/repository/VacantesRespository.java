package com.example.jpa_empleos.repository;




import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRespository
        extends JpaRepository<Vacante, Integer> {

    // Buscar vacantes por estatus
    List<Vacante> findByEstatus(EstatusVacante estatus);

    // Buscar vacantes destacadas y aprobadas, ordenadas por ID descendente
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(
            int destacado,
            EstatusVacante estatus
    );
}
