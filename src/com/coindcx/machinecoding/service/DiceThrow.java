package com.coindcx.machinecoding.service;

import java.util.Random;

public class DiceThrow {

    public static int getDiceThrow() {
        int rand = new Random().nextInt(6) + 1;
        return rand;
    }
}
