package examples.ch15;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * This class represents a long running operation
 */
public class LongRunningOperation implements IRunnableWithProgress {
  // The total sleep time
  private static final int TOTAL_TIME = 10000;

  // The increment sleep time
  private static final int INCREMENT = 500;

  private boolean indeterminate;

  /**
   * LongRunningOperation constructor
   * 
   * @param indeterminate whether the animation is unknown
   */
  public LongRunningOperation(boolean indeterminate) {
    this.indeterminate = indeterminate;
  }

  /**
   * Runs the long running operation
   * 
   * @param monitor the progress monitor
   */
  public void run(IProgressMonitor monitor) throws InvocationTargetException,
      InterruptedException {
    monitor.beginTask("Running long running operation",
        indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
      Thread.sleep(INCREMENT);
      monitor.worked(INCREMENT);
      if (total == TOTAL_TIME / 2) monitor.subTask("Doing second half");
    }
    monitor.done();
    if (monitor.isCanceled())
        throw new InterruptedException("The long running operation was cancelled");
  }
}