package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class AccumFundsDebitCardTest {
    AccumFundsDebitCard accumDebitCard = new AccumFundsDebitCard(BigDecimal.ZERO, BigDecimal.ZERO);

    @Test
    void putMoney_havaAccumFundsAndBalanceIncreasing() {
        accumDebitCard.putMoney(BigDecimal.valueOf(1000));
        Assertions.assertEquals(accumDebitCard.getAccumFunds(), new BigDecimal("0.050000"));
        accumDebitCard.putMoney(BigDecimal.valueOf(1000));
        Assertions.assertEquals(accumDebitCard.getAccumFunds(), new BigDecimal("0.100000"));
        Assertions.assertEquals(accumDebitCard.getBalance(), new BigDecimal("2000.100"));
    }

    @Test
    void getAvailableFundsInfo_HaveMapAndHaveKeyOfAccumFunds() {
        Map<String, BigDecimal> availableFundsInfo = accumDebitCard.getAvailableFundsInfo();
        Assertions.assertNotNull(availableFundsInfo);
        Assertions.assertEquals(availableFundsInfo.get("Накопленные средства за все депозиты"), accumDebitCard.getAccumFunds());
    }
}