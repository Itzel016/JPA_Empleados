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
    buscarVacantesSalario();
}


/**
 * Query Method: Buscar Vacantes por rango de Salario (Between).
 */
private void buscarVacantesSalario() {

    // Primera consulta: rango de salarios sin ordenamiento explícito
    List<Vacante> vacantesSalariosEntre7000y14000 = vacantesRepo
            .findBySalarioBetween(7000.0, 14000.0);

    System.out.println("Registros encontrados: "
            + vacantesSalariosEntre7000y14000.size());

    for (Vacante vacante : vacantesSalariosEntre7000y14000) {
        System.out.println(
                vacante.getId() + ": "
                + vacante.getNombre()
                + " - $"
                + vacante.getSalario()
        );
    }

    System.out.println("-".repeat(100));

    // Segunda consulta: mismo rango, ordenado por salario descendente
    List<Vacante> vacantesSalariosEntre7000y14000Desc = vacantesRepo
            .findBySalarioBetweenOrderBySalarioDesc(
                    7000.0,
                    14000.0
            );

    System.out.println("Registros encontrados: "
            + vacantesSalariosEntre7000y14000Desc.size());

    for (Vacante vacante : vacantesSalariosEntre7000y14000Desc) {
        System.out.println(
                vacante.getId() + ": "
                + vacante.getNombre()
                + " - $"
                + vacante.getSalario()
        );
    }
}


}
