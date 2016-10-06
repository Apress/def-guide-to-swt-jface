package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates how to draw vertical text
 */
public class VerticalText {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    final Shell shell = new Shell(display);
    final Font font = new Font(display, "Arial", 36, SWT.ITALIC);

    shell.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Set the font
        event.gc.setFont(font);

        // Draw some text up in the upper left
        GraphicsUtils.drawVerticalText("Hello", 0, 0, event.gc, SWT.UP);

        // Draw some text down in the lower right
        // Note how we calculate the origin
        String goodBye = "Good Bye";
        Point pt = event.gc.textExtent(goodBye);
        Rectangle rect = shell.getClientArea();
        GraphicsUtils.drawVerticalText(goodBye, rect.width - pt.y, rect.height
            - pt.x, event.gc, SWT.DOWN);
      }
    });
    shell.setText("Vertical Text");
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    font.dispose();
    display.dispose();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new VerticalText().run();
  }
}
