import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        //System.out.println(System.getProperty("user.dir"));
        testingSuite.writeRandomDataSets();
        ArrayList<String> sorts = testingTools.sortGenerator("mergeSort", 10);
        testingSuite.testSort(sorts, "randomData.csv", "results.txt");
    }
}