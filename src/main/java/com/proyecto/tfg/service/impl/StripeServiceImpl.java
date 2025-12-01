package com.proyecto.tfg.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.proyecto.tfg.service.IStripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements IStripeService {

    @Value("${stripe.api.key}")
    private String apiKey;

    @Override
    public String crearPagoTest(Double monto, String moneda) throws StripeException {

        Stripe.apiKey = apiKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (monto * 100))           // Stripe usa centavos
                .setCurrency(moneda.toLowerCase())
                .setPaymentMethod("pm_card_visa")          //  pago instantáneo de prueba
                .setConfirm(true)                          // confirmar automáticamente
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .setAllowRedirects(
                                        PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER
                                )
                                .build()
                )
                .build();

        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getId();
    }
}
