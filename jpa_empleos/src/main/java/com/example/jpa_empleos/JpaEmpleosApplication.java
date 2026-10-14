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
import java.util.Set;
import java.util.Optional;
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
    buscarUsuario();
}


/**
 * Método para buscar un usuario y mostrar sus perfiles asociados.
 */
public void buscarUsuario() { Optional<Usuario> usuarioOptional = usuarioRepo.findById(50); if (usuarioOptional.isPresent()) { Usuario usuario = usuarioOptional.get(); System.out.println("Nombre: " + usuario.getNombre()); System.out.println("Perfiles asignados"); for (Perfil perfil : usuario.getPerfiles()) { System.out.println(perfil.getPerfil()); } } else { System.out.println("Usuario no encontrado"); } } 

}
