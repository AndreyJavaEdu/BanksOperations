package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.CreditCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AccumFundsCreditCard extends CreditCard {
    private static final double ACCUM_PERCENT = 0.005; //Процент от суммы пополнений
    private BigDecimal accumFunds; //накопленная сумма

    public AccumFundsCreditCard(BigDecimal balance, BigDecimal CREDIT_LIMIT) {
        super(balance, CREDIT_LIMIT);
        this.accumFunds = BigDecimal.ZERO;
    }

    public BigDecimal getAccumFunds() {
        return accumFunds;
    }

    @Override
    public void putMoney(BigDecimal amount) {
        if (creditPart.compareTo(getCreditLimit()) == 0) {
            balance = balance.add(amount).add(getAccumFundsOfAmount(amount));
            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
        }
        else if (creditPart.compareTo(getCreditLimit()) < 0) {
            var remainingDebt = getCreditLimit().subtract(creditPart);
            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
            if (amount.compareTo(remainingDebt) < 0) {
                creditPart = creditPart.add(remainingDebt).add(getAccumFundsOfAmount(amount));
            } else if (amount.compareTo(remainingDebt) == 0) {
                creditPart = creditPart.add(remainingDebt);
                balance = balance.add(getAccumFundsOfAmount(amount));
            } else {
                creditPart = creditPart.add(remainingDebt);
                balance = balance.add(amount.subtract(remainingDebt));
                accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
            }
        }
        //второй вариант реализации метода без вложенности if:
//        if (creditPart.compareTo(getCreditLimit()) == 0) {
//            balance = balance.add(amount).add(getAccumFundsOfAmount(amount));
//            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
//        } else if (creditPart.compareTo(getCreditLimit()) < 0 && amount.compareTo(getCreditLimit().subtract(creditPart)) < 0) {
//            var remainingDebt = getCreditLimit().subtract(creditPart);
//            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
//            creditPart = creditPart.add(remainingDebt).add(getAccumFundsOfAmount(amount));
//        } else if (creditPart.compareTo(getCreditLimit()) < 0 && amount.compareTo(getCreditLimit().subtract(creditPart)) == 0) {
//            var remainingDebt = getCreditLimit().subtract(creditPart);
//            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
//            creditPart = creditPart.add(remainingDebt);
//            balance = balance.add(getAccumFundsOfAmount(amount));
//        } else if (creditPart.compareTo(getCreditLimit()) < 0) {
//            var remainingDebt = getCreditLimit().subtract(creditPart);
//            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
//            creditPart = creditPart.add(remainingDebt);
//            balance = balance.add(amount.subtract(remainingDebt));
//            accumFunds = accumFunds.add(getAccumFundsOfAmount(amount));
//        }
    }

    private BigDecimal getAccumFundsOfAmount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(ACCUM_PERCENT).divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_DOWN));
    }


    @Override
    public Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal> availableFunds = new HashMap<>();
        availableFunds.put("Баланс, включающий только собственные средства", getBalance());
        availableFunds.put("Основные средства, включающие собственные и кредитные средства", getBalanceInfo());
        availableFunds.put("Кредитный лимит данной кредитной карты", getCreditLimit());
        availableFunds.put("Действующий процент накопления карты от суммы депозита", BigDecimal.valueOf(ACCUM_PERCENT));
        availableFunds.put("Накопленные средства за все депозиты", getAccumFunds());
        return Collections.unmodifiableMap(availableFunds);
    }
}
