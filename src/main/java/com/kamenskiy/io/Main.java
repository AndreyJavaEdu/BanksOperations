package com.kamenskiy.io;

import com.kamenskiy.io.bonusCard.BonusCreditCard;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создание экземляра debitCard и проверка его методов");
        DebitCard debitCard = new DebitCard(BigDecimal.valueOf(10_000));
        debitCard.putMoney(BigDecimal.valueOf(5000));
        debitCard.pay(BigDecimal.valueOf(3000));


    }
}