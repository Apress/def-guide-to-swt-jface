package examples.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates PopupList
 */
public class PopupListTest {
  // These are the options that display in the list
  private static final String[] OPTIONS = { "Apple", "Banana", "Cherry",
      "Doughnut", "Eggplant", "Filbert", "Greens", "Hummus", "Ice Cream", "Jam"};

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("PopupList Test");
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
  private void createContents(final Shell shell) {
    shell.setLayout(new RowLayout());

    // Create a button to launch the list
    Button button = new Button(shell, SWT.PUSH);
    button.setText("Push Me");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Create a list
        PopupList list = new PopupList(shell);

        // Add the items to the list
        list.setItems(OPTIONS);

        // Open the list and get the selected item
        String selected = list.open(shell.getBounds());

        // Print the item to the console
        System.out.println(selected);
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PopupListTest().run();
  }
}
