package com.example.ecompaymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentLinkDto {
    long amount;
    String phoneNumber;
    String orderId;
}
