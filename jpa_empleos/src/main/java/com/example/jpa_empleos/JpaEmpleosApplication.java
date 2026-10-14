
package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final VacantesRepository vacantesRepo;

    public JpaEmpleosApplication(VacantesRepository vacantesRepo) {
        this.vacantesRepo = vacantesRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarVacantes();
    }

    /**
     * Método findAll - Interfaz JpaRepository
     */
    private void buscarVacantes() {
        List<Vacante> vacantes = vacantesRepo.findAll();

        for (Vacante vacante : vacantes) {
            System.out.println(
                vacante.getId() + ". " + vacante.getNombre()
            );
        }
    }
}

