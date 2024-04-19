package com.kamenskiy.io;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CreditCard extends BankCard {
    private final BigDecimal CREDIT_LIMIT;
    private BigDecimal creditPart;

    public CreditCard(BigDecimal balance, BigDecimal CREDIT_LIMIT) {
        super(balance);
        this.CREDIT_LIMIT = CREDIT_LIMIT;
        this.creditPart = CREDIT_LIMIT;
    }

    public BigDecimal getCreditPart() {
        return creditPart;
    }

    public BigDecimal getCREDIT_LIMIT() {
        return CREDIT_LIMIT;
    }

    @Override
    public void putMoney(BigDecimal amount) {
        if (creditPart.compareTo(CREDIT_LIMIT) == 0) {
            balance = balance.add(amount);
        } else if (creditPart.compareTo(CREDIT_LIMIT) < 0 && balance.equals(BigDecimal.ZERO)) {
            var remainingDebt = CREDIT_LIMIT.subtract(creditPart);
            if (amount.compareTo(remainingDebt) <= 0) {
                creditPart = creditPart.add(remainingDebt);
            } else {
                creditPart = creditPart.add(remainingDebt);
                balance = balance.add(amount.subtract(remainingDebt));
            }
        }
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else {
            var remainingDebt = amount.subtract(balance);
            if (creditPart.compareTo(remainingDebt) >= 0) {
                creditPart = creditPart.subtract(remainingDebt);
                balance = BigDecimal.ZERO;
            } else {
                return false; // Недостаточно средств и кредитной части
            }
        }
        return true; // Успешное списание средств
    }


    @Override
    public BigDecimal getBalanceInfo() {
        var fullBalance = balance.add(creditPart);
        System.out.println("Общий баланс кредитной карты с учетом собственных и доступных кредитных средств: " + fullBalance);
        return fullBalance;
    }

    @Override
    public Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal>  availableFunds= new HashMap<>();
        availableFunds.put("Баланс, включающий только собственные средства", balance);
        availableFunds.put("Основные средства, включающие собственные и кредитные средства",getBalanceInfo());
        availableFunds.put("Кредитный лимит данной кредитной карты", CREDIT_LIMIT);
        return Collections.unmodifiableMap(availableFunds);
    }
}
