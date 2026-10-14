package com.example.jpa_empleos;


import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.repository.CategoriasRepository;
import com.example.jpa_empleos.repository.PerfilesRepository;
import com.example.jpa_empleos.repository.UsuarioRepository;
import com.example.jpa_empleos.repository.VacantesRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.jpa_empleos.models.Usuario;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
    crearUsuarioConPerfiles();
}

    
/**
 * Crear usuario con los perfiles ADMINISTRADOR y USUARIO.
 */
private void crearUsuarioConPerfiles() {
    Usuario nuevoUsuario = new Usuario();

    nuevoUsuario.setNombre("José García");
    nuevoUsuario.setEmail("jose@gmail.com");
    nuevoUsuario.setUsername("joseg");
    nuevoUsuario.setPassword("12345");
    nuevoUsuario.setEstatus(1);
    nuevoUsuario.setFechaRegistro(LocalDate.now());

    Perfil perfil1 = perfilesRepo.findById(2)
            .orElseThrow(() ->
                    new IllegalStateException(
                            "No existe el perfil con ID 2"));

    Perfil perfil2 = perfilesRepo.findById(3)
            .orElseThrow(() ->
                    new IllegalStateException(
                            "No existe el perfil con ID 3"));

    Set<Perfil> perfilesUsuario = new HashSet<>();
    perfilesUsuario.add(perfil1);
    perfilesUsuario.add(perfil2);

    nuevoUsuario.setPerfiles(perfilesUsuario);

    Usuario usuarioGuardado = usuarioRepo.save(nuevoUsuario);

    System.out.println(
            "Usuario guardado correctamente. ID: "
                    + usuarioGuardado.getId());
}

}
