package examples.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates SashForm
 */
public class SashFormTest {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("SashForm Test");
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
   * @param parent the parent window
   */
  private void createContents(Composite parent) {
    // Fill the parent window with the buttons and sash
    parent.setLayout(new FillLayout());

    // Create the SashForm and the buttons
    SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
    new Button(sashForm, SWT.PUSH).setText("Left");
    new Button(sashForm, SWT.PUSH).setText("Right");
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SashFormTest().run();
  }
}
