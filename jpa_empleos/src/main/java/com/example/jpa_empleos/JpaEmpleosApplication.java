package com.example.jpa_empleos;


import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRespository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final VacantesRespository vacantesRepo;

    public JpaEmpleosApplication(VacantesRespository vacantesRepo) {
        this.vacantesRepo = vacantesRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarVacantesPorEstatus();
    }

    /**
     * Query Method: Buscar Vacantes por Estatus.
     */
    private void buscarVacantesPorEstatus() {

        List<Vacante> vacantesAprobadas = vacantesRepo
                .findByEstatus(EstatusVacante.Aprobada);

        System.out.println("Registros encontrados: "
                + vacantesAprobadas.size());

        for (Vacante vacante : vacantesAprobadas) {
            System.out.println(
                    vacante.getId() + ": "
                    + vacante.getNombre() + " - "
                    + vacante.getEstatus()
            );
        }
    }
}
