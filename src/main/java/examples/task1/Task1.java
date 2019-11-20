package examples.task1;

import utils.MyUtils;

public class Task1 {

    private int[][] exm = new int[][]{
            {0, 1, 0, 1},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 1}
    };
    private double a = 0;
    private double b = 3.99;

    public int check(double x, double y) {
        double tmpX = MyUtils.denormalize(x, a, b);
        double tmpY = MyUtils.denormalize(y, a, b);
        return checkTest(tmpX, tmpY);
    }

    public int checkTest(double x, double y) {
        int tmpX = (int) x;
        int tmpY = (int) y;
        return exm[tmpY][tmpX];
    }

    public int[] generate(double[][] test) {
        var res = new int[test.length];
        for (int i = 0; i < test.length; i++) {
            res[i] = check(test[i][0], test[i][1]);
        }
        return res;
    }

    public double[][] sample() {
        return MyUtils.normalization(sampleTest(), false, a, b);
    }

    public double[][] sampleTest() {
        var test = new double[][]{
                {0, 0},
                {1, 0},
                {2, 0},
                {3, 0},
                {0, 1},
                {1, 1},
                {2, 1},
                {3, 1},
                {0, 2},
                {1, 2},
                {2, 2},
                {3, 2},
                {0, 3},
                {1, 3},
                {2, 3},
                {3, 3}
        };

        return test;
    }
}
