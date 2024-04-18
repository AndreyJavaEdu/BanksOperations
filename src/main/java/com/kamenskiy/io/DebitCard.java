package com.kamenskiy.io;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class DebitCard extends BankCard {
    public DebitCard(BigDecimal balance) {
        super(balance);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount, new MathContext(3));
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal getBalance() {
        return null;
    }

    @Override
    public List<String> getAvailableFundsInfo() {
        return null;
    }
}