package com.gmail.hidekishima.helloworld.stat;

import java.text.NumberFormat;

import cern.colt.list.DoubleArrayList;
import cern.jet.stat.quantile.DoubleQuantileFinder;
import cern.jet.stat.quantile.QuantileFinderFactory;

/**
 * Calculate quantiles using Colt (a set of Open Source Libraries for 
 * High Performance Scientific and Technical Computing in Java.)
 * http://acs.lbl.gov/software/colt/
 * 
 * @author Hideki Shima
 *
 */
public class HelloQuantile {

  public static void main(String[] args) {

    int max_N = -1;
    int quantiles = 1;
    double epsilon = 0.00;
    double delta = 0.000;
    double[] phis = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
//    double[] phis = {0.001, 0.01, 0.1, 0.5, 0.9, 0.99, 0.999, 1.0};
//    int max_N = -1;
//    int quantiles = 100;
//    double epsilon = 0.001;
//    double delta = 0.0001;
//    double[] phis = {0.001, 0.01, 0.1, 0.5, 0.9, 0.99, 0.999, 1.0};
    DoubleQuantileFinder f = 
      QuantileFinderFactory.newDoubleQuantileFinder(false, max_N, epsilon, delta, quantiles, null);
    
    double[] confs = {0.001,0.002,0.002,0.002,0.003,0.003,0.003,0.003,0.003,0.003,
            0.01,0.01,0.01,0.01,0.01,0,01,0.04,0.05,0.05,0.07,0.4,1.0};
    for ( double d : confs ) {
      f.add(d);
    }    
    System.out.println(new DoubleArrayList(phis));

    DoubleArrayList approxQuantiles = f.quantileElements(new DoubleArrayList(phis));
    
    NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setMaximumFractionDigits(4);
    System.out.println(approxQuantiles.size()+": "+approxQuantiles);
    
  }
  
}
