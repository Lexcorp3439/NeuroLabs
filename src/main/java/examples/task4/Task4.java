package examples.task4;

import java.io.File;

import utils.MyUtils;

public class Task4 {

    private File file = new File("src/main/java/examples/task4/t4.jpg");

    private double a = 0;
    private double b = 599;

    public int check(double x, double y) {
        int tmpX = (int) MyUtils.denormalize(x, a, b);
        int tmpY = (int) MyUtils.denormalize(y, a, b);
        return checkTest(tmpX, tmpY);
    }

    public int checkTest(int x, int y) {
        var color = MyUtils.getColor(file, x, y);
        if (color.getRed() == 254 && color.getGreen() == 242 && color.getBlue() == 0) {
            return 0;
        }
        if (color.getRed() == 254 && color.getGreen() == 174 && color.getBlue() == 201) {
            return 1;
        }
        if (color.getRed() == 185 && color.getGreen() == 122 && color.getBlue() == 87) {
            return 2;
        }
        if (color.getRed() == 163 && color.getGreen() == 73 && color.getBlue() == 163) {
            return 3;
        }
        if (color.getRed() == 127 && color.getGreen() == 127 && color.getBlue() == 127) {
            return 4;
        }
        if (color.getRed() == 63 && color.getGreen() == 71 && color.getBlue() == 204) {
            return 5;
        }
        if (color.getRed() == 35 && color.getGreen() == 177 && color.getBlue() == 77) {
            return 6;
        }
        return -1;
    }

    public int[] generate(double[][] test) {
        var res = new int[test.length];
        for (int i = 0; i < test.length; i++) {
            res[i] = check(test[i][0], test[i][1]);
        }
        return res;
    }

    public int[] generateTest(double[][] test) {
        var res = new int[test.length];
        var count  = 0;
        for (double[] doubles : test) {
            var tmp = check(doubles[0], doubles[1]);
            if (tmp != -1) {
                res[count] = tmp;
                count++;
            }
        }
        var expected = new int[count];
        System.arraycopy(res, 0, expected, 0, count);
        return expected;
    }

    public double[][] sample(int n) {
        return MyUtils.normalization(sampleTest(n), false, a, b);
    }

    public double[][] sampleTest(int n) {
        return MyUtils.generateForTask3And4(n, (int) b);
    }
}
