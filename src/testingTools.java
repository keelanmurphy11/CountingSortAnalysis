import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class testingTools {
    //Creates a nearly sorted array
    public static <E extends Comparable<E>> void edgeSort(int[] array, int deviation) { //Don't have to put in indexs in initial call
        edgeSort(array, 0, array.length - 1, deviation);
    }
    public static void edgeSort(int[] array, int low_index, int high_index, int deviation) {
        if (low_index > high_index) {
            return;
        }

        //Pivot selection: median of three
        int pivotIndex = (low_index + high_index) / 2;
        if (pivotIndex - low_index <= 3) {
            return;
        }
        if (array[low_index] > 0) swap(array, low_index, pivotIndex);
        if (array[low_index] > 0) swap(array, low_index, high_index);
        if (array[pivotIndex] > 0) swap(array, pivotIndex, high_index);

        int pivotValue = array[pivotIndex];

        int lp = low_index; //left and right pointers
        int rp = high_index - 1;

        swap(array, pivotIndex, high_index); //pivot is now the rightmost element

        while (lp < rp) {
            while (lp < rp && array[lp] <= 0) {
                lp++;
            }
            while (rp > lp && array[rp] >= 0) {
                rp--;
            }
            if (lp == rp) {
                swap(array, lp, high_index); //Swap pivot from rightmost position to its sorted position
            } else {
                swap(array, lp, rp);
            }
        }

        edgeSort(array, low_index, lp - 1, deviation);
        edgeSort(array, lp + 1, high_index, deviation);
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //Generates a random array
    public static int[] arrayGenerator(int size, int min, int max) {
        Random gen = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = gen.nextInt(min, max);
        }
        return arr;
    }
    //Generates a random array
    public static int[] arrayGenerator(int size) {
        Random gen = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = gen.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return arr;
    }
    public static void insertElement(int element, int[] arr) {
        Random r = new Random();
        int index = r.nextInt(arr.length - 1);
        arr[index] = element;
    }
    //Allows you to test multiple different sorts on a set of arrays.
    public static ArrayList<Long> chooseSort(String sortName, ArrayList<int[]> data) {
        ArrayList<Long> times = new ArrayList<>();
        //data.forEach(arr -> System.out.println(Arrays.toString(arr)));
        long start;
        long end;
        switch (sortName) {
            /*
            case "mergeSortSimple":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.mergeSortSimple(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
             */
            case "radixSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    geekSorts.radixsort(nums, nums.length);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "mergeSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    geekSorts.mergeSort(nums, 0, nums.length - 1);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "quickSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    geekSorts.quickSort(nums, 0, nums.length - 1);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
                /*
            case "bottomUpMergeSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.bottomUpMergeSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;

            case "shellSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.shellSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
                */
            case "aruCountingSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    ARUCountingSort.callaruCS(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "aruCountingSortSpace":
                for (int[] nums : data) {
                    long space = ARUCountingSort.callaruCSSpace(nums);
                    times.add(space);
                }
                return times;
            case "countingSortSpace":
                for (int[] nums : data) {
                    long space = geekSorts.countSortSpace(nums);
                    times.add(space);
                }
                return times;
            case "countingSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    //geekSorts.countSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
                /*
            case "insertionSort":
                for (int[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.insertionSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;

                 */
            default:
                throw new IllegalArgumentException(sortName + " is not a valid sort name - Check sort prototypes in OtherSorts.java");
        }
    }
    //Factory for chooseSort() sort input
    public static ArrayList<String> sortGenerator(String sortName, int n) {
        ArrayList<String> sort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sort.add(sortName);
        }
        return sort;
    }
}
