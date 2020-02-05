package com.company;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {250, 260, 270, 240};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttakType = {"Physical", "Magical", "Kinetic", "Heal"};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }

    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttakType.length);
        bossDefenceType = heroesAttakType[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss health: " + bossHealth + " " + bossDefenceType);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("________________");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType.equals(heroesAttakType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(8) + 2;
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttakType[i] + " critical hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void heal(){
        if (heroesHealth[3] > 0) {
            if (heroesHealth[0] > 0 && heroesHealth[1] > 0 && heroesHealth[2] > 0){
                for (int i = 0; i <= heroesHealth.length; i++) {
                    heroesHealth[i] = heroesHealth[i] + 20;
                }
            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }
}
