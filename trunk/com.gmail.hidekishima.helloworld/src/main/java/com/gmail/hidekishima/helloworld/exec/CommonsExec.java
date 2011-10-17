package com.gmail.hidekishima.helloworld.exec;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

/**
 * Modified from tutorial code of commons exec 
 * 
 * @author Hideki Shima
 *
 */
public class CommonsExec {
  
  boolean printInBackground = false;
  
  public void tutorial() throws Exception {
    int exitValue;
    ExecuteWatchdog watchdog = null;
    PrintResultHandler resultHandler;

    CommandLine cmdLine = new CommandLine("AcroRd32.exe");
    cmdLine.addArgument("/p");
    cmdLine.addArgument("/h");
    cmdLine.addArgument("${file}");
    HashMap map = new HashMap();
    map.put("file", new File("invoice.pdf"));
    cmdLine.setSubstitutionMap(map);
    
    Executor executor = new DefaultExecutor();
    executor.setExitValue(1);

    int printJobTimeout = 60 * 1000;
    
    // create a watchdog if requested
    if (printJobTimeout > 0) {
      watchdog = new ExecuteWatchdog(printJobTimeout);
      executor.setWatchdog(watchdog);
    }

    // pass a "ExecuteResultHandler" when doing background printing
    if (printInBackground) {
      System.out.println("[print] Executing non-blocking print job  ...");
      resultHandler = new PrintResultHandler(watchdog);
      executor.execute(cmdLine, resultHandler);
    } else {
      System.out.println("[print] Executing blocking print job  ...");
      exitValue = executor.execute(cmdLine);
      resultHandler = new PrintResultHandler(exitValue);
    }
   
    // some time later the result handler callback was invoked so we
    // can safely request the exit value
    resultHandler.waitFor();
  }

  private class PrintResultHandler extends DefaultExecuteResultHandler {

    private ExecuteWatchdog watchdog;

    public PrintResultHandler(ExecuteWatchdog watchdog) {
      this.watchdog = watchdog;
    }

    public PrintResultHandler(int exitValue) {
      super.onProcessComplete(exitValue);
    }

    public void onProcessComplete(int exitValue) {
      super.onProcessComplete(exitValue);
      System.out.println("[resultHandler] The document was successfully printed ...");
    }

    public void onProcessFailed(ExecuteException e) {
      super.onProcessFailed(e);
      if (watchdog != null && watchdog.killedProcess()) {
        System.err.println("[resultHandler] The print process timed out");
      } else {
        System.err.println("[resultHandler] The print process failed to do : " + e.getMessage());
      }
    }
  }

}
