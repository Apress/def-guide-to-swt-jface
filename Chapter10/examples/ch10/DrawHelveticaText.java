package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates how to draw text
 */
public class DrawHelveticaText {
  public void run() {
    Display display = new Display();
    final Shell shell = new Shell(display);

    // Create the font
    final Font font = new Font(display, "Helvetica", 18, SWT.NORMAL);

    shell.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Set the font
        event.gc.setFont(font);

        // Draw the text
        event.gc.drawText("My Text", 0, 0);
      }
    });
    shell.setText("Draw Helvetica Text");
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    font.dispose();
    display.dispose();
  }

  public static void main(String[] args) {
    new DrawHelveticaText().run();
  }
}
