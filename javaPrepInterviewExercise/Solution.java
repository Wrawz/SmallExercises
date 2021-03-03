package com.lucatto.pancakesort;

import java.util.Arrays;

public class Solution {
/*
* RULES FOR JAVA TECHNICAL INTERVIEW PREP SESSION ON PRAMP:
* 1) Write a function flip(arr, k) that reverses the order of the first k elements in the array arr;
* 2) Write a function pancakeSort(arr) that returns the input array. (BUT!) You are allowed
* to use ONLY the function flip(arr, k) you wrote in the first step in order to make changes in the array
*
* Note from myself: I'm coding this before seeing anything from the actual solution
* */

    public static void main(String[] args) {
//            System.out.println(Arrays.toString(pancakeSort(new int[]{1, 5, 4, 3, 2}))); // returns [1, 2, 3, 4, 5]
//            old way done without static methods
            pancakeSort(new int[]{1, 5, 4, 3, 2});
        }

    /* about flip()
    * arr = [1, 2, 3, 4, 5] and k = 3
    * arr = [3, 2, 1, 4, 5]
    * */


    public static void flip(int[] arr, int k) {
            int[] arr2 = new int[k];
            for (int i = 0; i < k; i++) arr2[i] = arr[i];
            for (int i = k-1, j = 0; i >= 0; i--, j++) arr[j] = arr2[i];
        }

    public static void pancakeSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++)
            if (arr[i] > arr[i + 1]) {
                for (int j = i+3; j > 1; j--) flip(arr, j);
                flip(arr, i+3);
            }
        System.out.println(Arrays.toString(arr));
        // these are static methods, so you don't need to return anything for this to work.
    }


//    1, 5, 4, 3, 2 -> k = 3 and then k = 2 (to use k, k-1, k-2, k-3, ... k-n and then k=k0)
//    4, 5, 1, 3, 2
//    5, 4, 1, 3, 2
//    1, 4, 5, 3, 2 -> k = 4 (4, 3, 2, 3, 4)
//    3, 5, 4, 1, 2 -> k = 3
//    4, 5, 3, 1, 2 -> k = 2
//    5, 4, 3, 1, 2 -> k = 4
//    1, 3, 4, 5, 2 -> k = 5
//    2, 5, 4, 3, 1
//    3, 4, 5, 2, 1
//    5, 4, 3, 2, 1
//    1, 2, 3, 4, 5

}

// below is what I would do normally, but I can modify the array only with the flip function
//        for (int i = 0; i < arr.length-1; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] > arr[j]) {
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
