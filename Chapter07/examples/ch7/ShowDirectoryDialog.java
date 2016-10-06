package examples.ch7;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates the DirectoryDialog class
 */
public class ShowDirectoryDialog {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Directory Browser");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Creates the window contents
   * 
   * @param shell the parent shell
   */
  private void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(6, true));
    new Label(shell, SWT.NONE).setText("Directory:");

    // Create the text box extra wide to show long paths
    final Text text = new Text(shell, SWT.BORDER);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 4;
    text.setLayoutData(data);

    // Clicking the button will allow the user
    // to select a directory
    Button button = new Button(shell, SWT.PUSH);
    button.setText("Browse...");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        DirectoryDialog dlg = new DirectoryDialog(shell);

        // Set the initial filter path according
        // to anything they've selected or typed in
        dlg.setFilterPath(text.getText());

        // Change the title bar text
        dlg.setText("SWT's DirectoryDialog");

        // Customizable message displayed in the dialog
        dlg.setMessage("Select a directory");

        // Calling open() will open and run the dialog.
        // It will return the selected directory, or
        // null if user cancels
        String dir = dlg.open();
        if (dir != null) {
          // Set the text box to the new selection
          text.setText(dir);
        }
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ShowDirectoryDialog().run();
  }
}
