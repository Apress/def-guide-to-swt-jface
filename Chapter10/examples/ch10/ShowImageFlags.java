package examples.ch10;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates the effects of the flags on the constructor:
 * 
 * <code>Image(Device device, Image srcImage, int flag)</code>
 */
public class ShowImageFlags {
  // Members to hold the images
  private Image image;
  private Image copy;
  private Image disable;
  private Image gray;

  /**
   * Runs the program
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Show Image Flags");

    // Load the image
    image = new Image(display, this.getClass().getResourceAsStream(
        "/images/swt.png"));

    // Create the duplicate image
    copy = new Image(display, image, SWT.IMAGE_COPY);

    // Create the disabled image
    disable = new Image(display, image, SWT.IMAGE_DISABLE);

    // Create the gray image
    gray = new Image(display, image, SWT.IMAGE_GRAY);

    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }

    // Dispose the images
    image.dispose();
    copy.dispose();
    disable.dispose();
    gray.dispose();

    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    shell.setLayout(new FillLayout());

    // Create labels to hold each image
    new Label(shell, SWT.NONE).setImage(image);
    new Label(shell, SWT.NONE).setImage(copy);
    new Label(shell, SWT.NONE).setImage(disable);
    new Label(shell, SWT.NONE).setImage(gray);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ShowImageFlags().run();
  }
}
