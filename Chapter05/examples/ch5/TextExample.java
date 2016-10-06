package examples.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates text fields
 */
public class TextExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout(1, false));
    
    
    // Create a single line text field
    new Text(shell, SWT.BORDER);
    
    // Create a right-aligned single line text field
    new Text(shell, SWT.RIGHT | SWT.BORDER);
    
    // Create a password text field
    new Text(shell, SWT.PASSWORD | SWT.BORDER);
    
    // Create a read-only text field
    new Text(shell, SWT.READ_ONLY | SWT.BORDER).setText("Read Only");
    
    // Create a multiple line text field
    Text t = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
    t.setLayoutData(new GridData(GridData.FILL_BOTH));
    
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
