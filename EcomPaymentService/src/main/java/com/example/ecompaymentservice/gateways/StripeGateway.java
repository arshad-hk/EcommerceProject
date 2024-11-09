package com.example.ecompaymentservice.gateways;

import com.example.ecompaymentservice.dtos.InitiatePaymentLinkDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Stripe")
public class StripeGateway implements IPaymentGateway{
    @Value("${stripe.apikey}")
    private String stripeApiKey;
    @Override
    public String createPaymentLink(String orderId, long amount, String phoneNumber) throws StripeException {
        // Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";
        Stripe.apiKey = stripeApiKey;

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams.AfterCompletion.Type type = PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT;

        PaymentLinkCreateParams.AfterCompletion afterCompletion = PaymentLinkCreateParams.AfterCompletion
                .builder().setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().setUrl("http://localhost:8081/payments/success").build())
                .build();

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(afterCompletion)
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);

        return paymentLink.toString();
    }
}
