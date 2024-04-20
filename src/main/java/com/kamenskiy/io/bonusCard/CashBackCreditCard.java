package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.CreditCard;

import java.math.BigDecimal;
import java.util.Map;

public class CashBackCreditCard extends CreditCard {
    private final int CASH_BACK_PERCENT = 5; // процент кэшбека 1-100 %

    public CashBackCreditCard(BigDecimal balance, BigDecimal CREDIT_LIMIT) {
        super(balance, CREDIT_LIMIT);
    }

    public int getCASH_BACK_PERCENT() {
        return CASH_BACK_PERCENT;
    }

    @Override
    public BigDecimal getCreditPart() {
        return super.getCreditPart();
    }

    @Override
    public BigDecimal getCREDIT_LIMIT() {
        return super.getCREDIT_LIMIT();
    }

    @Override
    public void putMoney(BigDecimal amount) {
        super.putMoney(amount);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        return super.pay(amount);
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
