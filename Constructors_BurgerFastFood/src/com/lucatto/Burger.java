package com.lucatto;

public class Burger {

    private final int type;
    private final int toppingQuantity;
    private final int beverageType;
    private final String friesSize;

    public Burger(int type, int toppingQuantity, int beverageType, String friesSize) {
        this.type = type;
        this.toppingQuantity = toppingQuantity;
        this.beverageType = beverageType;
        this.friesSize = setFriesSize(friesSize);
    }

    public Burger(int type, int toppingQuantity, int beverageType) {
        this(type, toppingQuantity, beverageType, null);
    }

    public Burger(int type, int toppingQuantity, String friesSize) {
        this(type, toppingQuantity, 0, friesSize);
    }

    public Burger(int type, int toppingQuantity) {
        this(type, toppingQuantity, 0, "none");
    }

    private String setFriesSize(String friesSize) {
        return (friesSize.toLowerCase().equals("max")) ?
                friesSize : friesSize.toLowerCase().equals("min") ?
                friesSize : friesSize.toLowerCase().equals("med") ?
                friesSize : "none";
    }

    private int getType() {
        return type;
    }

    private int getToppingQuantity() {
        return toppingQuantity;
    }

    private int getBeverageType() {
        return beverageType;
    }

    private String getFriesSize() {
        return friesSize;
    }

    public static void printBurgerList() {
        System.out.println("1 - normal burger\n" +
                "2 - double burger\n" +
                "3 - cheese burger\n" +
                "4 - double cheese burger");
    }

    public static void printBeverageType() {
        System.out.println("1 - Water\n" +
                "2 - Sparkling water\n" +
                "3 - Soda");
    }

    public static void printFriesSize() {
        System.out.println("1 - Small\n" +
                "2 - Medium\n" +
                "3 - Large");
    }

    public static String intFriesToStringFries(int friesType) {
        switch (friesType) {
            case 1:
                return "min";
            case 2:
                return "med";
            case 3:
                return "max";
            default:
                return "none";
        }
    }

    private double getBurgerPriceByType(int burgerType) {
        switch (burgerType) {
            case 1:
                return 4.5;
            case 2:
                return 6.0;
            case 3:
                return 5.5;
            case 4:
                return 7.0;
        }
        return 0;
    }

    private double getBeveragePrice(int beverageType) {
        switch (beverageType) {
            case 1:
                return 2.0;
            case 2:
                return 2.5;
            case 3:
                return 3.0;
        }
        return 0.0;
    }

    public double getFriesPrice(String friesSize) {
        if (friesSize.toLowerCase().equals("min")) return 1.0;
        if (friesSize.toLowerCase().equals("med")) return 1.5;
        if (friesSize.toLowerCase().equals("max")) return 2.0;
        return 0.0;
    }

    public static double calculateBurgerPrice(Burger burger) {
        double burgerTypePrice = burger.getBurgerPriceByType(burger.getType());
        double toppingPrice = burger.getToppingQuantity() * 0.5;
        double beveragePrice = burger.getBeveragePrice(burger.getBeverageType());
        double friesPrice = burger.getFriesPrice(burger.getFriesSize());

        return burgerTypePrice + toppingPrice + beveragePrice + friesPrice;
    }

    public static boolean hasFries(int friesType) {
        return friesType >= 1 && friesType <= 3;
    }

    public static boolean hasBeverage(int beverageType) {
        return beverageType >= 1 && beverageType <= 3;
    }
}
