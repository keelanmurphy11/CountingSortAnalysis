public class ARUCountingSort {

    //-------------------------------Trivial Counting Sort--------------------------------//
    //Exact implementation of Algorithm 2 pseudocode from paper 4

    public static Integer[] aruCountingSort(Integer[] A, Integer[] B, Integer[] Q, Integer[] R, Integer k) {
        //A, B, Q, R are arrays of length n, n, m+1 & m+1 respectively
        // m = ceil(root(k)),  k is max element in array A

        int n = A.length;

        // pseudocode line 2
        int i = 0, j = 0;
        int m = (int) Math.ceil(Math.sqrt(k));   // could make more efficient maybe by getting len(Q) - 1, leaving calculation here for clarity though. also not sure which is faster?

        // pseudocode line 3-6
        // initialise Q and R to all 0s
        while (i <= m) {
            Q[i] = 0;
            R[i] = 0;
            i++;
        }

        // pseudocode line 7-10
        // partially order A in two separate arrays
        while (j < n) {
            Q[A[j] / m] = Q[A[j] / m] + 1; //store count for each quotient in array Q. ie if m = 10, and there are 5 elements in the 20s, then Q[2] will store 5, the quotient 10 divides 5 elements twice with some remainder
            R[A[j] % m] = R[A[j] % m] + 1; //Store the remainders of the same division
            j++;
        }


        // pseudocode line 11-15
        // iterate through both partially sorted lists and do cumulative sums
        // they are now both partially sorted storing the number of elements coming before them
        i = 1;
        while (i <= m) {
            Q[i] = Q[i] + Q[i - 1];
            R[i] = R[i] + R[i - 1];
            i++;
        }


        // pseudocode line 16-21
        //storing elements in remainder sorted order in list B
        j = n - 1; //initialise j to last element
        while (j >= 0) { //loop through backwards
            int d = A[j] % m;         //get quotient
            R[d] = R[d] - 1;          //decrement the remainder INDEX list
            B[R[d]] = A[j];           //store A value in appropriate remainder sorted position
            j--;
        }


        // pseudocode line 22-27
        // take reminder sorted list and now quotient sort it
        // since this is stable sorting - they will stay remainder sorted within their quotient group
        i = n - 1;                  // initialise i to last element
        while (i >= 0) {
            int d = B[i] / m;       // get last element of B, find its quotient group
            Q[d] = Q[d] - 1;        // decrement the associated index position of d
            A[Q[d]] = B[i];         // store the element in the correct location in A
            i--;
        }

        return A;
    }

    public static Integer[] callaruCS(Integer[] A){
        if (A == null || A.length < 2) return A; //safety check
        int n = A.length;

        int k = A[0];
        for (int x : A) if (x > k) k = x;

        int m = (int) Math.ceil(Math.sqrt(k)); //need for making size of arrays

        Integer[] B = new Integer[n];
        Integer[] Q = new Integer[m + 1];     // quotient  array
        Integer[] R = new Integer[m + 1];     // remainder array

        aruCountingSort(A, B, Q, R, k);
        return A;
    }
}
