package com.proyecto.tfg.controller;


import com.proyecto.tfg.constants.TFGConstants;
import com.proyecto.tfg.model.Response;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 * Controlador REST para gestionar operaciones CRUD de usuarios.
 * Incluye endpoints para crear, leer, actualizar y eliminar usuarios.
 */

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

  @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.findAll();
    }

    @Operation(
            summary = "Fetch User Details REST API",
            description = "REST API to fetch User details based on ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = Response.class)
                    )
            )
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdCliente(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.fetchAccount(id);
        return usuarioOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new User"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = Response.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<Response> createAccount(@Valid @RequestBody Usuario usuario) {
        usuarioService.createAccount(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response(TFGConstants.STATUS_201, TFGConstants.MESSAGE_201));
    }




    @Operation(
            summary = "Login User REST API",
            description = "REST API to authenticate a user with email and password"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful, returns the user details",
                    content = @Content(
                            schema = @Schema(implementation = Usuario.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized, invalid email or password",
                    content = @Content(
                            schema = @Schema(implementation = Response.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = Response.class)
                    )
            )
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response("401", "Invalid email or password"));
        }
    }


    @Operation(
            summary = "Update User Details REST API",
            description = "REST API to update users details based on ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = Response.class)
                    )
            )
    }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> existente = usuarioService.fetchAccount(id);
        if (existente == null) return ResponseEntity.notFound().build();
        usuario.setIdCliente(id);
        return ResponseEntity.ok(usuarioService.updateAccount(usuario));
    }

    @Operation(
            summary = "Delete User Details REST API",
            description = "REST API to delete users details based on ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = Response.class)
                    )
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        boolean eliminado = usuarioService.deleteAccount(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }
    }

}