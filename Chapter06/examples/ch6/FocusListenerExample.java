package examples.ch6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates FocusListener
 */
public class FocusListenerExample {

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // Create the shell
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout(3, true));
    shell.setText("One Potato, Two Potato");

    // Create the focus listener
    FocusListener listener = new FocusListener() {
      public void focusGained(FocusEvent event) {
        Button button = (Button) event.getSource();
        button.setText("I'm It!");
      }

      public void focusLost(FocusEvent event) {
        Button button = (Button) event.getSource();
        button.setText("Pick Me!");
      }
    };

    // Create the buttons and add the listener to each one
    for (int i = 0; i < 6; i++) {
      Button button = new Button(shell, SWT.PUSH);
      button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      button.setText("Pick Me!");
      button.addFocusListener(listener);
    }

    // Display the window
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
