package examples.ch15;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates JFace's MessageDialog class
 */
public class SendMessage extends ApplicationWindow {
  /**
   * SendMessage constructor
   */
  public SendMessage() {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run() {
    // Don't return from open() until window closes
    setBlockOnOpen(true);

    // Open the main window
    open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  /**
   * Configures the shell
   * 
   * @param shell the shell
   */
  protected void configureShell(Shell shell) {
    super.configureShell(shell);

    // Set the title bar text and the size
    shell.setText("Send Message");
    shell.setSize(500, 400);
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(5, true));

    // Create a big text box for the message text
    final Text text = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
    GridData data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 5;
    text.setLayoutData(data);

    // Create the Confirm button
    Button confirm = new Button(composite, SWT.PUSH);
    confirm.setText("Confirm");
    confirm.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the Error button
    Button error = new Button(composite, SWT.PUSH);
    error.setText("Error");
    error.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the Information button
    Button information = new Button(composite, SWT.PUSH);
    information.setText("Information");
    information.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the Question button
    Button question = new Button(composite, SWT.PUSH);
    question.setText("Question");
    question.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the Warning button
    Button warning = new Button(composite, SWT.PUSH);
    warning.setText("Warning");
    warning.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the label to display the return value
    final Label label = new Label(composite, SWT.NONE);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 5;
    label.setLayoutData(data);

    // Save ourselves some typing
    final Shell shell = parent.getShell();

    // Display a Confirmation dialog
    confirm.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        boolean b = MessageDialog.openConfirm(shell, "Confirm", text.getText());
        label.setText("Returned " + Boolean.toString(b));
      }
    });

    // Display an Error dialog
    error.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        MessageDialog.openError(shell, "Error", text.getText());
        label.setText("Returned void");
      }
    });

    // Display an Information dialog
    information.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        MessageDialog.openInformation(shell, "Information", text.getText());
        label.setText("Returned void");
      }
    });

    // Display a Question dialog
    question.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        boolean b = MessageDialog.openQuestion(shell, "Question", text.getText());
        label.setText("Returned " + Boolean.toString(b));
      }
    });

    // Display a Warning dialog
    warning.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        MessageDialog.openWarning(shell, "Warning", text.getText());
        label.setText("Returned void");
      }
    });

    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SendMessage().run();
  }
}