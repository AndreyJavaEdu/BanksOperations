package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.CreditCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CashBackCreditCard extends CreditCard {
    private final int CASH_BACK_PERCENT = 5; // процент кэшбека 1-100 %
    private BigDecimal allCashBack;


    public BigDecimal getAllCashBack() {
        return allCashBack;
    }

    public CashBackCreditCard(BigDecimal balance, BigDecimal CREDIT_LIMIT) {
        super(balance, CREDIT_LIMIT);
        allCashBack = BigDecimal.ZERO;
    }

    public int getCASH_BACK_PERCENT() {
        return CASH_BACK_PERCENT;
    }

    @Override
    public BigDecimal getCreditPart() {
        return super.getCreditPart();
    }

    @Override
    public BigDecimal getCREDIT_LIMIT() {
        return super.getCREDIT_LIMIT();
    }

    @Override
    public void putMoney(BigDecimal amount) {
        super.putMoney(amount);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        getCREDIT_LIMIT();
        if (balance.compareTo(amount) >= 0) {
            if (amount.compareTo(BigDecimal.valueOf(5000)) > 0) {
                var cashBack = getCashBack(amount);
                allCashBack = allCashBack.add(cashBack);
                balance = balance.subtract(amount).add(cashBack);
            }
        } else {
            var remainingDebt = amount.subtract(balance);
            if (creditPart.compareTo(remainingDebt) >= 0) {
                creditPart = creditPart.subtract(remainingDebt);
                balance = BigDecimal.ZERO;
                //возврат кэшбэка
                if (amount.compareTo(BigDecimal.valueOf(5000)) > 0) {
                    var cashBack = getCashBack(amount);
                    allCashBack = allCashBack.add(cashBack);
                    var difLimitCreditPart = getCREDIT_LIMIT().subtract(creditPart);
                    if (cashBack.compareTo(difLimitCreditPart) >= 0) {
                        creditPart = getCREDIT_LIMIT();
                        balance = balance.add(cashBack.subtract(difLimitCreditPart));
                    } else creditPart = creditPart.add(cashBack);
                }
            } else {
                return false; // Недостаточно средств и кредитной части
            }
        }
        return true; // Успешное списание средств
    }

    private BigDecimal getCashBack(BigDecimal amount) {
        return amount
                .multiply(BigDecimal.valueOf(CASH_BACK_PERCENT)
                        .divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_DOWN));
    }

    @Override
    public BigDecimal getBalanceInfo() {
        return super.getBalanceInfo();
    }

    @Override
    public Map<String, BigDecimal> getAvailableFundsInfo() {
        Map<String, BigDecimal> availableFunds = new HashMap<>();
        availableFunds.put("Баланс, включающий только собственные средства", balance);
        availableFunds.put("Доступная кредитная часть", getCreditPart());
        availableFunds.put("Основные средства, включающие собственные и кредитные средства", getBalanceInfo());
        availableFunds.put("Кредитный лимит данной кредитной карты", getCREDIT_LIMIT());
        availableFunds.put("Процент кэшбэка от суммы затрат более 5000", BigDecimal.valueOf(CASH_BACK_PERCENT));
        availableFunds.put("сумма всего кэшбэка", allCashBack);
        return Collections.unmodifiableMap(availableFunds);
    }
}
