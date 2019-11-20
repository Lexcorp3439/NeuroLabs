package utils;

import java.util.HashSet;
import java.util.Random;

public class KfoldArr {
    private double[] sample;
    private double[] test;
    private double[] train;
    private int current;
    private int k;
    private int trainSize;

    public KfoldArr(double[] sample) {
        this.sample = sample;
    }

    public void generate(int k) {
        this.sample = MyUtils.shuffle(sample);
        this.k = sample.length / k;
        this.trainSize = sample.length - this.k;
        this.current = 0;
        next();
    }

    public void next() {
        test = new double[k];
        train = new double[trainSize];

        int testI = 0;
        for (int i = current; i < current + k; i++) {
            test[testI] = sample[i];
            testI++;
        }

        int check = 0;
        int trainI = 0;
        while (check < sample.length) {
            if (check == current) {
                check = current + k;
            } else {
                train[trainI] = sample[check];
                trainI++;
                check++;
            }
        }

        current = current + k;
        if (current == sample.length) {
            current = 0;
        }
    }

    public void generateRandom(int k) {
        var random = new Random();
        var set = new HashSet<Integer>();

        this.k = sample.length / k;
        trainSize = sample.length - k;

        test = new double[this.k];
        train = new double[trainSize];

        int testCount = 0;
        int learnCount = 0;

        for (int i = 0; i < this.k; i++) {
            while (!set.add(random.nextInt(sample.length))) {
                ;
            }
        }

        for (int i = 0; i < sample.length; i++) {
            if (set.contains(i)) {
                test[testCount] = sample[i];
                testCount++;
            } else {
                train[learnCount] = sample[i];
                learnCount++;
            }
        }
    }

    public double[] getTest() {
        return test.clone();
    }

    public double[] getTrain() {
        return train.clone();
    }
}
