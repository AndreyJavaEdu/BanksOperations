package com.kamenskiy.io;

import com.kamenskiy.io.bonusCard.AccumFundsDebitCard;
import com.kamenskiy.io.bonusCard.BonusCreditCard;
import com.kamenskiy.io.bonusCard.BonusDebitCard;
import com.kamenskiy.io.bonusCard.CashBackDebitCard;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создание экземляра класса DebitCard с начальным балансом 10_000 и проверка его методов");
        BankCard debitCard = new DebitCard(BigDecimal.valueOf(10_000));
        startOperationsWithCard(debitCard);
        System.out.println();

        System.out.println("Создание экземляра класса CreditCard с начальным балансом 20_000, кредитным лимитом 15_000 и проверка его методов");
        BankCard creditCard = new CreditCard(BigDecimal.valueOf(20000), BigDecimal.valueOf(15000));
        startOperationsWithCard(creditCard);
        System.out.println();

        System.out.println("Создание экземляра класса BonusDebitCard с начальным балансом 0, " +
                "процентной ставкой бонусных баллов 1%");
        BankCard bonusDebitCard = new BonusDebitCard(BigDecimal.ZERO, 1, BigDecimal.ZERO);
        startOperationsWithCard(bonusDebitCard);
        System.out.println();

        System.out.println("Создание экземляра класса AccumFundsDebitCard с начальным балансом 0, " +
                "и с накопленными средства за все депозиты 0");
        BankCard accumFundsDebitCard = new AccumFundsDebitCard(BigDecimal.ZERO, BigDecimal.ZERO);
        startOperationsWithCard(accumFundsDebitCard);
        System.out.println();

        System.out.println("Создание экземляра класса CashBackDebitCard с начальным балансом 10_000 и проверка его методов");
        BankCard cashBackDebitCard = new CashBackDebitCard(BigDecimal.valueOf(10_000));
        startOperationsWithCard(cashBackDebitCard);
        System.out.println();

        System.out.println("Создание экземляра класса BonusCreditCard с начальным балансом 0, " +
                "кредитным лимитом 6000, процентной ставкой бонусных баллов = 1% и " +
                "начальным количеством бонусных баллов = 0");
        BankCard bonusCreditCard = new BonusCreditCard(BigDecimal.ZERO, BigDecimal.valueOf(6000), 1, BigDecimal.ZERO);
        startOperationsWithCard(bonusCreditCard);
        System.out.println();


    }

    private static void startOperationsWithCard(BankCard card) {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Начало работы метода пополнения баланса - putMoney()");
        card.putMoney(BigDecimal.valueOf(10000));
        System.out.println("Окончание работы метода пополнения баланса - putMoney()");
        System.out.println("Баланс карты после пополнения: " + card.getBalance());
        System.out.println();
        System.out.println("Начало работы метода оплаты - pay()");
        System.out.println("Баланс карты до списания: " + card.getBalance());
        card.pay(BigDecimal.valueOf(6000));
        System.out.println("Окончание работы метода оплаты - pay()");
        System.out.println("Баланс карты после списания: " + card.getBalance());
        System.out.println();
        System.out.println("Начало работы метода Получить информацию о балансе - getBalanceInfo()");
        card.getBalanceInfo();
        System.out.println("Окончание работы метода Получить информацию о балансе - getBalanceInfo()");
        System.out.println();
        System.out.println("Начало работы метода Получение информацию о доступных средствах - getAvailableFundsInfo()");
        Map<String, BigDecimal> availableFundsInfo = card.getAvailableFundsInfo();
        System.out.println(availableFundsInfo);
        System.out.println("Окончание работы метода Получение информацию о доступных средствах - getAvailableFundsInfo()");
        System.out.println("--------------------------------------------------------------------------------------------");

    }
}