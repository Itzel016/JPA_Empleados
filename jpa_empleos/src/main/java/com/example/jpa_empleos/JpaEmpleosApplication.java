package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import com.example.jpa_empleos.repository.CategoriasRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        buscarTodosPaginacion();
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
     * Método deleteAllInBatch [Usar con precaución]
     * - Interfaz JpaRepository
     */
    private void borrarTodasEnBloque() {
        categoriasJPARepo.deleteAllInBatch();
    }

    /**
     * Método findAll [Ordenados por nombre - Ascendente]
     */
    private void buscarTodosOrdenados() {

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

    /**
     * Método findAll [Con Paginación]
     * - Interfaz JpaRepository
     */
    private void buscarTodosPaginacion() {

        Page<Categoria> page =
                categoriasJPARepo.findAll(
                        PageRequest.of(3, 5)
                );

        System.out.println(
                "Total Registros: " +
                page.getTotalElements()
        );

        System.out.println(
                "Total Paginas: " +
                page.getTotalPages()
        );

        for (Categoria c : page.getContent()) {
            System.out.println(
                    c.getId() + " " +
                    c.getNombre()
            );
        }
    }
}