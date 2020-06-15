package by.epamtc.degtyarovea;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class OneDimensionArrayHelper {

    public static void main(String[] args) {

        double[] doubleNums = {-14, -3, 2, -1, 0, 1, 2, 3};
        int[] integerNums = {1, 2, 3, 0, 1, 3, 3};

        // Task 1
        System.out.printf("Max sum near nums: %.2f.%n", maxSumNextNums(doubleNums));

        // Task 2
        System.out.printf("Array without min number: %s.%n",
                Arrays.toString(deleteMinNum(integerNums)));

        // Task 3


        // Task 4
        System.out.printf("Most common number is: %.2f.%n",
                getMostCommonNum(doubleNums));

        // Task 5
        System.out.printf("Sum of max even and min odd: %.2f.%n",
                getSumMaxEvenAndMinOdd(doubleNums));
    }

    public static double maxSumNextNums(double[] nums) {
        if (!hasElements(nums)) {
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

    private static boolean hasElements(double[] nums) {
        return nums != null && nums.length > 0;
    }

    public static int[] deleteMinNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        } else {
            int minNum = getMinNum(nums);
            int newLength = getCountWithoutMin(nums, minNum);
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

    private static int getMinNum(int[] nums) {
        int min = nums[0];

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static int getCountWithoutMin(int[] nums, int min) {
        int count = 0;

        for (int num : nums) {
            if (num != min) {
                count++;
            }
        }

        return count;
    }

    public static double getMostCommonNum(double[] nums) {
        Map<Double, Integer> numsFrequency = getNumsFrequency(nums);
        double mostCommonNum = 0;
        double maxFrequency = 0;

        for (Map.Entry<Double, Integer> numWithFrequency : numsFrequency.entrySet()) {
            Double num = numWithFrequency.getKey();
            Integer frequency = numWithFrequency.getValue();

            if (frequency > maxFrequency || (frequency == maxFrequency && numWithFrequency.getKey() < mostCommonNum)) {
                mostCommonNum = num;
                maxFrequency = frequency;
            }
        }

        return mostCommonNum;
    }

    private static Map<Double, Integer> getNumsFrequency(double[] nums) {
        Map<Double, Integer> numFrequency = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            double num = nums[i];
            Integer frequency = numFrequency.get(num);

            if (frequency != null) {
                numFrequency.put(num, frequency + 1);
            } else {
                numFrequency.put(num, 1);
            }
        }

        return numFrequency;
    }

    public static double getSumMaxEvenAndMinOdd(double[] nums) {
        if (!hasElements(nums)) {
            return 0;
        }
        double maxEven = getMaxEven(nums);
        double minOdd = getMinOdd(nums);

        return maxEven + minOdd;
    }

    private static double getMaxEven(double[] nums) {
        double maxEven = nums[1];

        for (int i = 1; i < nums.length; i += 2) {
            if (nums[i] > maxEven) {
                maxEven = nums[i];
            }
        }

        return maxEven;
    }

    private static double getMinOdd(double[] nums) {
        double minOdd = nums[0];

        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] < minOdd) {
                minOdd = nums[i];
            }
        }

        return minOdd;
    }
}
