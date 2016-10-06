package examples.ch10;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

/**
 * This class demonstrates drawing lines
 */
public class LineExample {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Line Example");
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

    // Create a canvas to draw on
    Canvas canvas = new Canvas(shell, SWT.NONE);

    // Add a handler to do the drawing
    canvas.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
        // Get the canvas and its size
        Canvas canvas = (Canvas) e.widget;
        int maxX = canvas.getSize().x;
        int maxY = canvas.getSize().y;

        // Calculate the middle
        int halfX = (int) maxX / 2;
        int halfY = (int) maxY / 2;

        // Set the drawing color to blue
        e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLUE));

        // Set the width of the lines to draw
        e.gc.setLineWidth(10);

        // Draw a vertical line halfway across the canvas
        e.gc.drawLine(halfX, 0, halfX, maxY);

        // Draw a horizontal line halfway down the canvas
        e.gc.drawLine(0, halfY, maxX, halfY);
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new LineExample().run();
  }
}
