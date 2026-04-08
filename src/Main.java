import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello");
        int[] A = {426, 826, 2, 64, 101, 104, 101, 9, 100, 78};
        /*
        int[] B = TrivialCountingSort.callTrivCS(A);
        System.out.println(Arrays.toString(B));

        int[] B2 = ARUCountingSort.callaruCS(A);
         */
        System.out.println(Arrays.toString(A));
        OtherSorts.radixSort(A);
        System.out.println(Arrays.toString(A));

    }
}
