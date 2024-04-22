package com.kamenskiy.io.bonusCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class BonusCreditCardTest {
    BonusCreditCard creditCard = new BonusCreditCard(BigDecimal.valueOf(10_000), BigDecimal.valueOf(5_000), 1, BigDecimal.ZERO);
    private static final Logger LOGGER = LoggerFactory.getLogger(BonusCreditCardTest.class);

    @Test
    void pay_haveBonusPoints() {
        creditCard.pay(BigDecimal.valueOf(5000));
        LOGGER.info("Количество бонусных баллов: {}", creditCard.getBonusPoints());
        Assertions.assertNotNull(creditCard.getBonusPoints());
    }

    @Test
    void pay_bonusPointsIncreasedAndMethodReturnTrue() {
        boolean resultPay1 = creditCard.pay(BigDecimal.valueOf(5000));
        boolean resultPay2 = creditCard.pay(BigDecimal.valueOf(5000));
        assertThat(resultPay1).isEqualTo(true);
        assertThat(resultPay2).isEqualTo(true);
        assertThat(creditCard.getBonusPoints()).isEqualTo(new BigDecimal("100.000"));


    }

    @Test
    void getAvailableFundsInfo_MapHaveKeyAndValueAndNotNull() {
//        given
        boolean resultPay1 = creditCard.pay(BigDecimal.valueOf(5000));
        boolean resultPay2 = creditCard.pay(BigDecimal.valueOf(5000));
//        when
        Map<String, BigDecimal> availableFundsInfo = creditCard.getAvailableFundsInfo();
//        then
        assertThat(availableFundsInfo).isNotNull();
        assertThat(availableFundsInfo).containsKey("Количество начисленных бонусных баллов");
        assertThat(availableFundsInfo).isUnmodifiable();
        assertThat(availableFundsInfo).containsEntry("Количество начисленных бонусных баллов", creditCard.getBonusPoints());

    }
}