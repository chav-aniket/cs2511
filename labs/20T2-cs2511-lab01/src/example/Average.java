package example;

import java.util.Scanner;
import java.util.Arrays;

public class Average {

        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public float average(int[] nums) {
            // float result = 0;
            // Add your code
            return Arrays.stream(nums).sum()/nums.length;
        }

        public static void main(String[] args) {
            // Add your code
            Average avg = new Average();
            Scanner sc = new Scanner(System.in);

            int []nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(avg.average(nums));
            sc.close();
        }
}
