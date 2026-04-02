public class TrivialCountingSort {


    //-------------------------------Trivial Counting Sort--------------------------------//
    //Exact implementation of Algorithm 1 pseudocode from paper 4

    //A, B and C are arrays of length n, n, k+1 respectively
    public static int[] trivialCountingSort(int[] A, int[] B, int[] C, int k) {

        int n = A.length;
        int i = 0, j = 0;             // pseudocode line 2

        // pseudocode line 3-5
        //initialise c of length k to all zeros
        while (i <= k) {
            C[i] = 0;
            i++;
        }

        // pseudocode line 6-8
        //count occurrences of all n nums from 0-k
        while (j < n) {
            C[A[j]] = C[A[j]] + 1;
            j++;
        }

        // pseudocode line 9-12
        // Cumulative sum --> C[i] now holds count of elements <= i        O(k)
        i = 1;
        j = n - 1;
        while (i <= k) {
            C[i] = C[i] + C[i - 1];
            i++;
        }

        // pseudocode line 13-16
        // loop backwards, place elements in their correct location
        while (j >= 0) {
            B[C[A[j]] - 1] = A[j];    // get location of A[j] and place it there
            C[A[j]] = C[A[j]] - 1;    // decrement the index position e.g. the jth element of A is 5, c[5] = 10 meaning 9 elements are before it, we should place it in the 10th spot, 10-1 = 9 index position, decrement it.
            j--;
        }

        //B contains the sorted list, A unmodified
        return B;
    }

    public static int[] callTrivCS(int[] A){
        if (A == null || A.length < 2) return A; //safety check
        int n = A.length;

        int k = A[0]; //first element max for now
        for (int x : A) if (x > k) k = x; //find the max element of A

        int[] B = new int[n];         // output array
        int[] C = new int[k + 1];     // count array

        trivialCountingSort(A, B, C, k);
        return B;
    }
}
