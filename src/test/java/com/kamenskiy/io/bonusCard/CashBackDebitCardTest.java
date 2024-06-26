package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CashBackDebitCardTest {
    CashBackDebitCard cashBackDebitCard = new CashBackDebitCard(BigDecimal.valueOf(20_000));

    @Test
    void pay_returnTrueOrFalseAndReturnCashBack() {
        boolean resultOfPayOperation2 = cashBackDebitCard.pay(BigDecimal.valueOf(100_000));
        boolean resultOfPayOperation3 = cashBackDebitCard.pay(BigDecimal.valueOf(10_000));
        BigDecimal balanceInfo = cashBackDebitCard.getBalance();
        Assertions.assertFalse(resultOfPayOperation2);
        Assertions.assertTrue(resultOfPayOperation3);
        Assertions.assertEquals(balanceInfo, new BigDecimal("10500.000"));
    }

    @Test
    void getAvailableFundsInfo_haveValuesForKeysOfMapAndMethodReturnNotNull() {
        var availableFundsInfo = cashBackDebitCard.getAvailableFundsInfo();
        Assertions.assertNotNull(availableFundsInfo);
        Assertions.assertEquals(availableFundsInfo.get("Баланс на дебетовой карте с зачисленным кэшбэком"), new BigDecimal("20000.000"));
    }
}