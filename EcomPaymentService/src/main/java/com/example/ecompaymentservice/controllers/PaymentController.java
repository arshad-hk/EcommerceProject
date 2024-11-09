package com.example.ecompaymentservice.controllers;

import com.example.ecompaymentservice.dtos.InitiatePaymentLinkDto;
import com.example.ecompaymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String createPaymentLink(@RequestBody InitiatePaymentLinkDto initiatePaymentLinkDto) throws StripeException, RazorpayException {
        return paymentService.createPaymentLink(initiatePaymentLinkDto.getOrderId(), initiatePaymentLinkDto.getAmount() , initiatePaymentLinkDto.getPhoneNumber());
    }

    @GetMapping("/success")
    public String successPaymentLink(){
        String message = "The payment link is successfully created.";
        System.out.println(message);
        return message;
    }
}
