package com.gmail.hidekishima.helloworld.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * SWT sample code obtained from an article: 
 * http://www.atmarkit.co.jp/fjava/rensai2/eclipse2_07/eclipse07_1.html
 */
public class TextViewer {

  private Shell shell;
  private Display display;

  public Shell open(Display display) {
    this.display = display;
    shell = new Shell(display);
    shell.setText("Viewer");

    shell.open();
    return shell;
  }

  // In order to actually run this code, 
  // you need to include the swt dll to java.library.path
  // Also see files in C:\Program Files\eclipse\plugins\org.eclipse.swt.*
  public static void main(String[] args) {
    Display display = new Display();

    TextViewer viewer = new TextViewer();
    Shell shell = viewer.open(display);

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}