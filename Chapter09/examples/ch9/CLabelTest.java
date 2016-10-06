package examples.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates CLabel
 */
public class CLabelTest {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("CLabel Test");
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
   * @param parent the main window
   */
  private void createContents(Composite parent) {
    parent.setLayout(new GridLayout(1, false));

    // Create the CLabels
    CLabel left = new CLabel(parent, SWT.LEFT | SWT.SHADOW_IN);
    left.setText("Left and Shadow In");
    left.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    CLabel center = new CLabel(parent, SWT.CENTER | SWT.SHADOW_OUT);
    center.setText("Center and Shadow Out");
    center.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    CLabel right = new CLabel(parent, SWT.RIGHT | SWT.SHADOW_NONE);
    right.setText("Right and Shadow None");
    right.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new CLabelTest().run();
  }
}
