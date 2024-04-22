package com.kamenskiy.io.bonusCard;

import com.kamenskiy.io.CreditCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CashBackCreditCard extends CreditCard {
    private static final int CASH_BACK_PERCENT = 5; // процент кэшбека 1-100 %
    public static final int MIN_SPEND_FOR_CASHBACK = 5000;
    private BigDecimal allCashBack;


    public BigDecimal getAllCashBack() {
        return allCashBack;
    }

    public CashBackCreditCard(BigDecimal balance, BigDecimal CREDIT_LIMIT) {
        super(balance, CREDIT_LIMIT);
        allCashBack = BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getCreditPart() {
        return super.getCreditPart();
    }

    @Override
    public BigDecimal getCreditLimit() {
        return super.getCreditLimit();
    }

    @Override
    public void putMoney(BigDecimal amount) {
        super.putMoney(amount);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            if (amount.compareTo(BigDecimal.valueOf(MIN_SPEND_FOR_CASHBACK)) > 0) {
                var cashBack = getCashBack(amount);
                allCashBack = allCashBack.add(cashBack);
                balance = balance.subtract(amount).add(cashBack);
            }
            return true;
        }
        var remainingDebt = amount.subtract(balance);
        if (creditPart.compareTo(remainingDebt) < 0) {
            return false; // Недостаточно средств и кредитной части
        }
        balance = BigDecimal.ZERO;
        creditPart = creditPart.subtract(remainingDebt);
        if (amount.compareTo(BigDecimal.valueOf(MIN_SPEND_FOR_CASHBACK)) > 0) {
            BigDecimal cashBack = getCashBack(amount);
            allCashBack = allCashBack.add(cashBack);
            var difLimitCreditPart = getCreditLimit().subtract(creditPart);
            boolean cashBackExceedsLimit = cashBack.compareTo(difLimitCreditPart) >= 0;
            creditPart = cashBackExceedsLimit ? getCreditLimit() : creditPart.add(cashBack);
            balance = cashBackExceedsLimit ? balance.add(cashBack.subtract(difLimitCreditPart)) : balance;
        }
        return true;
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
        availableFunds.put("Баланс, включающий только собственные средства", getBalance());
        availableFunds.put("Доступная кредитная часть", getCreditPart());
        availableFunds.put("Основные средства, включающие собственные и кредитные средства", getBalanceInfo());
        availableFunds.put("Кредитный лимит данной кредитной карты", getCreditLimit());
        availableFunds.put("Процент кэшбэка от суммы затрат более 5000", BigDecimal.valueOf(CASH_BACK_PERCENT));
        availableFunds.put("Сумма всего кэшбэка", allCashBack);
        return Collections.unmodifiableMap(availableFunds);
    }
}
