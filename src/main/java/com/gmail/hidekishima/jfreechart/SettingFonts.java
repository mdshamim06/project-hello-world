package com.gmail.hidekishima.jfreechart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;

/**
 * Detting a font.
 * @author Hideki Shima
 *
 */
public class SettingFonts {
  public static void main(String[] args) {
    ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
    JFreeChart chart = ChartFactory.createScatterPlot("Map", "X", "Y", null,
            PlotOrientation.VERTICAL, true, true, false);

    //Setting Meiryo font
    Font font = new Font("Meiryo", Font.PLAIN, 12);
    chart.getLegend().setItemFont(font);
    chart.getTitle().setFont(font);
    chart.getXYPlot().getDomainAxis().setLabelFont(font);
    chart.getXYPlot().getRangeAxis().setLabelFont(font);
  }
}
