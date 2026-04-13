import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class testingSuite {
    public static void writeRandomDataSets() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("randomData.csv"));
            for (int i = 10; i <= 100000; i *= 10) {
                Integer[] arr = testingTools.arrayGenerator(i, 0, Integer.MAX_VALUE);
                StringBuilder out = new StringBuilder(Arrays.toString(arr));
                out = new StringBuilder(out.substring(1, out.length() - 1));
                writer.write(out.toString());
                writer.newLine();
            }
                writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred: "
                    + e.getMessage());
        }
    }
    public static void writeNearlySortedDataSets() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("nearlySortedData.csv"));
            for (int i = 10; i <= 100000; i *= 10) {
                Integer[] arr = testingTools.arrayGenerator(i, 0, Integer.MAX_VALUE);
                testingTools.edgeSort(arr, 10);
                StringBuilder out = new StringBuilder(Arrays.toString(arr));
                out = new StringBuilder(out.substring(1, out.length() - 1));
                writer.write(out.toString());
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred: "
                    + e.getMessage());
        }
    }
    public static ArrayList<Integer[]> readDataSet(String fileName) {
        ArrayList<Integer[]> data = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int i = 10;
            while ((line = reader.readLine()) != null) {
                Integer[] vals = new Integer[i];
                String[] strVals = line.split(", ");
                for (int j = 0; j < i; j++) {
                    vals[j] = Integer.parseInt(strVals[j]);
                }
                //Clone needed so data isn't lost
                data.add(vals.clone());
                i *= 10;
            }

            reader.close();
        }
        catch (IOException e) {
            System.out.println(
                    "An error occurred while reading the file: "
                            + e.getMessage());
        }
        return data;
    }
    public static void testSort(ArrayList<String> sortNames, String fileInput, String fileOutput) {
    StringBuilder sb = new StringBuilder();
        for (String sort : sortNames) {
            ArrayList<Integer[]> data = readDataSet(fileInput);
            ArrayList<Long> out = testingTools.chooseSort(sort, data);
            sb.append(sort).append(": ");
            for (Long num : out) {
                sb.append(num);
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 1).setCharAt(sb.length() - 1, '\n');
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printResults() {
        System.out.println("Sort, 100 items, 1000 items, 10000 items, 100000 items, 1000000 items");
        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}