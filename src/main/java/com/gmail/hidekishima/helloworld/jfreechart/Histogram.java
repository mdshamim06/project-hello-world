package com.gmail.hidekishima.helloworld.jfreechart;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

/**
 * Draw a histogram.
 * 
 * @author Hideki Shima
 *
 */
public class Histogram extends JFrame {
  
  private static final long serialVersionUID = 1L;

  public static void main(String[] args) {
    Histogram frame = new Histogram();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 500, 500);
    frame.setTitle("Sample graph");
    frame.setVisible(true);
  }

  public Histogram() {
    JFreeChart chart = createChart();
    
    try {
      //ChartUtilities.saveChartAsPNG(new File("test.png"), chart, 300, 300);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    ChartPanel cpanel = new ChartPanel(chart);
    getContentPane().add(cpanel, BorderLayout.CENTER);
  }
  
  private JFreeChart createChart() {
    double[] v1 = {0.2029, 0.2056, 0.2072, 0.2104, 0.2106, 0.2116, 0.2119, 0.2135, 0.2146, 0.2149, 0.2154, 0.2165, 0.2203, 0.2234, 0.2237, 0.2242, 0.2264, 0.2265, 0.2266, 0.2267, 0.2274, 0.2276, 0.2288, 0.2291, 0.2292, 0.2314, 0.2351, 0.2369, 0.2429, 0.2456, 0.2482, 0.2485, 0.2515, 0.2567, 0.2583, 0.2663, 0.2682, 0.2690, 0.2708, 0.2710, 0.2714, 0.2722, 0.2733, 0.2764, 0.2765, 0.2789, 0.2790, 0.2800, 0.2856, 0.2859, 0.2880, 0.2897, 0.2901, 0.2932, 0.2933, 0.2952, 0.2962, 0.2980, 0.2991, 0.3027, 0.3034, 0.3049, 0.3050, 0.3056, 0.3059, 0.3068, 0.3068, 0.3078, 0.3084, 0.3104, 0.3153, 0.3159, 0.3188, 0.3189, 0.3273, 0.3283, 0.3283, 0.3284, 0.3286, 0.3288, 0.3291, 0.3315, 0.3323, 0.3352, 0.3373, 0.3383, 0.3399, 0.3401, 0.3415, 0.3442, 0.3467, 0.3485, 0.3516, 0.3520, 0.3524, 0.3526, 0.3655, 0.3681, 0.3753, 0.3852, 0.3881, 0.3909, 0.3917, 0.3918, 0.3951, 0.3959, 0.3963, 0.3970, 0.3977, 0.3978, 0.4110, 0.4110, 0.4112, 0.4131, 0.4139, 0.4140, 0.4164, 0.4167, 0.4213, 0.4217, 0.4258, 0.4266, 0.4269, 0.4287, 0.4311, 0.4441, 0.4475, 0.4554, 0.4563, 0.4571, 0.4613, 0.4654, 0.4674, 0.4727, 0.4784, 0.4800, 0.4831, 0.4852, 0.4926, 0.4944, 0.5000, 0.5000, 0.5000, 0.5058, 0.5065, 0.5074, 0.5125, 0.5144, 0.5226, 0.5229, 0.5290, 0.5306, 0.5328, 0.5345, 0.5359, 0.5464, 0.5475, 0.5686, 0.5730, 0.5731, 0.5747, 0.5769, 0.5953, 0.6057, 0.6203, 0.6203, 0.6268, 0.6579, 0.6648, 0.6682, 0.6870, 0.6924, 0.6950, 0.6990, 0.7033, 0.7207, 0.7260, 0.7317, 0.7334, 0.7353, 0.7355, 0.7374, 0.7383, 0.7405, 0.7425, 0.7879, 0.8013, 0.8283, 0.8307, 0.8349, 0.8466, 0.8502, 0.8541, 0.8583, 0.8605, 0.8628, 0.8763, 0.8768, 0.8920, 0.8930, 0.9045, 0.9093, 0.9317, 0.9324, 0.9629, 0.9819};
    double[] v2 = {0.2004, 0.2008, 0.2009, 0.2013, 0.2015, 0.2017, 0.2032, 0.2032, 0.2046, 0.2054, 0.2056, 0.2057, 0.2059, 0.2063, 0.2069, 0.2070, 0.2075, 0.2077, 0.2079, 0.2080, 0.2083, 0.2092, 0.2102, 0.2116, 0.2118, 0.2122, 0.2128, 0.2128, 0.2150, 0.2153, 0.2155, 0.2155, 0.2161, 0.2166, 0.2169, 0.2171, 0.2173, 0.2177, 0.2180, 0.2180, 0.2194, 0.2199, 0.2203, 0.2205, 0.2206, 0.2206, 0.2210, 0.2210, 0.2210, 0.2211, 0.2214, 0.2225, 0.2227, 0.2227, 0.2238, 0.2240, 0.2240, 0.2248, 0.2250, 0.2255, 0.2279, 0.2282, 0.2288, 0.2301, 0.2301, 0.2309, 0.2311, 0.2315, 0.2319, 0.2320, 0.2320, 0.2326, 0.2331, 0.2337, 0.2338, 0.2339, 0.2340, 0.2344, 0.2347, 0.2350, 0.2350, 0.2351, 0.2353, 0.2362, 0.2365, 0.2367, 0.2375, 0.2379, 0.2382, 0.2391, 0.2397, 0.2414, 0.2423, 0.2444, 0.2445, 0.2456, 0.2459, 0.2464, 0.2471, 0.2472, 0.2475, 0.2475, 0.2475, 0.2475, 0.2479, 0.2487, 0.2505, 0.2510, 0.2520, 0.2525, 0.2529, 0.2545, 0.2558, 0.2564, 0.2571, 0.2574, 0.2575, 0.2583, 0.2584, 0.2586, 0.2588, 0.2592, 0.2595, 0.2601, 0.2612, 0.2617, 0.2651, 0.2654, 0.2660, 0.2660, 0.2664, 0.2666, 0.2669, 0.2672, 0.2679, 0.2682, 0.2694, 0.2706, 0.2709, 0.2714, 0.2715, 0.2718, 0.2722, 0.2725, 0.2733, 0.2733, 0.2739, 0.2748, 0.2749, 0.2749, 0.2754, 0.2754, 0.2757, 0.2758, 0.2791, 0.2794, 0.2799, 0.2803, 0.2818, 0.2828, 0.2832, 0.2832, 0.2835, 0.2851, 0.2867, 0.2868, 0.2895, 0.2900, 0.2901, 0.2913, 0.2918, 0.2925, 0.2932, 0.2944, 0.2976, 0.2983, 0.2985, 0.2990, 0.3015, 0.3024, 0.3038, 0.3062, 0.3063, 0.3070, 0.3077, 0.3086, 0.3091, 0.3104, 0.3107, 0.3107, 0.3126, 0.3138, 0.3140, 0.3146, 0.3146, 0.3152, 0.3163, 0.3187, 0.3188, 0.3190, 0.3291, 0.3294, 0.3299, 0.3301, 0.3304, 0.3316, 0.3336, 0.3341, 0.3353, 0.3365, 0.3370, 0.3398, 0.3411, 0.3418, 0.3437, 0.3474, 0.3479, 0.3495, 0.3496, 0.3497, 0.3497, 0.3518, 0.3525, 0.3527, 0.3532, 0.3534, 0.3535, 0.3537, 0.3541, 0.3541, 0.3543, 0.3552, 0.3554, 0.3567, 0.3585, 0.3605, 0.3606, 0.3638, 0.3639, 0.3648, 0.3714, 0.3743, 0.3743, 0.3747, 0.3778, 0.3780, 0.3804, 0.3833, 0.3853, 0.3862, 0.3863, 0.3877, 0.3890, 0.3902, 0.3919, 0.3920, 0.3940, 0.3944, 0.3994, 0.3997, 0.4034, 0.4035, 0.4081, 0.4098, 0.4170, 0.4217, 0.4218, 0.4238, 0.4257, 0.4325, 0.4344, 0.4385, 0.4386, 0.4397, 0.4469, 0.4614, 0.4681, 0.4691, 0.4727, 0.4746, 0.4761, 0.4848, 0.4918, 0.5000, 0.5000, 0.5150, 0.5213, 0.5401, 0.5644, 0.5679, 0.5685, 0.5720, 0.5795, 0.5840, 0.5860, 0.5866, 0.5875, 0.5949, 0.5978, 0.6090, 0.6115, 0.6172, 0.6256, 0.6316, 0.6384, 0.6433, 0.6467, 0.6495, 0.6682, 0.6684, 0.6815, 0.7136, 0.7178, 0.7534, 0.8257, 0.8559, 0.8615, 0.9000};
    HistogramDataset dataset = new HistogramDataset();
    int bin = 30;
    dataset.addSeries("POS", v1, bin, 0, 1);
    dataset.addSeries("NEG", v2, bin, 0, 1);
    
    JFreeChart chart = ChartFactory.createHistogram(
              "Histogram Demo", 
              null, 
              null, 
              dataset, 
              PlotOrientation.VERTICAL, 
              true, 
              false, 
              false
          );

    chart.setBackgroundPaint(new Color(230,230,230));
    XYPlot xyplot = (XYPlot)chart.getPlot();
    xyplot.setForegroundAlpha(0.7F);
    xyplot.setBackgroundPaint(Color.WHITE);
    xyplot.setDomainGridlinePaint(new Color(150,150,150));
    xyplot.setRangeGridlinePaint(new Color(150,150,150));
    XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
    xybarrenderer.setShadowVisible(false);
    xybarrenderer.setBarPainter(new StandardXYBarPainter()); 
//    xybarrenderer.setDrawBarOutline(false);
    return chart;
  }
  
}