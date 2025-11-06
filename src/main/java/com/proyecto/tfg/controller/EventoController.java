package com.proyecto.tfg.controller;


import com.proyecto.tfg.model.Evento;
import com.proyecto.tfg.service.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private IEventoService eventoService;

    @GetMapping
    public List<Evento> listarTodos() {
        return eventoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Integer id) {
        Evento evento = eventoService.buscarPorId(id);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Evento guardar(@RequestBody Evento evento) {
        return eventoService.guardar(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizar(@PathVariable Integer id, @RequestBody Evento evento) {
        Evento existente = eventoService.buscarPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();
        evento.setIdEvento(id);
        return ResponseEntity.ok(eventoService.guardar(evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}