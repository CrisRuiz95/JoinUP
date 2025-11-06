package com.proyecto.tfg.controller;


import com.proyecto.tfg.model.Direccion;
import com.proyecto.tfg.service.IDireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
public class DireccionController {

    @Autowired
    private IDireccionService direccionService;

    @GetMapping
    public List<Direccion> listarTodas() {
        return direccionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> buscarPorId(@PathVariable Integer id) {
        Direccion direccion = direccionService.buscarPorId(id);
        return direccion != null ? ResponseEntity.ok(direccion) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Direccion guardar(@RequestBody Direccion direccion) {
        return direccionService.guardar(direccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizar(@PathVariable Integer id, @RequestBody Direccion direccion) {
        Direccion existente = direccionService.buscarPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();
        direccion.setIdDireccion(id);
        return ResponseEntity.ok(direccionService.guardar(direccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        direccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}