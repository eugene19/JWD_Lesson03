package by.epamtc.degtyarovea;

import java.util.Arrays;

public class MultiDimensionArrayHelper {

    public static void main(String[] args) {

        // Task 1
        int size = 5;
        System.out.printf("1. Square array with size %d.%n%s%n",
                size,
                parseArrayToString(createSquareArray(size)));

        // Task 2
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

}
