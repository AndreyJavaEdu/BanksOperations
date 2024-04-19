package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.DebitCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BonusDebitCard extends DebitCard {
    private double bonusPointsRate; //процентная ставка по которой расчитываются бонусные баллы
    private BigDecimal bonusPoints; //количество бонусных баллов

    public BonusDebitCard(BigDecimal balance, BigDecimal bonusPints) {
        super(balance);
        this.bonusPoints = bonusPints;
    }

    public BonusDebitCard(BigDecimal balance, double bonusPointsRate, BigDecimal bonusPints) {
        super(balance);
        this.bonusPointsRate = bonusPointsRate;
        this.bonusPoints = bonusPints;
    }

    public double getBonusPointsRate() {
        return bonusPointsRate;
    }

    public void setBonusPointsRate(double bonusPointsRate) {
        this.bonusPointsRate = bonusPointsRate;
    }

    @Override
    public void putMoney(BigDecimal amount) {
        super.putMoney(amount);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        boolean successOperation = super.pay(amount);
        if (successOperation) {
            bonusPoints = bonusPoints.add(getBonusPoints(amount));
        }
        return successOperation;
    }

    private BigDecimal getBonusPoints(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(bonusPointsRate)).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_DOWN);
    }

    @Override
    public BigDecimal getBalanceInfo() {
        return super.getBalanceInfo();
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
