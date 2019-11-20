package examples.task3;

import java.io.File;

import utils.MyUtils;

public class Task3 {

    private File file = new File("src/main/java/examples/task3/t3.jpg");

    private double a = 0;
    private double b = 599;

    public int check(double x, double y) {
        int tmpX = (int) MyUtils.denormalize(x, a, b);
        int tmpY = (int) MyUtils.denormalize(y, a, b);
        return checkTest(tmpX, tmpY);
    }

    public int checkTest(int x, int y) {
        var color = MyUtils.getColor(file, x, y);
        if (color.getRed() == 237 && color.getGreen() == 27 && color.getBlue() == 36) {
            return 1;
        } else {
            return 0;
        }
    }

    public int[] generate(double[][] test) {
        var res = new int[test.length];
        for (int i = 0; i < test.length; i++) {
            res[i] = check(test[i][0], test[i][1]);
        }
        return res;
    }

    public double[][] sample(int n) {
        return MyUtils.normalization(sampleTest(n), false, a, b);
    }

    public double[][] sampleTest(int n) {
        return MyUtils.generateForTask3And4(n, (int) b);
    }


}
