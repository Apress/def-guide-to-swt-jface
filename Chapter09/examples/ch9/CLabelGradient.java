package examples.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates CLabel gradients
 */
public class CLabelGradient {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("CLabel Gradient");
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
    CLabel one = new CLabel(parent, SWT.LEFT);
    one.setText("First Gradient Example");
    one.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    one.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));

    // Set the background gradient
    one.setBackground(new Color[] {
        parent.getDisplay().getSystemColor(SWT.COLOR_RED),
        parent.getDisplay().getSystemColor(SWT.COLOR_GREEN),
        parent.getDisplay().getSystemColor(SWT.COLOR_BLUE)}, new int[] { 25, 50});

    CLabel two = new CLabel(parent, SWT.LEFT);
    two.setText("Second Gradient Example");
    two.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Set the background gradient
    two.setBackground(new Color[] {
        parent.getDisplay().getSystemColor(SWT.COLOR_WHITE),
        parent.getDisplay().getSystemColor(SWT.COLOR_GRAY),
        parent.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY),
        parent.getDisplay().getSystemColor(SWT.COLOR_BLACK)}, new int[] { 33, 67,
        100});
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new CLabelGradient().run();
  }
}
