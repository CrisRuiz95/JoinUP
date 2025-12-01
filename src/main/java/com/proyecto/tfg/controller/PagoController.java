package com.proyecto.tfg.controller;

import com.proyecto.tfg.model.Pago;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.service.IPagoService;
import com.proyecto.tfg.repository.UsuariosRepository;
import com.proyecto.tfg.service.impl.PagoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoServiceImpl pagoService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @PostMapping
    public Pago crearPago(@RequestParam Integer idUsuario,
                          @RequestParam Double monto,
                          @RequestParam String moneda) throws Exception {

        // Procesar el pago y actualizar rol si es exitoso
        Pago pago = pagoService.procesarPago(idUsuario, monto, moneda);

        // Opcional: cargar usuario actualizado
        Usuario usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Puedes agregar info extra en Pago si quieres, por ejemplo el rol
        // pago.setUsuario(usuario); // ya lo tienes en procesarPago

        return pago;
    }
}
