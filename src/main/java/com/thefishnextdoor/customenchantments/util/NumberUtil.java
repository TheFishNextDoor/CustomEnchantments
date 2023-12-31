package com.thefishnextdoor.customenchantments.util;

import java.util.Random;

public class NumberUtil {

    public static boolean chance(double percent) {
        double randomValue = new Random().nextDouble() * 100.0;
        return randomValue <= percent;
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}