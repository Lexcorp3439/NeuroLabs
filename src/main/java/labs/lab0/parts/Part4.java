package labs.lab0.parts;

import java.awt.Color;
import java.util.Arrays;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;

import examples.task3.Task3;
import utils.Kfold;
import utils.XYChartUtils;

public class Part4 {
    private Task3 task3;

    public Part4(Task3 task3) {
        this.task3 = task3;
    }

    public void build() {
        int k = 4;
        double[][] sample = task3.sample(2000);
        double[][] train;
        double[][] test;

        var kfold = new Kfold(sample);
        kfold.generateRandom(k);

        train = kfold.getTrain();
        test = kfold.getTest();

        preparation(train, test, "./4.3.draw");

        kfold.generate(k);

        for (int i = 0; i < k; i++) {
            train = kfold.getTrain();
            test = kfold.getTest();
            preparation(train, test, "" +( i + 1 ) + ".draw");
            if (i != k - 1) kfold.next();
        }
    }

    private void preparation(double[][] train, double[][] test, String title) {
        int[] genTrain = task3.generate(train);
        int[] genTest = task3.generate(test);

        XYChart chart = XYChartUtils.generateXYChart("Part4", 10);

        draw3(train, genTrain, chart, "Class 1 train", "Class 0 train", Color.GREEN, Color.YELLOW);
        draw3(test, genTest, chart, "Class 1 test", "Class 0 test", Color.RED, Color.black);

        new SwingWrapper(chart).displayChart();

        XYChartUtils.save(chart, title, 1000);
    }

    private void draw3(double[][] sample, int[] res, XYChart chart, String title1, String title2, Color color1, Color color2) {
        // data preparation
        int xCount = 0, xI = 0, oI = 0;
        for (int re : res) {
            if (re == 1) {
                xCount++;
            }
        }
        double[] xX = new double[xCount];
        double[] yX = new double[xCount];
        double[] xO = new double[sample.length - xCount];
        double[] yO = new double[sample.length - xCount];

        for (int i = 0; i < sample.length; i++) {
            if (res[i] == 1) {
                xX[xI] = sample[i][0];
                yX[xI] = 1 - sample[i][1];
                xI++;
            } else {
                xO[oI] = sample[i][0];
                yO[oI] = 1 - sample[i][1];
                oI++;
            }
        }

        // Series
        if (xI != 0) {
            XYSeries series1 = chart.addSeries(title1, xX, yX);
            series1.setMarkerColor(color1);
        }
        if (oI != 0) {
            XYSeries series2 = chart.addSeries(title2, xO, yO);
            series2.setMarkerColor(color2);
        }
    }

}
