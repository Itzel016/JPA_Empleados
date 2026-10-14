package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
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

        // Ejercicio 1: consultar todas las vacantes y sus categorías
       // buscarVacantes();

        // Ejercicio 2: guardar una nueva vacante
        // Se deja comentado para evitar insertar duplicados
        // cada vez que se inicia la aplicación.
         guardarVacante();
    }

    /**
     * Recuperar todas las vacantes y sus categorías.
     */
    public void buscarVacantes() {

        List<Vacante> vacantes = vacantesRepo.findAll();

        System.out.println("\n=== TODAS LAS VACANTES Y SUS CATEGORÍAS ===");

        for (Vacante vacante : vacantes) {
            String nombreCategoria = vacante.getCategoria() != null
                    ? vacante.getCategoria().getNombre()
                    : "Sin categoría";

            System.out.println(
                    vacante.getId() + ". "
                            + vacante.getNombre()
                            + " -> "
                            + nombreCategoria);
        }
    }

    /** * Guardar una vacante */
    private void guardarVacante() {
        Vacante vacante = new Vacante();
        vacante.setNombre("Desarrollador Java PRO");
        vacante.setDescripcion("Se busca desarrollador con experiencia en Spring Boot y JPA.");
        vacante.setFecha(LocalDate.now());
        vacante.setSalario(25000.0);
        vacante.setEstatus(EstatusVacante.Creada); // Enum, asegúrate que exista en tu proyecto
        vacante.setDestacado(1);
        vacante.setImagen("logo_empresa.png");
        vacante.setDetalles("Trabajo remoto con horario flexible. Beneficios y capacitación incluidos."); // Crear una
                                                                                                          // categoría
                                                                                                          // asociada
        Categoria categoria = new Categoria();
        categoria.setId(38); // Asegúrate de que este ID exista en tu base de datos
        // Si ya existe en la BD, solo asignas el id
        // // o puedes crear una nueva:

        // categoria.setNombre("Tecnología");
        // // categoria.setDescripcion("Empleos relacionados con desarrollo y TI.");
        vacante.setCategoria(categoria);
        vacantesRepo.save(vacante);
    }
}
