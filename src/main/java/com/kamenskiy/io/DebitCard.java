package com.kamenskiy.io;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DebitCard extends BankCard {
    public DebitCard(BigDecimal balance) {
        super(balance);
    }

    @Override
    public void putMoney(BigDecimal amount) {
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            balance = balance.add(amount);
            System.out.println("Пополнение денежных средсств на сумму: " + amount);
        }
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
    public String getBalanceInfo() {
        var balanceOfDebitCard = balance.toString();
        return "Доступные денежные средства: " + balanceOfDebitCard;
    }

    @Override
    public List<String> getAvailableFundsInfo() {
        List<String> availableFunds = new ArrayList<>();
        availableFunds.add(balance.toString());
        return Collections.unmodifiableList(availableFunds);
    }
}
