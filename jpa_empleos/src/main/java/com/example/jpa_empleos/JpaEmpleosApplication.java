package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import com.example.jpa_empleos.repository.CategoriasRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;
    private final CategoriasJPARepository categoriasJPARepo;

    public JpaEmpleosApplication(
            CategoriasRepository categoriasRepo,
            CategoriasJPARepository categoriasJPARepo) {

        this.categoriasRepo = categoriasRepo;
        this.categoriasJPARepo = categoriasJPARepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarTodosOrdenados();
        buscarTodosOrdenadosDescendente();
    }

    /**
     * Método findAll - Interfaz JpaRepository
     */
    private void buscarTodasJPA() {

        List<Categoria> categorias = categoriasJPARepo.findAll();

        for (Categoria categoria : categorias) {
            System.out.println(
                    categoria.getId() + " " +
                    categoria.getDescripcion() + " " +
                    categoria.getNombre()
            );
        }
    }

    /**
     * Método deleteAllInBatch [Usar con precaución] - Interfaz JpaRepository
     */
    private void borrarTodasEnBloque() {
        categoriasJPARepo.deleteAllInBatch();
    }

    /**
     * Método findAll [Ordenados por nombre - Ascendente]
     */
    private void buscarTodosOrdenados() {

        System.out.println("=== ORDEN ASCENDENTE ===");

        List<Categoria> categorias =
                categoriasJPARepo.findAll(
                        Sort.by("nombre")
                );

        for (Categoria categoria : categorias) {
            System.out.println(
                    categoria.getId() + " " +
                    categoria.getDescripcion() + " " +
                    categoria.getNombre()
            );
        }
    }

    /**
     * Método findAll [Ordenados por nombre - Descendente]
     */
    private void buscarTodosOrdenadosDescendente() {

        System.out.println("=== ORDEN DESCENDENTE ===");

        List<Categoria> categorias =
                categoriasJPARepo.findAll(
                        Sort.by("nombre").descending()
                );

        for (Categoria categoria : categorias) {
            System.out.println(
                    categoria.getId() + " " +
                    categoria.getDescripcion() + " " +
                    categoria.getNombre()
            );
        }
    }
}