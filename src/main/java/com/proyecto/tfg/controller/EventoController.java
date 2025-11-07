package com.proyecto.tfg.controller;

import com.proyecto.tfg.constants.TFGConstants;
import com.proyecto.tfg.model.Evento;
import com.proyecto.tfg.model.Response;
import com.proyecto.tfg.service.IEventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private IEventoService eventoService;



    // ========================
    // ✅ Crear un nuevo evento
    // ========================
    @Operation(
            summary = "Create Event REST API",
            description = "REST API to create a new event"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<Response> createEvent(@Valid @RequestBody Evento evento) {
        eventoService.save(evento);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response(TFGConstants.STATUS_201, TFGConstants.MESSAGE_201));
    }

    // ========================
    // ✅ Actualizar un evento existente
    // ========================
    @Operation(
            summary = "Update Event REST API",
            description = "REST API to update an existing event by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> actualizarEvento(@PathVariable int id, @Valid @RequestBody Evento evento) {
        try {
            Evento updated = eventoService.updateEvento(id, evento);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response(TFGConstants.STATUS_200, "Evento actualizado correctamente."));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(TFGConstants.STATUS_417, e.getMessage()));
        }
    }

    // ========================
    // ✅ Listar todos los eventos
    // ========================
    @Operation(
            summary = "List All Events REST API",
            description = "REST API to fetch all events from the database"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "204", description = "No events found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = Evento.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<Evento>> listarTodos() {
        List<Evento> eventos = eventoService.listAll();
        if (eventos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(eventos); // 200 OK
    }

    // ========================
    // ✅ Obtener un evento por ID
    // ========================
    @Operation(
            summary = "Get Event by ID REST API",
            description = "REST API to fetch event details by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event found successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = Evento.class)))
    })
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Evento> obtenerEvento(@PathVariable int id) {
        Optional<Evento> optionalEvento = eventoService.fetchEvento(id);

        return optionalEvento
                .map(evento -> ResponseEntity.ok(evento))          // 200 OK con el evento
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no existe
    }

    // ========================
    // ✅ Buscar eventos por título
    // ========================
    @Operation(
            summary = "Search Events by Title REST API",
            description = "REST API to search events by partial title match"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events found successfully"),
            @ApiResponse(responseCode = "204", description = "No events found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = Evento.class)))
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<Evento>> buscarPorTitulo(@RequestParam("titulo") String titulo) {
        List<Evento> eventos = eventoService.findByTitulo(titulo);
        if (eventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventos);
    }

    // ========================
    // ✅ Eliminar un evento por ID
    // ========================
    @Operation(
            summary = "Delete Event REST API",
            description = "REST API to delete an event by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        boolean eliminado = eventoService.delete(id);
        if (eliminado) {
            return ResponseEntity.ok("Evento eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Evento no encontrado.");
        }
    }

}
