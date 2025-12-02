package com.proyecto.tfg.service;

import com.stripe.exception.StripeException;

public interface IStripeService {

    /**
     * Crea un pago de prueba en Stripe y devuelve el ID de la transacción.
     *
     * @param monto  Monto a pagar
     * @param moneda Moneda del pago
     * @return ID del PaymentIntent de Stripe
     * @throws StripeException en caso de error con Stripe
     */
    String crearPagoTest(Double monto, String moneda) throws StripeException;
}
