package utils;

import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

public class XYChartUtils {

    public static void save(XYChart chart, String name, int DPI) {
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, name, BitmapEncoder.BitmapFormat.PNG, DPI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static XYChart generateXYChart(
            String title,
            int markerSize
    ) {
        return generateXYChart(
                500,
                500,
                title,
                "X",
                "Y",
                XYSeries.XYSeriesRenderStyle.Scatter,
                false,
                Styler.LegendPosition.OutsideE,
                markerSize
        );
    }


    public static XYChart generateXYChart(
            int width,
            int height,
            String title,
            String xTitle,
            String yTitle,
            XYSeries.XYSeriesRenderStyle style,
            boolean chartVisible,
            Styler.LegendPosition legendPosition,
            int markerSize
    ) {
        XYChart chart = new XYChartBuilder().width(500).height(500).title("Task3").xAxisTitle("X").yAxisTitle("Y").build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setMarkerSize(3);

        return chart;
    }
}
