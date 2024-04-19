package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BonusDebitCardTest {
    BonusDebitCard bonusDebitCard = new BonusDebitCard(BigDecimal.valueOf(10_000), 1, BigDecimal.ZERO);
    BonusDebitCard bonusDebitCard2 = new BonusDebitCard(BigDecimal.valueOf(10_000), 1, BigDecimal.TEN);

    @Test
    void pay_haveBonusPointsWhenPay() {
        boolean resultOfPayOperation1 = bonusDebitCard.pay(BigDecimal.valueOf(100_000));
        boolean resultOfPayOperation2 = bonusDebitCard.pay(BigDecimal.valueOf(5_000));
        boolean resultOfPayOperation3 = bonusDebitCard.pay(BigDecimal.valueOf(5_000));
        BigDecimal balanceInfo = bonusDebitCard.getBalanceInfo();
        Assertions.assertFalse(resultOfPayOperation1);
        Assertions.assertTrue(resultOfPayOperation2);
        Assertions.assertEquals(bonusDebitCard.getBonusPoints(), new BigDecimal("100.000"));
    }

    @Test
    void getAvailableFundsInfo_haveValuesForKeysOfMapAndMethodReturnNotNull() {
        var availableFundsInfo = bonusDebitCard2.getAvailableFundsInfo();
        Assertions.assertNotNull(availableFundsInfo);
        Assertions.assertEquals(availableFundsInfo.get("Баланс на дебетовой карте с бонусными балами"), new BigDecimal("10000"));
        Assertions.assertEquals(availableFundsInfo.get("Количество бонусных баллов"), bonusDebitCard2.getBonusPoints());
    }
}