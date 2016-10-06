package examples.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates Buttons
 */
public class ButtonExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout(3, true));
    
    // Create three push buttons
    new Button(shell, SWT.PUSH).setText("Push 1");
    new Button(shell, SWT.PUSH).setText("Push 2");
    new Button(shell, SWT.PUSH).setText("Push 3");
    
    // Create three checkboxes
    new Button(shell, SWT.CHECK).setText("Checkbox 1");
    new Button(shell, SWT.CHECK).setText("Checkbox 2");
    new Button(shell, SWT.CHECK).setText("Checkbox 3");
    
    // Create three toggle buttons
    new Button(shell, SWT.TOGGLE).setText("Toggle 1");
    new Button(shell, SWT.TOGGLE).setText("Toggle 2");
    new Button(shell, SWT.TOGGLE).setText("Toggle 3");
    
    // Create three radio buttons
    new Button(shell, SWT.RADIO).setText("Radio 1");
    new Button(shell, SWT.RADIO).setText("Radio 2");
    new Button(shell, SWT.RADIO).setText("Radio 3");
    
    // Create three flat buttons
    new Button(shell, SWT.FLAT).setText("Flat 1");
    new Button(shell, SWT.FLAT).setText("Flat 2");
    new Button(shell, SWT.FLAT).setText("Flat 3");
    
    // Create three arrow buttons
    new Button(shell, SWT.ARROW);
    new Button(shell, SWT.ARROW | SWT.LEFT);
    new Button(shell, SWT.ARROW | SWT.DOWN);

    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
