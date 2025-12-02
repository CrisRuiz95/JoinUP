package com.proyecto.tfg.service;

import com.proyecto.tfg.model.Pago;

public interface IPagoService {

    /**
     * Procesa un pago para un usuario dado.
     * @param idUsuario ID del usuario que realiza el pago
     * @param monto Monto del pago
     * @param moneda Moneda del pago
     * @return El pago registrado en la base de datos
     * @throws Exception en caso de fallo
     */
    public Pago procesarPago(Integer idUsuario, Double monto, String moneda) throws Exception;
}
