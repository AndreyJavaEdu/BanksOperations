package com.kamenskiy.io;

import com.kamenskiy.io.bonusCard.BonusCreditCard;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создание экземляра DebitCard с начальным балансом 10_000 и проверка его методов");
        DebitCard debitCard = new DebitCard(BigDecimal.valueOf(10_000));
        System.out.println("Начало работы метода пополнения баланса - putMoney()");
        debitCard.putMoney(BigDecimal.valueOf(5000));
        System.out.println("Окончание работы метода пополнения баланса - putMoney()");

        System.out.println("Баланс карты после пополнения: " + debitCard.getBalance());

        System.out.println("Начало работы метода оплаты - pay()");
        debitCard.pay(BigDecimal.valueOf(3000));
        System.out.println("Окончание работы метода оплаты - pay()");

        System.out.println("Баланс карты после оплаты: " + debitCard.getBalance());

        System.out.println("Начало работы метода Получить информацию о балансе - getBalanceInfo()");
        BigDecimal balanceInfo = debitCard.getBalanceInfo();
        System.out.println(balanceInfo);
        System.out.println("Окончание работы метода Получить информацию о балансе - getBalanceInfo()");

        System.out.println("Начало работы метода Получить информацию о балансе - getBalanceInfo()");
        Map<String, BigDecimal> availableFundsInfo = debitCard.getAvailableFundsInfo();
        System.out.println(availableFundsInfo);
        System.out.println("Начало работы метода Получить информацию о балансе - getBalanceInfo()");

        System.out.println("----------------------------------------------------------");


    }
}