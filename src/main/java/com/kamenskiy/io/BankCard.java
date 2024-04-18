package com.kamenskiy.io;

import java.math.BigDecimal;
import java.util.List;

public abstract class BankCard {
    protected BigDecimal balance;

    public BankCard(BigDecimal balance) {
        this.balance = balance;
    }

    public void putMoney(BigDecimal amount){
        balance.add(amount);
    }

    public abstract boolean pay(BigDecimal amount);

    public abstract BigDecimal getBalance();

    public abstract List<String> getAvailableFundsInfo();
}
