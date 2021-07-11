package com.oluwafemi.data;

import com.oluwafemi.data.entity.Applicable;
import com.oluwafemi.data.entity.Identification;
import com.oluwafemi.data.entity.Links;
import com.oluwafemi.data.entity.Networks;
import com.oluwafemi.data.entity.PaymentNetworkEntity;
import com.oluwafemi.data.entity.mapper.PaymentEntityMapper;
import com.oluwafemi.domain.PaymentNetwork;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class PaymentEntityMapperTest {

    private PaymentEntityMapper paymentEntityMapper;
    private static final String TEST_CODE_1 = "VISA";
    private static final String TEST_LABEL_1 = "Visa";
    private static final String TEST_CODE_2 = "UNIONPAY";
    private static final String TEST_LABEL_2 = "UnionPay";
    private static final String TEST_METHOD = "CREDIT_CARD";

    @Before
    public void setUp() {
        paymentEntityMapper = new PaymentEntityMapper();
    }

    @Test
    public void transformPaymentNetworkEntity_returns_paymentNetworks() {
        PaymentNetworkEntity testNetworkEntity = createFakePaymentNetworkEntity();
        List<PaymentNetwork> paymentNetworks = paymentEntityMapper.transform(testNetworkEntity);

        assertThat(paymentNetworks.toArray()[0], is(instanceOf(PaymentNetwork.class)));
        assertThat(paymentNetworks.toArray()[1], is(instanceOf(PaymentNetwork.class)));
        assertThat(paymentNetworks.size(), is(2));
    }

    @Test
    public void transformApplicable_returns_paymentNetwork() {
        PaymentNetworkEntity testNetworkEntity = createFakePaymentNetworkEntity();
        Applicable applicable = testNetworkEntity.getNetworks().getApplicable().get(0);
        PaymentNetwork paymentNetwork = paymentEntityMapper.transform(applicable);

        assertThat(paymentNetwork.getCode(), is(TEST_CODE_1));
        assertThat(paymentNetwork.getLabel(), is(TEST_LABEL_1));
    }

    private PaymentNetworkEntity createFakePaymentNetworkEntity() {
        PaymentNetworkEntity networkEntity = new PaymentNetworkEntity();
        networkEntity.setNetworks(createFakeNetworks());
        networkEntity.setIdentification(createFakeIdentification());
        networkEntity.setOperation("LIST");

        return networkEntity;
    }

    private Identification createFakeIdentification() {
        Identification testIdentification = new Identification();
        testIdentification.setLongId("6076b2feabe8170009");
        testIdentification.setShortId("05753");
        testIdentification.setTransactionId("20678");

        return testIdentification;
    }

    private Networks createFakeNetworks() {
        Networks networks = new Networks();
        networks.setApplicable(createFakeApplicableList());

        return networks;
    }

    private List<Applicable> createFakeApplicableList() {
        List<Applicable> applicableList = new ArrayList<>();

        Applicable applicable1 = new Applicable();
        applicable1.setCode(TEST_CODE_1);
        applicable1.setLabel(TEST_LABEL_1);
        applicable1.setMethod(TEST_METHOD);
        applicable1.setLinks(createFakeLink());

        Applicable applicable2 = new Applicable();
        applicable2.setCode(TEST_CODE_2);
        applicable2.setLabel(TEST_LABEL_2);
        applicable2.setMethod(TEST_METHOD);
        applicable2.setLinks(createFakeLink());

        applicableList.add(applicable1);
        applicableList.add(applicable2);

        return applicableList;
    }

    private Links createFakeLink() {
        Links links = new Links();
        links.setLang("AMEX");
        links.setLogo("https://logo.com");
        return links;
    }

}
