package com.kamenskiy.io;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public abstract class BankCard {
    protected BigDecimal balance;

    public BigDecimal getBalance() {
        return balance.setScale(3, RoundingMode.HALF_DOWN);
    }

    BankCard(BigDecimal balance) {
        this.balance = balance;
    }

    abstract void putMoney(BigDecimal amount);

    abstract boolean pay(BigDecimal amount);

    abstract BigDecimal getBalanceInfo();

    abstract Map<String, BigDecimal> getAvailableFundsInfo();
}
