import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        ArrayList<String> sorts = new ArrayList<>();



        sorts = testingTools.sortGenerator("aruCountingSort", 13);
        testingSuite.testSort(sorts, "nearlySortedData.csv", "results.txt");

        /*
         */
    }
}