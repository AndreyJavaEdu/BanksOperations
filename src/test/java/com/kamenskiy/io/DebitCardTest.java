package com.kamenskiy.io;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DebitCardTest {

    @Test
    void putMoney_balanceIncreaseAfterMethodEnd() {
        //arrange
        DebitCard debitCard = new DebitCard(BigDecimal.ZERO);
        //act
        debitCard.putMoney(BigDecimal.TEN);
        //assert
        assertThat(debitCard.getBalance()).isEqualTo(new BigDecimal("10.000"));
    }

    @Test
    void pay_returnBooleanIfBalanceGreaterOrLessThenAmount() {
        //arrange
        DebitCard debitCard = new DebitCard(new BigDecimal("10000.000"));
        //act
        boolean resultPay1 = debitCard.pay(BigDecimal.valueOf(5000));
        boolean resultPay2 = debitCard.pay(BigDecimal.valueOf(20000));
        //assert
        assertThat(resultPay1).isEqualTo(true);
        assertThat(resultPay1).isTrue();
        assertThat(resultPay2).isFalse();
    }

    @Test
    void getBalanceInfo_haveResultOfBalance() {
        //arrange
        DebitCard debitCard = new DebitCard(new BigDecimal("10000.000"));
        DebitCard debitCard2 = new DebitCard(BigDecimal.ZERO);
        //act
        BigDecimal resultBalanceInfo = debitCard.getBalanceInfo();
        BigDecimal resultBalanceInfo2 = debitCard2.getBalanceInfo();
        //assert
        assertThat(resultBalanceInfo).isEqualTo(new BigDecimal("10000.000"));
        assertThat(resultBalanceInfo2).isEqualTo((debitCard2.getBalance()));
    }

    @Test
    void getAvailableFundsInfo_haveKeyAndHaveEntry() {
        //arrange
        DebitCard debitCard = new DebitCard(new BigDecimal("10000.000"));
        //act
        Map<String, BigDecimal> fundsInfo = debitCard.getAvailableFundsInfo();
        //assert
        assertThat(fundsInfo).isNotNull();
        assertThat(fundsInfo).containsEntry("Баланс", debitCard.getBalance());
        assertThat(fundsInfo).isUnmodifiable();
    }
}