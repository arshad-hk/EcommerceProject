package com.example.ecompaymentservice.services;

import com.example.ecompaymentservice.gateways.IPaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    IPaymentGateway paymentGateway;
    public PaymentService(@Qualifier("Stripe") IPaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public String createPaymentLink(String orderId, long amount, String phoneNumber) throws StripeException, RazorpayException {
        return paymentGateway.createPaymentLink(orderId, amount, phoneNumber);
    }
}
