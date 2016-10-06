package examples.ch12;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class implements a web browser
 */
public class SimpleBrowser {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Simple Browser");
    createContents(shell);
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
  private void createContents(Shell shell) {
    shell.setLayout(new FormLayout());

    // Create the composite to hold the buttons and text field
    Composite controls = new Composite(shell, SWT.NONE);
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    controls.setLayoutData(data);

    // Create the web browser
    final Browser browser = new Browser(shell, SWT.NONE);
    data = new FormData();
    data.top = new FormAttachment(controls);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    browser.setLayoutData(data);

    // Create the controls and wire them to the browser
    controls.setLayout(new GridLayout(6, false));

    // Create the back button
    Button button = new Button(controls, SWT.PUSH);
    button.setText("Back");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.back();
      }
    });

    // Create the forward button
    button = new Button(controls, SWT.PUSH);
    button.setText("Forward");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.forward();
      }
    });

    // Create the refresh button
    button = new Button(controls, SWT.PUSH);
    button.setText("Refresh");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.refresh();
      }
    });

    // Create the stop button
    button = new Button(controls, SWT.PUSH);
    button.setText("Stop");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.stop();
      }
    });

    // Create the address entry field and set focus to it
    final Text url = new Text(controls, SWT.BORDER);
    url.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    url.setFocus();

    // Create the go button
    button = new Button(controls, SWT.PUSH);
    button.setText("Go");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.setUrl(url.getText());
      }
    });

    // Allow users to hit enter to go to the typed URL
    shell.setDefaultButton(button);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SimpleBrowser().run();
  }
}
