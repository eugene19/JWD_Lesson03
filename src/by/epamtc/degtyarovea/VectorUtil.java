package by.epamtc.degtyarovea;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class VectorUtil {

    public static void main(String[] args) {
        double[] doubleNums = {-14.1, -3.2, 2, -1, 0, 1, 2.4, 3};
        int[] integerNums = {1, 2, 3, 0, 1, 3, 3};

        // Task 1
        System.out.printf("Max sum of nums nearby: %.2f.%n",
                maxSumNearbyNums(doubleNums));

        // Task 2
        System.out.printf("Array without min number: %s.%n",
                Arrays.toString(deleteMinNums(integerNums)));

        // Task 3
        int[] initPassword = {0, 0, 0, 0, 0, 0, 0, 0, 1, 5};
        int[] fullPassword = password(initPassword);
        String passwordText = (fullPassword != null) ? Arrays.toString(fullPassword) : "Password not found";
        System.out.printf("Password: %s.%n", passwordText);

        // Task 4
        System.out.printf("Most common number is: %.2f.%n",
                mostCommonNum(doubleNums));

        // Task 5
        System.out.printf("Sum of max even and min odd: %.2f.%n",
                sumMaxEvenAndMinOdd(doubleNums));
    }

    public static double maxSumNearbyNums(double[] nums) {
        if (isEmpty(nums)) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else {
            double maxSum = nums[0] + nums[nums.length - 1];
            double currentSum;

            for (int i = 0; i < nums.length / 2; i++) {
                currentSum = nums[i] + nums[nums.length - 1 - i];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }

            return maxSum;
        }
    }

    private static boolean isEmpty(double[] nums) {
        return nums == null || nums.length == 0;
    }

    public static int[] deleteMinNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        } else {
            int minNum = minNum(nums);
            int newLength = countWithoutMin(nums, minNum);
            int[] withoutMin = new int[newLength];
            int count = 0;

            for (int num : nums) {
                if (num != minNum) {
                    withoutMin[count++] = num;
                }
            }

            return withoutMin;
        }
    }

    private static int minNum(int[] nums) {
        int min = nums[0];

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static int countWithoutMin(int[] nums, int min) {
        int count = 0;

        for (int num : nums) {
            if (num != min) {
                count++;
            }
        }

        return count;
    }

    public static int[] password(int[] array) {
        int[] password = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int indexFirstCube = 0;
        int indexSecondCube = 0;

        // find initial cubes
        for (int i = 0; i < array.length; i++) {
            indexFirstCube = array[indexFirstCube] > 0 ? indexFirstCube : i;
            indexSecondCube = array[indexFirstCube] > 0 && array[i] > 0 ? i : indexSecondCube;
        }

        int[] sequence = findSequenceOfThree(array, indexFirstCube, indexSecondCube);

        for (int i = 0; i < password.length; i++) {
            password[i] = sequence[i % 3];
        }

        if (password[indexFirstCube] != array[indexFirstCube]
                || password[indexSecondCube] != array[indexSecondCube]) {
            return null;
        } else {
            return password;
        }
    }

    private static int[] findSequenceOfThree(int[] array, int indexFirstCube, int indexSecondCube) {
        int[] sequence = new int[3];
        int pos2 = (indexSecondCube - indexFirstCube) % 3 > 1 ? 2 : 1;
        int pos3 = (indexSecondCube - indexFirstCube) % 3 > 1 ? 1 : 2;

        sequence[indexFirstCube % 3] = array[indexFirstCube];
        sequence[(indexFirstCube + pos2) % 3] = array[indexSecondCube];
        sequence[(indexFirstCube + pos3) % 3] = 10 - array[indexFirstCube] - sequence[(indexFirstCube + pos2) % 3];

        return sequence;
    }

    public static double mostCommonNum(double[] nums) {
        Map<Double, Integer> numsFrequency = numsFrequency(nums);
        double mostCommonNum = 0;
        double maxFrequency = 0;

        for (Map.Entry<Double, Integer> numWithFrequency : numsFrequency.entrySet()) {
            Double num = numWithFrequency.getKey();
            Integer frequency = numWithFrequency.getValue();

            if (frequency > maxFrequency
                    || (frequency == maxFrequency && numWithFrequency.getKey() < mostCommonNum)) {
                mostCommonNum = num;
                maxFrequency = frequency;
            }
        }

        return mostCommonNum;
    }

    private static Map<Double, Integer> numsFrequency(double[] nums) {
        Map<Double, Integer> numFrequency = new TreeMap<>();

        for (double num : nums) {
            Integer frequency = numFrequency.get(num);

            if (frequency != null) {
                numFrequency.put(num, ++frequency);
            } else {
                numFrequency.put(num, 1);
            }
        }

        return numFrequency;
    }

    public static double sumMaxEvenAndMinOdd(double[] nums) {
        if (isEmpty(nums)) {
            return 0;
        }
        double maxEven = maxEven(nums);
        double minOdd = minOdd(nums);

        return maxEven + minOdd;
    }

    private static double maxEven(double[] nums) {
        double maxEven = nums[1];

        for (int i = 1; i < nums.length; i += 2) {
            if (nums[i] > maxEven) {
                maxEven = nums[i];
            }
        }

        return maxEven;
    }

    private static double minOdd(double[] nums) {
        double minOdd = nums[0];

        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] < minOdd) {
                minOdd = nums[i];
            }
        }

        return minOdd;
    }
}
