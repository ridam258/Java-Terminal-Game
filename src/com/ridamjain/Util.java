package com.ridamjain;

import java.util.ArrayList;

public class Util {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void createDelay(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void printer(String lineToPrint, int delay){
        String[] array = lineToPrint.split("");
        for (String c : array) {
            try {
                Thread.sleep(delay);
                System.out.print(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static int CalculateDamage(){
        return getRandomNumber(30,51);
    }
}