package by.epamtc.degtyarovea;

import java.util.Arrays;

public class MultiDimensionArrayHelper {

    public static void main(String[] args) {
        int size = 4;

        // Task 1
        System.out.printf("1. Square array with size %d.%n%s%n",
                size,
                parseArrayToString(createSquareArray(size)));

        // Task 2
        System.out.printf("2. Square array with input diagonal with size %d.%n%s%n",
                size,
                parseArrayToString(createSquareArrayWithDiagonals(size)));

        // Task 3
        System.out.printf("3. Square array with 1 and 0 with size %d.%n%s%n",
                size,
                parseArrayToString(createSquareArrayWithOneZero(size)));
    }

    public static int[][] createSquareArray(int size) {
        if (size <= 0) {
            return null;
        }
        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++) {
            int counter = 1;

            if (i % 2 == 0) {
                for (int j = 0; j < size; j++) {
                    array[i][j] = counter++;
                }
            } else {
                for (int j = size - 1; j >= 0; j--) {
                    array[i][j] = counter++;
                }
            }
        }

        return array;
    }

    public static String parseArrayToString(int[][] array) {
        if (array == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        for (int[] ints : array) {
            builder.append(Arrays.toString(ints))
                    .append("\n");
        }

        return builder.toString();
    }

    public static int[][] createSquareArrayWithDiagonals(int size) {
        if (size <= 0) {
            return null;
        }
        int[][] array = new int[size][size];

        for (int i = 0; i < array.length; i++) {
            array[i][i] = (i + 1) * (i + 2);
        }

        return array;
    }

    public static int[][] createSquareArrayWithOneZero(int size) {
        if (size <= 0) {
            return null;
        }
        int[][] array = new int[size][size];
        int till = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (j < till ^ (array[i].length - 1 - j >= till)) ? 1 : 0;
            }

            if (i < array.length / 2 - 1) {
                till++;
            }
            if (i > array.length / 2 - 1) {
                till--;
            }
        }

        return array;
    }

}
