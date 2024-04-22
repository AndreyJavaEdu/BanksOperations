package com.kamenskiy.io;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CreditCard extends BankCard {
    private final BigDecimal creditLimit;
    protected BigDecimal creditPart;

    public CreditCard(BigDecimal balance, BigDecimal creditLimit) {
        super(balance);
        this.creditLimit = creditLimit;
        this.creditPart = creditLimit;
    }

    public BigDecimal getCreditPart() {
        return creditPart;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    @Override
    protected void putMoney(BigDecimal amount) {
        if (creditPart.compareTo(creditLimit) == 0) {
            balance = balance.add(amount);
        } else if (creditPart.compareTo(creditLimit) < 0 && balance.equals(BigDecimal.ZERO)) {
            var remainingDebt = creditLimit.subtract(creditPart);
            if (amount.compareTo(remainingDebt) <= 0) {
                creditPart = creditPart.add(remainingDebt);
            } else {
                creditPart = creditPart.add(remainingDebt);
                balance = balance.add(amount.subtract(remainingDebt));
            }
        }
    }

    @Override
    protected boolean pay(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return true;
        }
        var remainingDebt = amount.subtract(balance);
        if (creditPart.compareTo(remainingDebt) >= 0) {
            creditPart = creditPart.subtract(remainingDebt);
            balance = BigDecimal.ZERO;
            return true;
        }
        return false;
    }


    @Override
    protected BigDecimal getBalanceInfo() {
        var fullBalance = getBalance().add(creditPart);
        System.out.println("Общий баланс кредитной карты с учетом собственных и доступных кредитных средств: " + fullBalance);
        return fullBalance;
    }

    @Override
    protected Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal> availableFunds = new HashMap<>();
        availableFunds.put("Баланс, включающий только собственные средства", getBalance());
        availableFunds.put("Основные средства, включающие собственные и кредитные средства", getBalanceInfo());
        availableFunds.put("Кредитный лимит кредитной карты", creditLimit);
        return Collections.unmodifiableMap(availableFunds);
    }
}
