import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        ArrayList<String> sorts = new ArrayList<>();
        //testingSuite.writeRandomDataSets();
        sorts.addAll(testingTools.sortGenerator("aruCountingSortSpace", 13));
        sorts.addAll(testingTools.sortGenerator("countingSortSpace", 13));
        testingSuite.testSort(sorts, "randomData.csv", "results.txt");
    }
}