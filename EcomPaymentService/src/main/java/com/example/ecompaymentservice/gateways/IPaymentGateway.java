package com.example.ecompaymentservice.gateways;

import com.example.ecompaymentservice.dtos.InitiatePaymentLinkDto;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface IPaymentGateway {
    String createPaymentLink(String orderId, long amount, String phoneNumber) throws StripeException, RazorpayException;
}
