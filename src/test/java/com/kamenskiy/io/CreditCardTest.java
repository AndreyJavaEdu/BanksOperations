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
}