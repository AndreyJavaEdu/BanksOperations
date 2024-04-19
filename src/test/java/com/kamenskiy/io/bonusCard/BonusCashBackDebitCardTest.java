package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BonusCashBackDebitCardTest {
    BonusCashBackDebitCard cashBackDebitCard = new BonusCashBackDebitCard(BigDecimal.valueOf(20_000));

    @Test
    void pay() {
        boolean resultOfPayOperation2 = cashBackDebitCard.pay(BigDecimal.valueOf(100_000));
        boolean resultOfPayOperation3 = cashBackDebitCard.pay(BigDecimal.valueOf(10_000));
        cashBackDebitCard.getBalanceInfo();
        Assertions.assertFalse(resultOfPayOperation2);
        Assertions.assertTrue(resultOfPayOperation3);

    }

    @Test
    void getAvailableFundsInfo() {
    }
}