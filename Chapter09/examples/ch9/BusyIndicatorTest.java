package examples.ch9;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This program demonstrates BusyIndicator
 */
public class BusyIndicatorTest {
  // The amount of time to sleep (in ms)
  private static final int SLEEP_TIME = 3000;

  // Labels for the button
  private static final String RUN = "Press to Run";
  private static final String IS_RUNNING = "Running...";

  /**
   * Runs the application
   */
  private void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("BusyIndicator Test");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Create the window's contents
   * 
   * @param shell the parent shell
   */
  private void createContents(Shell shell) {
    shell.setLayout(new FillLayout());
    final Button button = new Button(shell, SWT.PUSH);
    button.setText(RUN);
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Change the button's text
        button.setText(IS_RUNNING);

        // Show the busy indicator
        BusyIndicator.showWhile(button.getDisplay(), new SleepThread(SLEEP_TIME));

        // Thread has completed; reset the button's text
        button.setText(RUN);
      }
    });
  }

  /**
   * Application's entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new BusyIndicatorTest().run();
  }
}

/**
 * This class is a thread that sleeps the specified number of milliseconds
 */

class SleepThread extends Thread {
  private long ms;

  /**
   * SleepThread constructor
   * 
   * @param ms the number of milliseconds to sleep
   */
  public SleepThread(long ms) {
    this.ms = ms;
  }

  /**
   * Runs the thread
   */
  public void run() {
    try {
      sleep(ms);
    } catch (InterruptedException e) {}
  }
}
