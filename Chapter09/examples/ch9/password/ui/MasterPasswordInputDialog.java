package examples.ch9.password.ui;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class prompts for the entry of the master password for a file
 */
public class MasterPasswordInputDialog extends Dialog {
  private String message;
  private String input;

  /**
   * InputDialog constructor
   * 
   * @param parent the parent
   */
  public MasterPasswordInputDialog(Shell parent) {
    // Pass the default styles here
    this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
  }

  /**
   * InputDialog constructor
   * 
   * @param parent the parent
   * @param style the style
   */
  public MasterPasswordInputDialog(Shell parent, int style) {
    // Let users override the default styles
    super(parent, style);
    setText("Password");
    setMessage("Please enter your password:");
  }

  /**
   * Gets the message
   * 
   * @return String
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message
   * 
   * @param message the new message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the input
   * 
   * @return String
   */
  public String getInput() {
    return input;
  }

  /**
   * Sets the input
   * 
   * @param input the new input
   */
  public void setInput(String input) {
    this.input = input;
  }

  /**
   * Opens the dialog and returns the input
   * 
   * @return String
   */
  public String open() {
    // Create the dialog window
    Shell shell = new Shell(getParent(), getStyle());
    shell.setText(getText());
    createContents(shell);
    shell.pack();
    shell.open();
    Display display = getParent().getDisplay();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    // Return the entered value, or null
    return input;
  }

  /**
   * Creates the dialog's contents
   * 
   * @param shell the dialog window
   */
  private void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(2, true));

    // Show the message
    Label label = new Label(shell, SWT.NONE);
    label.setText(message);
    GridData data = new GridData();
    data.horizontalSpan = 2;
    label.setLayoutData(data);

    // Display the input box
    final Text text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 2;
    text.setLayoutData(data);

    // Create the OK button and add a handler
    // so that pressing it will set input
    // to the entered value
    Button ok = new Button(shell, SWT.PUSH);
    ok.setText("OK");
    data = new GridData(GridData.FILL_HORIZONTAL);
    ok.setLayoutData(data);
    ok.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        input = text.getText();
        shell.close();
      }
    });

    // Create the cancel button and add a handler
    // so that pressing it will set input to null
    Button cancel = new Button(shell, SWT.PUSH);
    cancel.setText("Cancel");
    data = new GridData(GridData.FILL_HORIZONTAL);
    cancel.setLayoutData(data);
    cancel.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        input = null;
        shell.close();
      }
    });

    // Set the OK button as the default, so
    // user can type input and press Enter
    // to dismiss
    shell.setDefaultButton(ok);
  }
}
