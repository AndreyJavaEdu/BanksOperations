package com.kamenskiy.io;

import java.math.BigDecimal;
import java.util.Map;

public abstract class BankCard {
    protected BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BankCard(BigDecimal balance) {
        this.balance = balance;
    }

    public abstract void putMoney(BigDecimal amount);

    public abstract boolean pay(BigDecimal amount);

    public abstract BigDecimal getBalanceInfo();

    public abstract Map<String, BigDecimal> getAvailableFundsInfo();
}
