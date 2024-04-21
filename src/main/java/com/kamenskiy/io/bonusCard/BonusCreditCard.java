package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.CreditCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BonusCreditCard extends CreditCard {
    private double bonusPointsRate; //процентная ставка по которой расчитываются бонусные баллы
    private BigDecimal bonusPoints; //количество бонусных баллов

    public BonusCreditCard(BigDecimal balance, BigDecimal CREDIT_LIMIT, double bonusPointsRate, BigDecimal bonusPoints) {
        super(balance, CREDIT_LIMIT);
        this.bonusPointsRate = bonusPointsRate;
        this.bonusPoints = bonusPoints;
    }

    public double getBonusPointsRate() {
        return bonusPointsRate;
    }

    public void setBonusPointsRate(double bonusPointsRate) {
        this.bonusPointsRate = bonusPointsRate;
    }

    public BigDecimal getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(BigDecimal bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        boolean operationPay = super.pay(amount);
        if (operationPay) {
            bonusPoints = bonusPoints.add(getPointsWhenPay(amount));
        }
        return operationPay;

    }

    private BigDecimal getPointsWhenPay(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(bonusPointsRate)).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_DOWN);
    }

    @Override
    public Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal> availableFunds = new HashMap<>();
        availableFunds.put("Баланс, включающий только собственные средства", balance);
        availableFunds.put("Основные средства, включающие собственные и кредитные средства", getBalanceInfo());
        availableFunds.put("Кредитный лимит данной кредитной карты", getCreditLimit());
        availableFunds.put("Процентная ставка бонусных баллов:", BigDecimal.valueOf(bonusPointsRate));
        availableFunds.put("Количество бонусных баллов", bonusPoints);
        return Collections.unmodifiableMap(availableFunds);
    }
}
