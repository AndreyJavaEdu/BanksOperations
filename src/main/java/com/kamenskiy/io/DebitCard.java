package com.kamenskiy.io;

import java.math.BigDecimal;
import java.util.List;

public class DebitCard extends BankCard{
    public DebitCard(BigDecimal balance) {
        super(balance);
    }

    @Override
    public boolean pay(BigDecimal amount) {
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
