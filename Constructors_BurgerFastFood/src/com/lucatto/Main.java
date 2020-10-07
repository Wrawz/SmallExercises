package com.lucatto;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int burgerType;
        int toppingsQuantity;
        int beverageType;
        int friesSize;
        while (true) {
            System.out.println("Please choose burger type.");
            Burger.printBurgerList();
            if (scanner.hasNextInt()) {
                burgerType = scanner.nextInt();
                if (burgerType < 1 || burgerType > 4)
                    continue;
                scanner.nextLine();
                while (true) {
                    System.out.println("Please choose how many toppings. (from 0 up to 10)");
                    if (scanner.hasNextInt()) {
                        toppingsQuantity = scanner.nextInt();
                        if (toppingsQuantity < 0 || toppingsQuantity > 10) {
                            scanner.nextLine();
                            continue;
                        }
                        scanner.nextLine();
                        while (true) {
                            System.out.println("Please choose beverage type.");
                            Burger.printBeverageType();
                            if (scanner.hasNextInt()) {
                                beverageType = scanner.nextInt();
                                if (beverageType < 0 || beverageType > 3) {
                                    scanner.nextLine();
                                    continue;
                                }
                                scanner.nextLine();
                                while (true) {
                                    System.out.println("Please choose fries size.");
                                    Burger.printFriesSize();
                                    if (scanner.hasNextInt()) {
                                        friesSize = scanner.nextInt();
                                        if (friesSize < 0 || friesSize > 3) {
                                            scanner.nextLine();
                                            continue;
                                        }
                                        break;
                                    } else {
                                        scanner.nextLine();
                                    }
                                }
                                break;
                            } else {
                                scanner.nextLine();
                            }
                        }
                        break;
                    } else {
                        scanner.nextLine();
                    }
                }
                break;
            } else {
                scanner.nextLine();
            }
        }

        if (!(Burger.hasBeverage(beverageType)) && !(Burger.hasFries(friesSize))) {
            System.out.println("Price for burger: " + Burger.calculateBurgerPrice(new Burger(burgerType, toppingsQuantity)));
            return;
        }
        if (!(Burger.hasBeverage(beverageType)) && (Burger.hasFries(friesSize))) {
            System.out.println("Price for burger: " + Burger.calculateBurgerPrice(new Burger(burgerType, toppingsQuantity, Burger.intFriesToStringFries(friesSize))));
            return;
        }
        if ((Burger.hasBeverage(beverageType)) && !(Burger.hasFries(friesSize))) {
            System.out.println("Price for burger: " + Burger.calculateBurgerPrice(new Burger(burgerType, toppingsQuantity, beverageType)));
            return;
        }
        System.out.println("Price for burger: " + Burger.calculateBurgerPrice(new Burger(burgerType, toppingsQuantity, beverageType, Burger.intFriesToStringFries(friesSize))));
        scanner.close();
    }

}
