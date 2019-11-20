package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class MyUtils {

    public static double[][] normalization(double[][] test, boolean de, double a, double b) {
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                if (de) {
                    test[i][j] = denormalize(test[i][j], a, b);
                } else {
                    test[i][j] = normalize(test[i][j], a, b);
                }
            }
        }
        return test;
    }


    public static double denormalize(double t, double a, double b) {
        return (b - a) * t;
    }


    public static double normalize(double x, double a, double b) {
        return x / (b - a);
    }


    public static Color getColor(File file, int pxX, int pxY) {
        BufferedImage img = null;
        Color color = null;
        try {
            img = ImageIO.read(file);
            int rgb = img.getRGB(pxX, pxY);
            return new Color(rgb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Color.BLACK;
    }


    public static double[][] generateForTask3And4(int n, int upper) {
        var random = new Random();
        var arr = new double[n][2];
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(upper);
            int y = random.nextInt(upper);
            arr[i][0] = x;
            arr[i][1] = y;
        }
        return arr;
    }


    public static double[] generateForTask5(int n) {
        int a = -10;
        int b = 10;
        var random = new Random();
        var arr = new double[n];
        arr[0] = denormalize(0, a, b) - 10;
        arr[n - 1] = denormalize(1, a, b) - 10;
        for (int i = 1; i < n - 1; i++) {
            double x = random.nextDouble();
            arr[i] = denormalize(x, a, b) - 10;
        }
        return arr;
    }


    public static void confusionMatrix(int[] actual, int[] expected, int n) {
        int[][] matrix = new int[n][n];
        int e = 0;
        int all = expected.length;

        for (int i = 0; i < all; i++) {
            matrix[expected[i]][actual[i]]++;
            if (expected[i] == actual[i]) {
                e++;
            }
        }
        System.out.println("Confusion Matrix");
        MyUtils.matrixToString(matrix);
        System.out.println("-----------------");
        System.out.println("Средняя вероятность ошибки: " + (double) (all - e) / all);
        System.out.println("Средняя вероятность правильного распознавания: " + (double) e / all);
    }


    public static int[] confusion(int[] expected, int k, int n) {
        var actual = expected.clone();
        var random = new Random();
        for (int i = 0; i < expected.length; i++) {
            int next = random.nextInt(100);
            if (next < k) {
                int last = actual[i];
                while (actual[i] == last) {
                    actual[i] = random.nextInt(n);
                }
            }
        }
        return actual;
    }


    public static void matrixToString(double[][] twoDimArray) {
        for (double[] ints : twoDimArray) {                        //идём по строкам
            for (int j = 0; j < twoDimArray[0].length; j++) {   //идём по столбцам
                System.out.print(" " + ints[j] + " ");          //вывод элемента
            }
            System.out.println();        //перенос строки ради визуального сохранения табличной формы
        }
    }


    public static void matrixToString(int[][] twoDimArray) {
        for (int[] ints : twoDimArray) {                        //идём по строкам
            for (int j = 0; j < twoDimArray[0].length; j++) {   //идём по столбцам
                System.out.print(" " + ints[j] + " ");          //вывод элемента
            }
            System.out.println();        //перенос строки ради визуального сохранения табличной формы
        }
    }

    public static int[][] shuffle (int[][] arr) {
        Random random = new Random();
        int[][] newArray = new int[arr.length][arr[0].length];
        List<Integer> indexes = new ArrayList<>(arr.length);
        int count = 0;

        do {
            int var = random.nextInt(arr.length);
            if (!indexes.contains(var)) {
                indexes.add(var);
                newArray[var] = arr[count++];
            }
        } while (count != arr.length);

        return newArray;
    }

    public static double[][] shuffle (double[][] arr) {
        Random random = new Random();
        double[][] newArray = new double[arr.length][arr[0].length];
        List<Integer> indexes = new ArrayList<>(arr.length);
        int count = 0;

        do {
            int var = random.nextInt(arr.length);
            if (!indexes.contains(var)) {
                indexes.add(var);
                newArray[var] = arr[count++];
            }
        } while (count != arr.length);

        return newArray;
    }

    public static double[] shuffle (double[] arr) {
        Random random = new Random();
        double[] newArray = new double[arr.length];
        List<Integer> indexes = new ArrayList<>(arr.length);
        int count = 0;

        do {
            int var = random.nextInt(arr.length);
            if (!indexes.contains(var)) {
                indexes.add(var);
                newArray[var] = arr[count++];
            }
        } while (count != arr.length);

        return newArray;
    }
}
