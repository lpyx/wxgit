package com.jvm1;

import java.math.BigDecimal;

public class DeadLoop {
    public static void main(String[] args) {

        turnValue(new BigDecimal("100000000000000000000"));
        turnValue(new BigDecimal("1"));
        turnValue(new BigDecimal("10.100"));
    }
    private static void turnValue(BigDecimal number){
        if(number == null){
            return;
        }

        System.out.println(number.stripTrailingZeros().toPlainString());
    }
}

