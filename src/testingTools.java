import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class testingTools {
    //Creates a nearly sorted array
    public static <E extends Comparable<E>> void edgeSort(E[] array, int deviation) { //Don't have to put in indexs in initial call
        edgeSort(array, 0, array.length - 1, deviation);
    }
    public static <E extends Comparable<E>> void edgeSort(E[] array, int low_index, int high_index, int deviation) {
        if (low_index > high_index) {
            return;
        }

        //Pivot selection: median of three
        int pivotIndex = (low_index + high_index) / 2;
        if (pivotIndex - low_index <= 3) {
            return;
        }
        if (array[low_index].compareTo(array[pivotIndex]) > 0) swap(array, low_index, pivotIndex);
        if (array[low_index].compareTo(array[high_index]) > 0) swap(array, low_index, high_index);
        if (array[pivotIndex].compareTo(array[high_index]) > 0) swap(array, pivotIndex, high_index);

        E pivotValue = array[pivotIndex];

        int lp = low_index; //left and right pointers
        int rp = high_index - 1;

        swap(array, pivotIndex, high_index); //pivot is now the rightmost element

        while (lp < rp) {
            while (lp < rp && array[lp].compareTo(pivotValue) <= 0) {
                lp++;
            }
            while (rp > lp && array[rp].compareTo(pivotValue) >= 0) {
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

    public static <E extends Comparable<E>> void swap(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //Generates a random array
    public static Integer[] arrayGenerator(int size, int min, int max) {
        Random gen = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = gen.nextInt(min, max);
        }
        return arr;
    }
    //Generates a random array
    public static Integer[] arrayGenerator(int size) {
        Random gen = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = gen.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return arr;
    }

    //Allows you to test multiple different sorts on a set of arrays.
    public static ArrayList<Long> chooseSort(String sortName, ArrayList<Integer[]> data) {
        ArrayList<Long> times = new ArrayList<>();
        //data.forEach(arr -> System.out.println(Arrays.toString(arr)));
        long start;
        long end;
        switch (sortName) {
            case "mergeSortSimple":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.mergeSortSimple(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "radixSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.radixSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "mergeSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.mergeSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "quickSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.quickSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "bottomUpMergeSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.bottomUpMergeSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "shellSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.shellSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "countingSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.countingSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "insertionSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    OtherSorts.insertionSort(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
            case "aruCountingSort":
                for (Integer[] nums : data) {
                    start = System.nanoTime();
                    ARUCountingSort.callaruCS(nums);
                    end = System.nanoTime();
                    times.add(end - start);
                }
                return times;
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
