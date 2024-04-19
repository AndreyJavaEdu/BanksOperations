package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class CashBackDebitCardTest {
    CashBackDebitCard cashBackDebitCard = new CashBackDebitCard(BigDecimal.valueOf(20_000));

    @Test
    void pay_returnTrueOrFalseAndReturnCashBack() {
        boolean resultOfPayOperation2 = cashBackDebitCard.pay(BigDecimal.valueOf(100_000));
        boolean resultOfPayOperation3 = cashBackDebitCard.pay(BigDecimal.valueOf(10_000));
        BigDecimal balanceInfo = cashBackDebitCard.getBalanceInfo();
        Assertions.assertFalse(resultOfPayOperation2);
        Assertions.assertTrue(resultOfPayOperation3);
        Assertions.assertEquals(balanceInfo, new BigDecimal("10500.00"));
    }

    @Test
    void getAvailableFundsInfo() {
        var availableFundsInfo = cashBackDebitCard.getAvailableFundsInfo();
        Assertions.assertNotNull(availableFundsInfo);
        Assertions.assertTrue(availableFundsInfo instanceof Map<String, BigDecimal>);
        Assertions.assertEquals(availableFundsInfo.get("Баланс на дебетовой карте с кэшбэком"), new BigDecimal("20000"));
    }
}