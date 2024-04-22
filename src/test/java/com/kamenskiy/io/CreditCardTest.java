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
    void putMoney() {
        creditCard.putMoney(BigDecimal.TEN);
        Assertions.assertEquals(creditCard.balance, BigDecimal.valueOf(10));
        creditCard.pay(BigDecimal.valueOf(500));
        creditCard.putMoney(BigDecimal.valueOf(300));
//        Assertions.assertEquals(creditCard.getBalanceInfo(), creditCard.creditPart);

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
    void getAvailableFundsInfo_returnNotNullAndHaveKeyAndValueInMap(){
        Map<String, BigDecimal> availableFundsInfo = creditCard.getAvailableFundsInfo();
        assertThat(availableFundsInfo).isNotNull();
        assertThat(availableFundsInfo).containsKey("Кредитный лимит кредитной карты");
        assertThat(availableFundsInfo).containsEntry("Баланс, включающий только собственные средства", creditCard.getBalance());
        assertThat(availableFundsInfo).isUnmodifiable();
    }
}