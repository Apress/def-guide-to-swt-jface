package examples.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates CLabel
 */
public class CLabelShort {
  private Image lookImage;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("CLabel Short");

    // Load the image
    lookImage = new Image(display, this.getClass().getResourceAsStream(
        "/images/look.gif"));

    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }

    // Dispose the image
    if (lookImage != null) lookImage.dispose();

    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   */
  private void createContents(Composite parent) {
    parent.setLayout(new FillLayout());

    // Create the CLabel
    CLabel label = new CLabel(parent, SWT.LEFT);
    label.setText("This is a CLabel with a lot of long-winded text");
    label.setImage(lookImage);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new CLabelShort().run();
  }
}
