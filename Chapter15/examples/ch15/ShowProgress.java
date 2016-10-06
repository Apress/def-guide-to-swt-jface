package examples.ch15;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates JFace's ProgressMonitorDialog class
 */
public class ShowProgress extends ApplicationWindow {
  /**
   * ShowProgress constructor
   */
  public ShowProgress() {
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

    // Set the title bar text
    shell.setText("Show Progress");
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, true));

    // Create the indeterminate checkbox
    final Button indeterminate = new Button(composite, SWT.CHECK);
    indeterminate.setText("Indeterminate");

    // Create the ShowProgress button
    Button showProgress = new Button(composite, SWT.NONE);
    showProgress.setText("Show Progress");

    final Shell shell = parent.getShell();

    // Display the ProgressMonitorDialog
    showProgress.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        try {
          new ProgressMonitorDialog(shell).run(true, true,
              new LongRunningOperation(indeterminate.getSelection()));
        } catch (InvocationTargetException e) {
          MessageDialog.openError(shell, "Error", e.getMessage());
        } catch (InterruptedException e) {
          MessageDialog.openInformation(shell, "Cancelled", e.getMessage());
        }
      }
    });

    parent.pack();
    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ShowProgress().run();
  }
}