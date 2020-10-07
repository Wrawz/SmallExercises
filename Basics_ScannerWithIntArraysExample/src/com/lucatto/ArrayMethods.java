package com.lucatto;

public class ArrayMethods {

    private ArrayMethods() {
    }

    public static ArrayMethods getInstance() {
        return new ArrayMethods();
    }

    public int findFirstRepeatedElement(int[] intArray) {
        int[] newIntArray = new int[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            newIntArray[i] = intArray[i];
            for (int j = 0; j < i; j++) {
                if (newIntArray[i] == newIntArray[j])
                    return newIntArray[i];
            }
        }
        return -1;
    }

    public int findEarlierRepeatedElement(int[] intArray) {
        int[] newIntArray = new int[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            newIntArray[i] = intArray[i];
            for (int ints : newIntArray)
                if (newIntArray[i] == ints)
                    return ints;
        }
        return -1;
    }

    public int findLastRepeatedElement(int[] intArray) {
        int[] newIntArray = reversedArray(intArray);
        return findEarlierRepeatedElement(newIntArray);
    }

    public int[] sortArrayAsc(int[] intArray) {
        for (int i = 0; i < intArray.length - 1; i++)
            for (int j = i + 1; j < intArray.length; j++)
                if (intArray[i] > intArray[j]) {
                    int tempInt = intArray[i];
                    intArray[i] = intArray[j];
                    intArray[j] = tempInt;
                }
        return intArray;
    }

    public int[] sortArrayDesc(int[] intArray) {
        for (int i = 0; i < intArray.length - 1; i++)
            for (int j = i + 1; j < intArray.length; j++)
                if (intArray[i] < intArray[j]) {
                    int tempInt = intArray[i];
                    intArray[i] = intArray[j];
                    intArray[j] = tempInt;
                }
        return intArray;
    }

    public int[] reversedArray(int[] intArray) {
        int[] newIntArray = new int[intArray.length];
        int i = intArray.length - 1;
        for (int ints : intArray) {
            newIntArray[i] = ints;
            i--;
        }
        return newIntArray;
    }

}
