package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class displays information about the display device.
 */
public class ShowDevice {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Display Device");
    createContents(shell);
    shell.pack();
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

    // Create a text box to hold the data
    Text text = new Text(shell, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

    // Get the display device
    Device device = shell.getDisplay();

    // Put its data into a buffer
    StringBuffer buf = new StringBuffer();
    buf.append("getBounds(): ").append(device.getBounds()).append("\n");
    buf.append("getClientArea(): ").append(device.getClientArea()).append("\n");
    buf.append("getDepth(): ").append(device.getDepth()).append("\n");
    buf.append("getDPI(): ").append(device.getDPI()).append("\n");

    // By setting warnings to true and then getting warnings, we know if the
    // current platform supports it
    device.setWarnings(true);
    buf.append("Warnings supported: ").append(device.getWarnings()).append("\n");

    // Put the collected information into the text box
    text.setText(buf.toString());
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ShowDevice().run();
  }
}
