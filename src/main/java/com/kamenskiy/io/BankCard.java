package com.kamenskiy.io;

import java.math.BigDecimal;
import java.util.List;

public abstract class BankCard {
    protected BigDecimal balance;

    public BankCard(BigDecimal balance) {
        this.balance = balance;
    }

    public abstract void putMoney(BigDecimal amount);

    public abstract boolean pay(BigDecimal amount);

    public abstract String getBalanceInfo();

    public  List<String> getAvailableFundsInfo(){
return null;
    }
}
