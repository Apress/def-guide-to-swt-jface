package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates how to draw text in colors
 */
public class ColorFont {
  // The color indices to use for the text
  private static final int[] COLOR_INDICES = { SWT.COLOR_BLUE, SWT.COLOR_GREEN,
      SWT.COLOR_RED, SWT.COLOR_GRAY};

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    final Shell shell = new Shell(display);

    // Handler to do the drawing
    shell.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Loop through the colors, moving down the screen each iteration
        for (int i = 0, n = COLOR_INDICES.length, y = 0, height = event.gc
            .getFontMetrics().getHeight(); i < n; i++, y += height) {
          event.gc.setForeground(shell.getDisplay().getSystemColor(
              COLOR_INDICES[i]));
          event.gc.drawText("Hooray for Color!", 0, y);
        }
      }
    });
    shell.setText("Color Font");
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ColorFont().run();
  }
}
