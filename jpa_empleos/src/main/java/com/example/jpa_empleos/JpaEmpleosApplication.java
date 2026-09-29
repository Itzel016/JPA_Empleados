package com.example.jpa_empleos;

import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;

    public JpaEmpleosApplication(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mostrarCategorias();
    }

    private void mostrarCategorias() {
        System.out.println("=== TODAS LAS CATEGORÍAS ===");

        categoriasRepo.findAll().forEach(categoria -> {
            System.out.println(categoria);
        });
    }
}