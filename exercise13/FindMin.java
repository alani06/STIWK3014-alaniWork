package Exercise11.exercise13;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindMin extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;

    private int[] numbers;
    private int startIndex;
    private int endIndex;

    public FindMin (int[] numbers, int startIndex, int endIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        int sliceLength = (endIndex - startIndex) + 1;

        if (sliceLength > 2) {
            int mid = startIndex + (sliceLength/2) - 1;

            FindMin lowerHalf = new FindMin(numbers, startIndex, mid);
            FindMin upperHalf = new FindMin(numbers, mid + 1, endIndex);

            lowerHalf.fork();
            int upperResult = upperHalf.compute();
            int lowerResult = lowerHalf.join();

            return Math.min(lowerResult, upperResult);
        } else {
            if (startIndex == endIndex) {
                return numbers[startIndex];
            }
            return Math.min(numbers[startIndex], numbers[endIndex]);
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int [100];
        Random random = new Random(System.currentTimeMillis());
        for (int i=0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        System.out.print("Array:");
        for (int num: numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        ForkJoinPool pool = new ForkJoinPool (Runtime.getRuntime().availableProcessors());
        Integer min = pool.invoke (new FindMin(numbers, 0, numbers.length-1));

        System.out.println("Minimum value in the array: " +min);
    }
}
