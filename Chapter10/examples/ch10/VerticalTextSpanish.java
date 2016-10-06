package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates how to draw vertical text
 */
public class VerticalTextSpanish {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    final Shell shell = new Shell(display);
    final Font font = new Font(display, "Arial", 36, SWT.ITALIC);

    // Create "Hello" image
    final Image hello = GraphicsUtils.createRotatedText("Hola", font, shell
        .getForeground(), shell.getBackground(), SWT.UP);

    // Create "Good Bye" image
    final Image goodBye = GraphicsUtils.createRotatedText("Chao Pescado", font,
        shell.getForeground(), shell.getBackground(), SWT.DOWN);

    shell.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Set the font
        event.gc.setFont(font);

        // Draw hello in the upper left
        event.gc.drawImage(hello, 0, 0);

        // Draw good bye in the lower right
        // Note how we calculate the origin
        Rectangle rcImage = goodBye.getBounds();
        Rectangle rect = shell.getClientArea();
        event.gc.drawImage(goodBye, rect.width - rcImage.width, rect.height
            - rcImage.height);
      }
    });
    shell.setText("Vertical Text Spanish");
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    goodBye.dispose();
    hello.dispose();
    font.dispose();
    display.dispose();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new VerticalTextSpanish().run();
  }
}
