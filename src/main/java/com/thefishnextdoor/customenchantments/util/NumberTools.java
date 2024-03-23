package com.thefishnextdoor.customenchantments.util;

import java.util.Random;

public class NumberTools {

    public static boolean chance(double percent) {
        double randomValue = new Random().nextDouble() * 100.0;
        return randomValue <= percent;
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}