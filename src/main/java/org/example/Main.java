package org.example;


import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/ildar/Documents/Java itis/Pancake_Sort/src/main/java/org/example/out.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("/Users/ildar/Documents/Java itis/Pancake_Sort/src/main/java/org/example/data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split(" ");
                int[] intArray = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    intArray[i] = Integer.parseInt(numbers[i]);
                }
                System.out.println(Arrays.toString(intArray));
                long startTime = System.currentTimeMillis();
                int iterations = PancakeSort(intArray);
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                System.out.print("Время выполнения алгоритма: " + executionTime + " миллисекунд. ");
                System.out.println("Длина массива " + intArray.length);
                System.out.println(Arrays.toString(intArray));
                writeDataToFile(writer,intArray.length,executionTime,iterations);

            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении данных из файла: " + e.getMessage());
        }

    }
    public static int PancakeSort(int[] arr) {
        Integer iterations = 0;
        int n = arr.length;
        for (int curSize = n; curSize > 1; curSize--) {
            iterations++;
            int maxIndex = findMaxIndex(arr, curSize, iterations);
            if (maxIndex != curSize - 1) {
                flip(arr, maxIndex + 1);
                flip(arr, curSize);
                iterations++;

            }
        }
        return iterations;
    }

    private static void flip(int[] arr, int k) {
        int i = 0;
        while (i < k / 2) {
            int temp = arr[i];
            arr[i] = arr[k - i - 1];
            arr[k - i - 1] = temp;
            i++;
        }
    }

    private static int findMaxIndex(int[] arr, int n, Integer iterations) {
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            iterations++;
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static void writeDataToFile(BufferedWriter writer, int one, long two, int three) throws IOException {
        writer.write(Integer.toString(one));
        writer.write(" ");
        writer.write(Long.toString(two));
        writer.write(" ");
        writer.write(Integer.toString(three));
        writer.write(" ");
        writer.newLine();
    }
}