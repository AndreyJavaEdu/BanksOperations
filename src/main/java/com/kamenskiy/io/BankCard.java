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

    protected abstract void putMoney(BigDecimal amount);

    protected abstract boolean pay(BigDecimal amount);

    protected abstract BigDecimal getBalanceInfo();

    protected abstract Map<String, BigDecimal> getAvailableFundsInfo();
}
