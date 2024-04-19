package com.kamenskiy.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CreditCardTest {
    CreditCard creditCard = new CreditCard(BigDecimal.ZERO, BigDecimal.valueOf(1000));


    @Test
    void putMoney() {
        creditCard.putMoney(BigDecimal.TEN);
        Assertions.assertEquals(creditCard.balance, BigDecimal.valueOf(10));
    }

    @Test
    void pay() {
        Assertions.assertFalse(creditCard.pay(BigDecimal.valueOf(10000)));
        Assertions.assertTrue(creditCard.pay(BigDecimal.valueOf(100)));
        Assertions.assertEquals(creditCard.getCreditPart(), BigDecimal.valueOf(900));
        Assertions.assertEquals(creditCard.balance, BigDecimal.ZERO);
    }

    @Test
    void getBalanceInfo() {
        var info = creditCard.getBalanceInfo();
        Assertions.assertNotNull(info);
    }
}