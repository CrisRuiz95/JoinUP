package com.proyecto.tfg.service.impl;

import com.proyecto.tfg.model.Pago;
import com.proyecto.tfg.model.Rol;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.repository.PagoRepository;
import com.proyecto.tfg.repository.UsuariosRepository;
import com.proyecto.tfg.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private StripeServiceImpl stripeService; // servicio que llama a Stripe en modo test

    @Override
    public Pago procesarPago(Integer idUsuario, Double monto, String moneda) throws Exception {
        // 1️⃣ Buscar usuario
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2️⃣ Crear PaymentIntent en Stripe (modo test)
        String idTransaccion = stripeService.crearPagoTest(monto, moneda);

        // 3️⃣ Guardar pago en base de datos
        Pago pago = new Pago();
        pago.setUsuario(usuario);
        pago.setMonto(monto);
        pago.setMoneda(moneda);
        pago.setEstado("exitoso"); // modo test
        pago.setFecha(LocalDateTime.now());
        pago.setIdTransaccion(idTransaccion);
        pagoRepository.save(pago);

        // 4️⃣ Cambiar rol del usuario si el pago fue exitoso
        if ("exitoso".equalsIgnoreCase(pago.getEstado())) {
            usuario.setRol(Rol.PREMIUM); // asignar rol PREMIUM
            usuarioRepository.save(usuario);
        }

        return pago;
    }

}
