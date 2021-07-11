package com.oluwafemi.domain;

import java.util.ArrayList;
import java.util.List;

public class DataFake {

    public static List<PaymentNetwork> fakePaymentNetworks() {
        List<PaymentNetwork> networks = new ArrayList<>();
        PaymentNetwork paypal = new PaymentNetwork();
        paypal.setCode("PPL");
        paypal.setLabel("Paypal");
        paypal.setMethod("Online");
        paypal.setLogoUrl("");

        PaymentNetwork klarna = new PaymentNetwork();
        paypal.setCode("KLNA");
        paypal.setLabel("Klarna");
        paypal.setMethod("Online");
        paypal.setLogoUrl("");

        networks.add(paypal);
        networks.add(klarna);

        return networks;
    }

}
