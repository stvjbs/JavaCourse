package com.andersenlab.entity;

import java.math.BigDecimal;

public class TicketPrice {
    private static final BigDecimal PRICE_20_MINUTES = BigDecimal.valueOf(300);
    private static final BigDecimal PRICE_90_MINUTES = BigDecimal.valueOf(450);
    private static final BigDecimal PRICE_DAY = BigDecimal.valueOf(700);
    private static final BigDecimal PRICE_MONTH = BigDecimal.valueOf(9000);

    public static BigDecimal getPRICE_20_MINUTES() {
        return PRICE_20_MINUTES;
    }

    public static BigDecimal getPRICE_90_MINUTES() {
        return PRICE_90_MINUTES;
    }

    public static BigDecimal getPRICE_DAY() {
        return PRICE_DAY;
    }

    public static BigDecimal getPRICE_MONTH() {
        return PRICE_MONTH;
    }
}
