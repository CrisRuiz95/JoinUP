package com.proyecto.tfg.controller;

import com.proyecto.tfg.service.IUsuarioDeseadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deseados")
@CrossOrigin(origins = "*")
public class UsuarioDeseadoController {

    @Autowired
    private IUsuarioDeseadoService usuarioDeseadoService;

    // Toggle: añade si no existe, elimina si ya está
    @PostMapping("/toggle")
    public ResponseEntity<?> toggleDeseado(
            @RequestParam int idCliente,
            @RequestParam int idEvento) {

        try {
            String mensaje = usuarioDeseadoService.toggleDeseado(idCliente, idEvento);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error procesando la petición", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✔ Saber si el evento está marcado como deseado
    @GetMapping("/check")
    public ResponseEntity<?> checkDeseado(
            @RequestParam int idCliente,
            @RequestParam int idEvento) {

        boolean esDeseado = usuarioDeseadoService.esDeseado(idCliente, idEvento);
        return new ResponseEntity<>(esDeseado, HttpStatus.OK);
    }

    // Eliminar directamente (opcional)
    @DeleteMapping("/delete")
    public ResponseEntity<?> eliminarDeseado(
            @RequestParam int idCliente,
            @RequestParam int idEvento) {

        usuarioDeseadoService.eliminarDeseado(idCliente, idEvento);
        return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
    }
}
