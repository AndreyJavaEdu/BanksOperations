package com.kamenskiy.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CreditCardTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardTest.class);
    CreditCard creditCard = new CreditCard(BigDecimal.ZERO, BigDecimal.valueOf(1000));


    @Test
    void putMoney_creditPartEqualsCreditLimit() {
        //arrange
        creditCard = new CreditCard(BigDecimal.ZERO, BigDecimal.valueOf(2000));
        //act
        creditCard.putMoney(BigDecimal.valueOf(1000));
        //assert
        Assertions.assertEquals(new BigDecimal("1000.000"), creditCard.getBalance());
    }

    @Test
    void putMoney_CreditPartLessThanCreditLimitAndBalanceZero() {
        //arrange
        creditCard = new CreditCard(BigDecimal.ZERO, BigDecimal.valueOf(3000));
        creditCard.creditPart = BigDecimal.ZERO;
        //act
        creditCard.putMoney(new BigDecimal("3000.000"));
        //assert
        Assertions.assertEquals(new BigDecimal("0.000"), creditCard.getBalance());
        Assertions.assertEquals(creditCard.creditPart, creditCard.getCreditLimit());
    }

    @Test
    void putMoney_creditPartLessThanCreditLimitAndBalanceZero_insufficientAmount() {
        //arrange
        creditCard = new CreditCard(BigDecimal.ZERO, BigDecimal.valueOf(3000));
        creditCard.creditPart = BigDecimal.ZERO;
        //act
        creditCard.putMoney(new BigDecimal("4000.000"));
        //assert
        Assertions.assertEquals(new BigDecimal("1000.000"), creditCard.getBalance());
        Assertions.assertEquals(creditCard.creditPart, creditCard.getCreditLimit());

    }

    @Test
    void pay_returnTrueAndFalse() {
        Assertions.assertFalse(creditCard.pay(BigDecimal.valueOf(10000)));
        Assertions.assertTrue(creditCard.pay(BigDecimal.valueOf(100)));
        Assertions.assertEquals(creditCard.getCreditPart(), BigDecimal.valueOf(900));
        LOGGER.info("Собственные средства карты: {}", creditCard.getBalance());
        Assertions.assertEquals(creditCard.balance, BigDecimal.ZERO);
    }

    @Test
    void getBalanceInfo_returnSumBalanceAndCreditLimit() {
        var info = creditCard.getBalanceInfo();
        Assertions.assertNotNull(info);
        assertThat(info).isGreaterThan(BigDecimal.valueOf(999));
        assertThat(info).isLessThan(BigDecimal.valueOf(1001));
        assertThat(info).isEqualTo(new BigDecimal("1000.000"));
    }

    @Test
    void getAvailableFundsInfo_returnNotNullAndHaveKeyAndValueInMap() {
        Map<String, BigDecimal> availableFundsInfo = creditCard.getAvailableFundsInfo();
        assertThat(availableFundsInfo).isNotNull();
        assertThat(availableFundsInfo).containsKey("Кредитный лимит кредитной карты");
        assertThat(availableFundsInfo).containsEntry("Баланс, включающий только собственные средства", creditCard.getBalance());
        assertThat(availableFundsInfo).isUnmodifiable();
    }
}