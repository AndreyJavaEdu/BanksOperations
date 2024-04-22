package com.kamenskiy.io.bonusCard;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CashBackCreditCardTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CashBackCreditCardTest.class);
    CashBackCreditCard creditCard = new CashBackCreditCard(BigDecimal.valueOf(10_000), BigDecimal.valueOf(5000));

    @Test
    void pay_whenAmountLessThanBalanceReturnCashBackToBalance() {
        //given
        boolean result1 = creditCard.pay(BigDecimal.valueOf(10_000_000));
        boolean result2 = creditCard.pay(BigDecimal.valueOf(6000));
        //then
        Assertions.assertTrue(result2);
        Assertions.assertFalse(result1);
        LOGGER.info("Кэшбэк от величины оплаты: {}", creditCard.getAllCashBack().toString());
        LOGGER.info("Баланс с возвращенным кэшбэком: {}", creditCard.getBalance());
        Assertions.assertNotNull(creditCard.getAllCashBack());
        Assertions.assertEquals(creditCard.getAllCashBack(), new BigDecimal("300.000"));
        LOGGER.info("Кредитная часть: {}", creditCard.getCreditPart());
        Assertions.assertEquals(creditCard.getCreditLimit(), BigDecimal.valueOf(5000));
    }

    @Test
    void pay_cashBackReturnsToCreditPartFirstWhenBalanceIsZero() {
        LOGGER.info("Кредитная часть была: {}", creditCard.getCreditPart());
        creditCard.pay(BigDecimal.valueOf(12000));
        LOGGER.info("Доступная кредитная часть стала: {}", creditCard.getCreditPart());
        Assertions.assertNotEquals(creditCard.getCreditPart(), new BigDecimal("3000.000"));
        Assertions.assertEquals(creditCard.getAllCashBack(), new BigDecimal("600.000"));
    }

    @Test
    void getAvailableFundsInfo_returnThatResultIsImmutableAndHaveKeysAndHaveValueWithFullBalance() {
        Map<String, BigDecimal> fundsInfo = creditCard.getAvailableFundsInfo();
        creditCard.pay(BigDecimal.valueOf(1000));
        assertThat(fundsInfo).isNotNull();
        assertThat(fundsInfo).containsKey("Сумма всего кэшбэка");
        Map.Entry<String, BigDecimal> entry;
        assertThat(fundsInfo).containsEntry("Основные средства, включающие собственные и кредитные средства", creditCard.getBalanceInfo());
        assertThat(fundsInfo).isUnmodifiable();
    }
}