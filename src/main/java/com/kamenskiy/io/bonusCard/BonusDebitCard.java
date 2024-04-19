package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.DebitCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class BonusDebitCard extends DebitCard {
    private double bonusPointsRate; //процентная ставка по которой расчитываются бонусные баллы


    public BonusDebitCard(BigDecimal balance) {
        super(balance);
    }

    public BonusDebitCard(BigDecimal balance, double bonusPointsRate) {
        super(balance);
        this.bonusPointsRate = bonusPointsRate;
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
        if (successOperation){
            getBonusPoints(amount);
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
        return super.getAvailableFundsInfo();
    }
}
