package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.DebitCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CashBackDebitCard extends DebitCard {
    private final int CASH_BACK_PERCENT = 5; // процент кэшбека 1-100 %
    private BigDecimal allCashBack;

    public BigDecimal getAllCashBack() {
        return allCashBack;
    }

    public void setAllCashBack(BigDecimal allCashBack) {
        this.allCashBack = allCashBack;
    }

    public CashBackDebitCard(BigDecimal balance) {
        super(balance);
    }

    public int getCASH_BACK_PERCENT() {
        return CASH_BACK_PERCENT;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (amount.compareTo(balance) <= 0) {
            if (amount.compareTo(BigDecimal.valueOf(5000)) > 0) {
                BigDecimal cashBack = getCashBack(amount);
                allCashBack = allCashBack.add(cashBack);
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

    @Override
    public Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal> availableFunds = new HashMap<>();
        availableFunds.put("Баланс на дебетовой карте с кэшбэком", balance);
        availableFunds.put("Процент кэшбэка от суммы затрат более 5000", BigDecimal.valueOf(CASH_BACK_PERCENT));
        return Collections.unmodifiableMap(availableFunds);
    }

    private BigDecimal getCashBack(BigDecimal amount) {
        return amount
                .multiply(BigDecimal.valueOf(CASH_BACK_PERCENT)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN));
    }
}



