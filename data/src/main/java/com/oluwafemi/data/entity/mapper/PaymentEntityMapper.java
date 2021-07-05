package com.oluwafemi.data.entity.mapper;

import com.oluwafemi.data.entity.Applicable;
import com.oluwafemi.data.entity.PaymentNetworkEntity;
import com.oluwafemi.domain.PaymentNetwork;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaymentEntityMapper {

    public PaymentNetwork transform(Applicable applicable) {
        PaymentNetwork paymentNetwork = null;
        if (applicable != null) {
            paymentNetwork = new PaymentNetwork();
            paymentNetwork.setCode(applicable.getCode());
            paymentNetwork.setLabel(applicable.getLabel());
            paymentNetwork.setMethod(applicable.getMethod());
            paymentNetwork.setLogoUrl(applicable.getLinks().getLogo());
        }
        return paymentNetwork;
    }

    public List<PaymentNetwork> transform(PaymentNetworkEntity networkEntity) {
        List<Applicable> networkEntities = networkEntity.getNetworks().getApplicable();
        final List<PaymentNetwork> paymentNetworks = new ArrayList<>();
        for (Applicable paymentNetwork : networkEntities) {
            final PaymentNetwork network = transform(paymentNetwork);
            if (network != null) {
                paymentNetworks.add(network);
            }
        }
        return paymentNetworks;
    }
}
