package labs.lab0.parts;

import java.util.Random;

import utils.MyUtils;
import examples.task3.Task3;
import examples.task4.Task4;
import examples.task5.Task5;

public class Part3 {
    private Task3 task3;
    private Task4 task4;
    private Task5 task5;


    public Part3(Task3 task3, Task4 task4, Task5 task5) {
        this.task3 = task3;
        this.task4 = task4;
        this.task5 = task5;
    }

    public void build() {
        var generate3 = task3.generate(task3.sample(1500));
        var generate4 = task4.generate(task4.sample(2000));
        var generate5 = task5.generate(task5.sample(10000));

        confusion3(generate3);
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        confusion4(generate4);
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        confusion5(generate5, 20);
    }

    private void confusion3(int[] expected) {
        var actual = MyUtils.confusion(expected, 20, 2);
        MyUtils.confusionMatrix(actual, expected, 2);
    }

    private void confusion4(int[] expected) {
        var actual = MyUtils.confusion(expected, 20, 7);
        MyUtils.confusionMatrix(actual, expected, 7);
    }


    private void confusion5(double[] expected, int k) {
        var actual = expected.clone();
        var random = new Random();
        var n = expected.length;

        var maxErr = Double.MIN_VALUE;
        var absErr = 0d;
        var refErr = 0d;

        for (int i = 0; i < n; i++) {
            int next = random.nextInt(100);
            if (next < k) {
                actual[i] = actual[i] * 1.2;
            }
        }

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                var tmp = Math.abs(expected[i] - actual[i]);
                absErr += Math.abs(expected[i] - actual[i]);
                refErr += actual[i] / expected[i];
                if (maxErr < tmp) maxErr = tmp;
            }
        }

        System.out.println("Абсолютная ошибка: " + absErr / n);
        System.out.println("Относительная ошибка: " + refErr / n);
        System.out.println("Максимальная по модулю ошибка: " + maxErr);
    }
}
