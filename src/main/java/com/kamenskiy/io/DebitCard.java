package com.kamenskiy.io;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.Map;

public class DebitCard extends BankCard {
    public DebitCard(BigDecimal balance) {
        super(balance);
    }

    @Override
    protected void putMoney(BigDecimal amount) {
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            balance = balance.add(amount);
            System.out.println("Пополнение денежных средств на сумму: " + amount);
        }
    }

    @Override
    protected boolean pay(BigDecimal amount) {
        if (amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount, new MathContext(3));
            return true;
        }
        return false;
    }

    @Override
    protected BigDecimal getBalanceInfo() {
        System.out.println("Доступные денежные средства: " + balance);
        return balance;
    }

    @Override
    protected Map<String, BigDecimal> getAvailableFundsInfo() {
        return Collections.singletonMap("Баланс:", balance);
    }
}