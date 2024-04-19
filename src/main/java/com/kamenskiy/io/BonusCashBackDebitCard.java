package com.kamenskiy.io;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BonusCashBackDebitCard extends DebitCard {
    private int cashBackPercent; // процент кэшбека 1-100 %

    public BonusCashBackDebitCard(BigDecimal balance) {
        super(balance);
    }

    public BonusCashBackDebitCard(BigDecimal balance, int cashBackPercent) {
        super(balance);
        this.cashBackPercent = cashBackPercent;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (amount.compareTo(balance) <= 0) {
            if (amount.compareTo(BigDecimal.valueOf(5000)) > 0) {
                BigDecimal cashBack = getCashBack(amount);
                balance = balance
                        .subtract(amount)
                        .add(cashBack);
                System.out.println("Сумма кэшбэка составили: " + cashBack);
            } else balance = balance
                    .subtract(amount);

            return true;
        }
        return false;
    }

    private BigDecimal getCashBack(BigDecimal amount) {
        return amount
                .multiply(BigDecimal.valueOf(cashBackPercent)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN));
    }
}



