package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.CreditCard;

import java.math.BigDecimal;

public class BonusCreditCard extends CreditCard {
    private double bonusPointsRate; //процентная ставка по которой расчитываются бонусные баллы
    private BigDecimal bonusPoints; //количество бонусных баллов

    public BonusCreditCard(BigDecimal balance, BigDecimal CREDIT_LIMIT) {
        super(balance, CREDIT_LIMIT);
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



}
