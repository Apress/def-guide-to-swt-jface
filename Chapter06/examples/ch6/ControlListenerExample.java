package examples.ch6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates ControlListeners
 */
public class ControlListenerExample {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    Image image = new Image(display, "happyGuy.gif");
    createContents(shell, image);
    shell.pack();
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    if (image != null) image.dispose();
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   * @param image the image
   */
  private void createContents(Shell shell, Image image) {
    shell.setLayout(new GridLayout());

    // Create a label to hold the image
    Label label = new Label(shell, SWT.NONE);
    label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
    label.setImage(image);
    shell.setData(label);

    // Add the listener
    shell.addControlListener(new ControlAdapter() {
      public void controlResized(ControlEvent event) {
        // Get the event source (the shell)
        Shell shell = (Shell) event.getSource();

        // Get the source's data (the label)
        Label label = (Label) shell.getData();

        // Determine how big the shell should be to fit the image
        Rectangle rect = shell.getClientArea();
        ImageData data = label.getImage().getImageData();

        // If the shell is too small, hide the image
        if (rect.width < data.width || rect.height < data.height) {
          shell.setText("Too small.");
          label.setText("I'm melting!");
        } else {
          // He fits!
          shell.setText("Happy Guy Fits!");
          label.setImage(label.getImage());
        }
      }
    });
  }

  /**
   * Application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ControlListenerExample().run();
  }
}
