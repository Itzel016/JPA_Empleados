package com.example.jpa_empleos;


import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.repository.CategoriasRepository;
import com.example.jpa_empleos.repository.PerfilesRepository;
import com.example.jpa_empleos.repository.UsuarioRepository;
import com.example.jpa_empleos.repository.VacantesRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;
    
    private final VacantesRepository vacantesRepo;
    private final PerfilesRepository perfilesRepo;
    private final UsuarioRepository usuarioRepo;

    public JpaEmpleosApplication(
            CategoriasRepository categoriasRepo,
           
            VacantesRepository vacantesRepo,
            PerfilesRepository perfilesRepo,
            UsuarioRepository usuarioRepo) {

        this.categoriasRepo = categoriasRepo;
       
        this.vacantesRepo = vacantesRepo;
        this.perfilesRepo = perfilesRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        crearPerfiles();
    }

    /**
     * Método para crear los perfiles.
     */
    private void crearPerfiles() {
        perfilesRepo.saveAll(obtenerPerfiles());
    }

    /**
     * Regresa una lista de los perfiles de la aplicación.
     */
    private List<Perfil> obtenerPerfiles() {
        List<Perfil> perfiles = new LinkedList<>();

        Perfil perfil1 = new Perfil();
        perfil1.setPerfil("SUPERVISOR");

        Perfil perfil2 = new Perfil();
        perfil2.setPerfil("ADMINISTRADOR");

        Perfil perfil3 = new Perfil();
        perfil3.setPerfil("USUARIO");

        perfiles.add(perfil1);
        perfiles.add(perfil2);
        perfiles.add(perfil3);

        return perfiles;
    }
}
