package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates drawing polygons
 */
public class PolygonExample {
  private Text txtWidth = null;
  private Text txtHeight = null;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Polygon Example");
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
    shell.setLayout(new FillLayout(SWT.VERTICAL));

    // Create the canvas to draw the polygons on
    Canvas drawingCanvas = new Canvas(shell, SWT.NONE);
    drawingCanvas.addPaintListener(new PolygonExamplePaintListener());
  }

  /**
   * This class gets the user input and draws the requested oval
   */
  private class PolygonExamplePaintListener implements PaintListener {
    public void paintControl(PaintEvent e) {
      // Get the canvas for drawing and its dimensions
      Canvas canvas = (Canvas) e.widget;
      int x = canvas.getBounds().width;
      int y = canvas.getBounds().height;

      // Set the drawing color
      e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));

      // Create the points for drawing a triangle in the upper left
      int[] upper_left = { 0, 0, 200, 0, 0, 200};

      // Create the points for drawing a triangle in the lower right
      int[] lower_right = { x, y, x, y - 200, x - 200, y};

      // Draw the triangles
      e.gc.fillPolygon(upper_left);
      e.gc.fillPolygon(lower_right);
    }
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PolygonExample().run();
  }
}