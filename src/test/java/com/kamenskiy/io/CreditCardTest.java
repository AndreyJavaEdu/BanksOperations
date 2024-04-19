package com.kamenskiy.io;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void putMoney() {
        CreditCard creditCard = new CreditCard(BigDecimal.ZERO, BigDecimal.valueOf(1000));
        creditCard.putMoney(BigDecimal.TEN);
        Assertions.assertEquals(creditCard.balance, BigDecimal.valueOf(10));
    }

    @Test
    void pay() {
        CreditCard creditCard = new CreditCard(BigDecimal.ZERO, BigDecimal.valueOf(1000));

        Assertions.assertFalse(creditCard.pay(BigDecimal.valueOf(10000)));
        Assertions.assertTrue(creditCard.pay(BigDecimal.valueOf(100)));
        Assertions.assertEquals(creditCard.getCreditPart(), BigDecimal.valueOf(900));
        Assertions.assertEquals(creditCard.balance, BigDecimal.ZERO);
    }
}