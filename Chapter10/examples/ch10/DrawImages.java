package examples.ch10;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates how to draw images
 */
public class DrawImages {
  public void run() {
    Display display = new Display();
    final Shell shell = new Shell(display);

    // Load an image
    final Image image = new Image(display, this.getClass().getResourceAsStream(
        "/images/swt.png"));
    System.out.println(image.getImageData().scanlinePad);
    image.getImageData().scanlinePad = 40;
    System.out.println(image.getImageData().scanlinePad);

    shell.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Draw the untainted image
        event.gc.drawImage(image, 0, 0);

        // Determine how big the drawing area is
        Rectangle rect = shell.getClientArea();

        // Get information about the image
        ImageData data = image.getImageData();

        // Calculate drawing values
        int srcX = data.width / 4;
        int srcY = data.height / 4;
        int srcWidth = data.width / 2;
        int srcHeight = data.height / 2;
        int destWidth = 2 * srcWidth;
        int destHeight = 2 * srcHeight;

        // Draw the image
        event.gc.drawImage(image, srcX, srcY, srcWidth, srcHeight, rect.width
            - destWidth, rect.height - destHeight, destWidth, destHeight);
      }
    });
    shell.setText("Draw Images");
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    image.dispose();
    display.dispose();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new DrawImages().run();
  }
}
