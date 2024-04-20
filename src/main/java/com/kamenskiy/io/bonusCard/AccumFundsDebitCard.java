package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.DebitCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AccumFundsDebitCard extends DebitCard {
    private final double ACCUM_PERCENT = 0.005;
    private BigDecimal accumFunds;

    public AccumFundsDebitCard(BigDecimal balance, BigDecimal accumFunds) {
        super(balance);
        this.accumFunds = accumFunds;
    }

    public double getACCUM_PERCENT() {
        return ACCUM_PERCENT;
    }

    public BigDecimal getAccumFunds() {
        return accumFunds;
    }

    public void setAccumFunds(BigDecimal accumFunds) {
        this.accumFunds = accumFunds;
    }

    @Override
    public void putMoney(BigDecimal amount) {
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
            balance = balance.add(amount).add(getAccumFundsOfAmount(amount));
            System.out.println("Пополнение денежных средств на сумму: " + amount);
        }
    }

    private BigDecimal getAccumFundsOfAmount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(ACCUM_PERCENT).divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_DOWN));
    }


    @Override
    public Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal> availableFunds = new HashMap<>();
        availableFunds.put("Баланс на дебетовой карте с бонусом накоплений", balance);
        availableFunds.put("Действующий процент накопления карты от суммы депозита", BigDecimal.valueOf(ACCUM_PERCENT));
        availableFunds.put("Накопленные средства за все депозиты", accumFunds);
        return Collections.unmodifiableMap(availableFunds);
    }
}
