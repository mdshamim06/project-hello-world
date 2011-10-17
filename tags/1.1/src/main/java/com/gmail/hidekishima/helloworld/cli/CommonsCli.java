package com.gmail.hidekishima.helloworld.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

public class CommonsCli {

  private static Options createOptions() {
    Options options = new Options();
    options.addOption("h", "help", false, "print this message and exit");
    options.addOption("i", "input", true, "input file");
    return options;
  }

  private static void showHelp(Options options) {
    HelpFormatter h = new HelpFormatter();
    h.printHelp("help", options);
    System.exit(-1);
  }
  
  public static void main(String[] args) {
    Options options = createOptions();
    try {
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);
      @SuppressWarnings("unused")
      String inputPath = cmd.getOptionValue("i"); 
    } catch (Exception e) {
      e.printStackTrace();
      showHelp(options);
    }
  }
}
