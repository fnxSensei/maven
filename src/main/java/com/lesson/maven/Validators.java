package com.lesson.maven;

/**
 * @author example
 * @version 2.1
 */
public class Validators {
    // попадает ли строка в диапазон
    /**
     * @param string
     * @param min
     * @param max
     * @return
     * @throws
     * */
    public static boolean stringBetween(String string, int min, int max){
        return string.length() > min && string.length() < max;
    }

    // является ли число положительным
    public static boolean isPositive(int value){
        return value > 0;
    }
}