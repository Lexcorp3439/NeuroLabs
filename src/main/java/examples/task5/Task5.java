package examples.task5;

import utils.MyUtils;

public class Task5 {
    private double a = 0;
    private double b = 599;


    public double check(double x) {
        return Math.sin(Math.abs(x + 10)) * Math.sin(x * x) * Math.abs(Math.cos(x * x)) * Math.abs(x + 8);
    }

    public double[] generate(double[] test) {
        var res = new double[test.length];
        for (int i = 0; i < test.length; i++) {
            res[i] = check(test[i]);
        }
        res[0] = 0.0;
        return res;
    }

    public double[] sample(int n) {
        return MyUtils.generateForTask5(n);
    }


}
