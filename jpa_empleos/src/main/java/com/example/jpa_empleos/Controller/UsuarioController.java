package com.example.jpa_empleos.Controller;


import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.models.Usuario;
import com.example.jpa_empleos.repository.PerfilesRepository;
import com.example.jpa_empleos.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/jpa-usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepo;
    private final PerfilesRepository perfilesRepo;

    public UsuarioController(
            UsuarioRepository usuarioRepo,
            PerfilesRepository perfilesRepo) {
        this.usuarioRepo = usuarioRepo;
        this.perfilesRepo = perfilesRepo;
    }

    // 1. Buscar un usuario y sus perfiles
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Integer id) {

        Optional<Usuario> usuarioOptional = usuarioRepo.findById(id);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuario no encontrado con ID: " + id);
    }

    // 2. Crear un usuario con perfiles existentes
    @PostMapping
    public ResponseEntity<?> crearUsuario(
            @RequestBody Usuario usuario) {

        try {
            Set<Perfil> perfilesAsociados = new HashSet<>();

            if (usuario.getPerfiles() != null) {
                for (Perfil perfilRecibido : usuario.getPerfiles()) {

                    Perfil perfilBD = perfilesRepo
                            .findById(perfilRecibido.getId())
                            .orElse(null);

                    if (perfilBD == null) {
                        return ResponseEntity.badRequest()
                                .body("No existe el perfil con ID: "
                                        + perfilRecibido.getId());
                    }

                    perfilesAsociados.add(perfilBD);
                }
            }

            usuario.setPerfiles(perfilesAsociados);

            if (usuario.getFechaRegistro() == null) {
                usuario.setFechaRegistro(LocalDate.now());
            }

            Usuario usuarioGuardado = usuarioRepo.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioGuardado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el usuario: " + e.getMessage());
        }
    }

    // 3. Crear varios perfiles
    @PostMapping("/perfiles")
    public ResponseEntity<?> crearPerfiles(
            @RequestBody List<Perfil> perfiles) {

        try {
            List<Perfil> perfilesGuardados =
                    perfilesRepo.saveAll(perfiles);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(perfilesGuardados);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear los perfiles: " + e.getMessage());
        }
    }
}
