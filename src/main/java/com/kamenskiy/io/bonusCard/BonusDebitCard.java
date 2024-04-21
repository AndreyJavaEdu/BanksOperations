package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.DebitCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BonusDebitCard extends DebitCard {
    private final double bonusPointsRate; //процентная ставка по которой расчитываются бонусные баллы
    private BigDecimal bonusPoints; //количество бонусных баллов

    public BonusDebitCard(BigDecimal balance, double bonusPointsRate, BigDecimal bonusPints) {
        super(balance);
        this.bonusPointsRate = bonusPointsRate;
        this.bonusPoints = bonusPints;
    }

    public BigDecimal getBonusPoints() {
        return bonusPoints;
    }

    public double getBonusPointsRate() {
        return bonusPointsRate;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        boolean successOperation = super.pay(amount);
        if (successOperation) {
            bonusPoints = bonusPoints.add(getPointsWhenPay(amount));
        }
        return successOperation;
    }

    private BigDecimal getPointsWhenPay(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(bonusPointsRate)).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_DOWN);
    }

    @Override
    public Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal> availableFunds = new HashMap<>();
        availableFunds.put("Баланс на дебетовой карте с бонусными балами", balance);
        availableFunds.put("Процентная ставка бонусных баллов", BigDecimal.valueOf(bonusPointsRate));
        availableFunds.put("Количество бонусных баллов", bonusPoints);
        return Collections.unmodifiableMap(availableFunds);
    }
}
