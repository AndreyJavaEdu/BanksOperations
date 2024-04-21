package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

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
        Assertions.assertEquals(creditCard.getBalance(), new BigDecimal("2000.100000"));
    }

    @Test
    void getAvailableFundsInfo() {

    }
}