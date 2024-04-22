package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AccumFundsCreditCardTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccumFundsCreditCardTest.class);
    AccumFundsCreditCard creditCard = new AccumFundsCreditCard(BigDecimal.ZERO, BigDecimal.valueOf(5000), BigDecimal.ZERO);

    @Test
    void putMoney_increaseBalanceWhenDepositOnAccumFunds() {
        creditCard.putMoney(BigDecimal.valueOf(1000));
        LOGGER.info("Сумма накоплений при депозите 1000: {}", creditCard.getAccumFunds());
        Assertions.assertEquals(creditCard.getAccumFunds(), new BigDecimal("0.050000"));
        creditCard.putMoney(BigDecimal.valueOf(1000));
        LOGGER.info("Сумма накоплений при депозите 2000: {}", creditCard.getAccumFunds());
        LOGGER.info("Баланс на карте после двух операций депозита по 1000: {}", creditCard.getBalance());
        Assertions.assertEquals(creditCard.getAccumFunds(), new BigDecimal("0.100000"));
        Assertions.assertEquals(creditCard.getBalance(), new BigDecimal("2000.100"));
    }

    @Test
    void getAvailableFundsInfo() {
        //given
        creditCard.putMoney(BigDecimal.valueOf(10_000));
        //when
        Map<String, BigDecimal> availableFundsInfo = creditCard.getAvailableFundsInfo();
        //then
        assertThat(availableFundsInfo).isNotNull();
        assertThat(availableFundsInfo).containsKey("Действующий процент накопления карты от суммы депозита");
        assertThat(availableFundsInfo).isUnmodifiable();
        assertThat(availableFundsInfo).containsEntry("Накопленные средства за все депозиты", creditCard.getAccumFunds());
    }
}