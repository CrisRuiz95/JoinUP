package com.proyecto.tfg.controller;

import com.proyecto.tfg.model.UsuarioEvento;
import com.proyecto.tfg.service.IUsuarioEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripciones")
public class UsuarioEventoController {

    @Autowired
    private IUsuarioEventoService usuarioEventoService;

    // POST: /api/inscripciones/unirse?idUsuario=1&idEvento=5
    @PostMapping("/unirse")
    public ResponseEntity<?> unirse(@RequestParam int idUsuario,
                                    @RequestParam int idEvento) {
        try {
            // El servicio aún crea la inscripción, pero el controlador
            // ignora el objeto devuelto por el servicio.
            usuarioEventoService.unirseAEvento(idUsuario, idEvento);

            // 💡 CAMBIO: Devolvemos un String en lugar de la entidad
            return new ResponseEntity<>("TE HAS UNIDO CORRECTAMENTE", HttpStatus.OK); // O HttpStatus.CREATED

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE: /api/inscripciones/desunirse?idUsuario=1&idEvento=5
    @DeleteMapping("/desunirse")
    public ResponseEntity<?> desunirse(@RequestParam int idUsuario,
                                       @RequestParam int idEvento) {

        boolean eliminado = usuarioEventoService.desunirseDeEvento(idUsuario, idEvento);

        if (eliminado) {
            return new ResponseEntity<>("Desinscripción realizada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la inscripción o los datos son incorrectos", HttpStatus.NOT_FOUND);
        }
    }
}