package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates drawing points. It draws a sine wave.
 */
public class PointExample {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Point Example");
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    shell.setLayout(new FillLayout());

    // Create the canvas for drawing on
    Canvas canvas = new Canvas(shell, SWT.NONE);

    // Add the paint handler to draw the sine wave
    canvas.addPaintListener(new PointExamplePaintListener());

    // Use a white background
    canvas.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
  }

  /**
   * This class draws a sine wave using points
   */
  private class PointExamplePaintListener implements PaintListener {
    public void paintControl(PaintEvent e) {
      // Get the canvas and its dimensions
      Canvas canvas = (Canvas) e.widget;
      int maxX = canvas.getSize().x;
      int maxY = canvas.getSize().y;

      // Calculate the middle
      int halfX = (int) maxX / 2;
      int halfY = (int) maxY / 2;

      // Set the line color and draw a horizontal axis
      e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
      e.gc.drawLine(0, halfY, maxX, halfY);

      // Draw the sine wave
      for (int i = 0; i < maxX; i++) {
        e.gc.drawPoint(i, getNormalizedSine(i, halfY, maxX));
      }
    }

    /**
     * Calculates the sine value
     * 
     * @param x the value along the x-axis
     * @param halfY the value of the y-axis
     * @param maxX the width of the x-axis
     * @return int
     */
    int getNormalizedSine(int x, int halfY, int maxX) {
      double piDouble = 2 * Math.PI;
      double factor = piDouble / maxX;
      return (int) (Math.sin(x * factor) * halfY + halfY);
    }
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PointExample().run();
  }
}
