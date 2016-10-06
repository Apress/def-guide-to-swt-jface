package examples.ch8;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates a Sash
 */
public class SashExampleTwo {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Sash Two");
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
    composite.setLayout(new FormLayout());

    // Create the sash first, so the other controls
    // can be attached to it.
    Sash sash = new Sash(composite, SWT.VERTICAL);
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0); // Attach to top
    data.bottom = new FormAttachment(100, 0); // Attach to bottom
    data.left = new FormAttachment(50, 0); // Attach halfway across
    sash.setLayoutData(data);

    // Create the first text box and attach its right edge
    // to the sash
    Text one = new Text(composite, SWT.BORDER);
    data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(sash, 0);
    one.setLayoutData(data);

    // Create the second text box and attach its left edge
    // to the sash
    Text two = new Text(composite, SWT.BORDER);
    data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(sash, 0);
    data.right = new FormAttachment(100, 0);
    two.setLayoutData(data);
  }

  /**
   * Application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SashExampleTwo().run();
  }
}
