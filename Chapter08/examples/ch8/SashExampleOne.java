package examples.ch8;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates a Sash
 */
public class SashExampleOne {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Sash One");
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
   * Creates the contents of the main window
   * 
   * @param composite the parent composite
   */
  public void createContents(Composite composite) {
    composite.setLayout(new FillLayout());
    new Text(composite, SWT.BORDER);
    new Sash(composite, SWT.VERTICAL);
    new Text(composite, SWT.BORDER);
  }

  /**
   * Application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SashExampleOne().run();
  }
}
