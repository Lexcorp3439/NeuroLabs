package labs.lab0.parts;

import java.awt.Color;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import utils.XYChartUtils;
import examples.task1.Task1;
import examples.task3.Task3;
import examples.task4.Task4;
import examples.task5.Task5;

public class Part2 {
    private Task1 task1;
    private Task3 task3;
    private Task4 task4;
    private Task5 task5;


    public Part2(Task1 task1, Task3 task3, Task4 task4, Task5 task5) {
        this.task1 = task1;
        this.task3 = task3;
        this.task4 = task4;
        this.task5 = task5;
    }

    public void build() {
        var sample1 = task1.sample();
        var sample3 = task3.sample(1500);
        var sample4 = task4.sample(2000);
        var sample5 = task5.sample(10000);

        var generate1 = task1.generate(sample1);
        var generate3 = task3.generate(sample3);
        var generate4 = task4.generate(sample4);
        var generate5 = task5.generate(sample5);

        draw1(sample1, generate1);
        draw3(sample3, generate3);
        draw4(sample4, generate4);
        draw5(sample5, generate5);

    }

    private void draw1(double[][] sample, int[] res) {
        // data preparation
        double[] xX = new double[sample.length / 2];
        double[] yX = new double[sample.length / 2];
        double[] xO = new double[sample.length / 2];
        double[] yO = new double[sample.length / 2];
        int xI = 0;
        int oI = 0;

        for (int i = 0; i < sample.length; i++) {
            if (res[i] == 1) {
                xX[xI] = sample[i][0];
                yX[xI] = sample[i][1];
                xI++;
            } else {
                xO[oI] = sample[i][0];
                yO[oI] = sample[i][1];
                oI++;
            }
        }

        XYChart chart = XYChartUtils.generateXYChart("Task1", 16);

        // Series
        chart.addSeries("Class X", xX, yX);
        XYSeries series = chart.addSeries("Class O", xO, yO);
        series.setMarker(SeriesMarkers.DIAMOND);

        new SwingWrapper(chart).displayChart();

        XYChartUtils.save(chart, "./draw1", 1000);
    }

    private void draw3(double[][] sample, int[] res) {
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

        XYChart chart = XYChartUtils.generateXYChart("Task3", 10);

        // Series
        chart.addSeries("Class 1", xX, yX);
        XYSeries series = chart.addSeries("Class 0", xO, yO);
        series.setMarker(SeriesMarkers.DIAMOND);

        new SwingWrapper(chart).displayChart();

        XYChartUtils.save(chart, "./draw3", 1000);
    }

    private void draw4(double[][] sample, int[] res) {
        // data preparation
        int count0 = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0;
        int i0 = 0, i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, i6 = 0;

        for (int re : res) {
            if (re == 0) {
                count0++;
            }
            if (re == 1) {
                count1++;
            }
            if (re == 2) {
                count2++;
            }
            if (re == 3) {
                count3++;
            }
            if (re == 4) {
                count4++;
            }
            if (re == 5) {
                count5++;
            }
            if (re == 6) {
                count6++;
            }
        }
        double[] x0 = new double[count0];
        double[] y0 = new double[count0];
        double[] x1 = new double[count1];
        double[] y1 = new double[count1];
        double[] x2 = new double[count2];
        double[] y2 = new double[count2];
        double[] x3 = new double[count3];
        double[] y3 = new double[count3];
        double[] x4 = new double[count4];
        double[] y4 = new double[count4];
        double[] x5 = new double[count5];
        double[] y5 = new double[count5];
        double[] x6 = new double[count6];
        double[] y6 = new double[count6];

        for (int i = 0; i < sample.length; i++) {
            switch (res[i]) {
                case 0:
                    x0[i0] = sample[i][0];
                    y0[i0] = 1 - sample[i][1];
                    i0++;
                    break;
                case 1:
                    x1[i1] = sample[i][0];
                    y1[i1] = 1 - sample[i][1];
                    i1++;
                    break;
                case 2:
                    x2[i2] = sample[i][0];
                    y2[i2] = 1 - sample[i][1];
                    i2++;
                    break;
                case 3:
                    x3[i3] = sample[i][0];
                    y3[i3] = 1 - sample[i][1];
                    i3++;
                    break;
                case 4:
                    x4[i4] = sample[i][0];
                    y4[i4] = 1 - sample[i][1];
                    i4++;
                    break;
                case 5:
                    x5[i5] = sample[i][0];
                    y5[i5] = 1 - sample[i][1];
                    i5++;
                    break;
                case 6:
                    x6[i6] = sample[i][0];
                    y6[i6] = 1 - sample[i][1];
                    i6++;
                    break;
            }
        }
        XYChart chart = XYChartUtils.generateXYChart("Task4", 10);

        XYSeries series0 = chart.addSeries("Class 0", x0, y0);
        series0.setMarkerColor(Color.YELLOW);

        XYSeries series1 = chart.addSeries("Class 1", x1, y1);
        series1.setMarker(SeriesMarkers.DIAMOND);
        series1.setMarkerColor(Color.PINK);

        XYSeries series2 = chart.addSeries("Class 2", x2, y2);
        series2.setMarker(SeriesMarkers.CIRCLE);
        series2.setMarkerColor(Color.BLACK);

        XYSeries series3 = chart.addSeries("Class 3", x3, y3);
        series3.setMarker(SeriesMarkers.CROSS);
        series3.setMarkerColor(Color.MAGENTA);

        XYSeries series4 = chart.addSeries("Class 4", x4, y4);
        series4.setMarker(SeriesMarkers.SQUARE);
        series4.setMarkerColor(Color.GRAY);

        XYSeries series5 = chart.addSeries("Class 5", x5, y5);
        series5.setMarker(SeriesMarkers.OVAL);
        series5.setMarkerColor(Color.BLUE);

        XYSeries series6 = chart.addSeries("Class 6", x6, y6);
        series6.setMarker(SeriesMarkers.TRIANGLE_DOWN);
        series6.setMarkerColor(Color.GREEN);

        new SwingWrapper(chart).displayChart();

        XYChartUtils.save(chart, "./draw4", 1000);
    }


    private void draw5(double[] sample, double[] res) {
        XYChart chart = XYChartUtils.generateXYChart("Task3", 3);

        chart.addSeries("f(P)", sample, res);
        // Show it
        new SwingWrapper(chart).displayChart();

        XYChartUtils.save(chart, "./draw5", 1000);
    }
}
