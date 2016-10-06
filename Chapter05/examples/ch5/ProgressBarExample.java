package examples.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates ProgressBar
 */
public class ProgressBarExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout());
    
    // Create a smooth progress bar
    ProgressBar pb1 = new ProgressBar(shell, SWT.HORIZONTAL | SWT.SMOOTH);
    pb1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    pb1.setMinimum(0);
    pb1.setMaximum(30);
    
    // Create an indeterminate progress bar
    ProgressBar pb2 = new ProgressBar(shell, SWT.HORIZONTAL | SWT.INDETERMINATE);
    pb2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    
    // Start the first progress bar
    new LongRunningOperation(display, pb1).start();
    
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
}
  
/**
 * This class simulates a long running operation
 */
class LongRunningOperation extends Thread {
  private Display display;
  private ProgressBar progressBar;

  public LongRunningOperation(Display display, ProgressBar progressBar) {
    this.display = display;
    this.progressBar = progressBar;
  }
  
  public void run() {
    // Perform work here--this operation just sleeps
    for (int i = 0; i < 30; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // Do nothing
      }
      display.asyncExec(new Runnable() {
        public void run() {
          if (progressBar.isDisposed()) return;
          
          // Increment the progress bar
          progressBar.setSelection(progressBar.getSelection() + 1);
        }
      });
    }
  }
}

