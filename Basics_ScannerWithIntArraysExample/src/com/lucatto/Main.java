package com.lucatto;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Testing methods:
//        System.out.println(ArrayMethods.getInstance().findFirstRepeatedElement(new int[]{3, 2, 5, 7, 7, 5, 2, 3}));
//        System.out.println(ArrayMethods.getInstance().findEarlierRepeatedElement(new int[]{4, 2, 5, 7, 7, 5, 2, 3, 4}));
//        System.out.println(Arrays.toString(ArrayMethods.getInstance().reverseArray(new int[]{3, 2, 5, 7, 3, 2})));
//        System.out.println(Arrays.toString(ArrayMethods.getInstance().sortArrayAsc(new int[]{3, 2, 5, 7, 3, 2})));
//        System.out.println(Arrays.toString(ArrayMethods.getInstance().sortArrayDesc(new int[]{3, 2, 5, 7, 3, 2})));
//        System.out.println(ArrayMethods.getInstance().findLastRepeatedElement(new int[]{3, 2, 5, 7, 7, 3, 2}));

        Scanner scanner = new Scanner(System.in);
        int[] userIntArray;
        while (true) {
            System.out.print("Please insert the number of elements in your array: ");
            if (scanner.hasNextInt()) {
                int numberOfElements = scanner.nextInt();
                userIntArray = new int[numberOfElements];
                scanner.nextLine();
                for (int i = 0; i < userIntArray.length; i++) {
                    System.out.print("Insert number #" + (i + 1) + ": ");
                    if (scanner.hasNextInt()) userIntArray[i] = scanner.nextInt();
                    else i--;
                    scanner.nextLine();
                }
            } else {
                scanner.nextLine();
                continue;
            }
            break;
        }
        while (true) {
            System.out.println("Please select an action. Press:");
            System.out.println("1 to find first repeated element;\n" +
                    "2 to find earlier repeated element;\n" +
                    "3 to find last repeated element\n" +
                    "4 to sort array ascending;\n" +
                    "5 to sort array descending;\n" +
                    "6 to reverse your array.");
            System.out.print(">> ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        System.out.println(ArrayMethods.getInstance().findFirstRepeatedElement(userIntArray));
                        break;
                    case 2:
                        System.out.println(ArrayMethods.getInstance().findEarlierRepeatedElement(userIntArray));
                        break;
                    case 3:
                        System.out.println(ArrayMethods.getInstance().findLastRepeatedElement(userIntArray));
                        break;
                    case 4:
                        System.out.println(Arrays.toString(ArrayMethods.getInstance().sortArrayAsc(userIntArray)));
                        break;
                    case 5:
                        System.out.println(Arrays.toString(ArrayMethods.getInstance().sortArrayDesc(userIntArray)));
                        break;
                    case 6:
                        System.out.println(Arrays.toString(ArrayMethods.getInstance().reverseArray(userIntArray)));
                        break;
                    default:
                        continue;
                }
                break;
            } else {
                scanner.nextLine();
            }
        }
        scanner.close();
    }

}
