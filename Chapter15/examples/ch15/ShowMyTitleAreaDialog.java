package examples.ch15;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates JFace's TitleAreaDialog class
 */
public class ShowMyTitleAreaDialog extends ApplicationWindow {
  /**
   * ShowCustomDialog constructor
   */
  public ShowMyTitleAreaDialog() {
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
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, true));

    // Create the button
    Button show = new Button(composite, SWT.NONE);
    show.setText("Show");

    final Shell shell = parent.getShell();

    // Display the TitleAreaDialog
    show.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Create and show the dialog
        MyTitleAreaDialog dlg = new MyTitleAreaDialog(shell);
        dlg.open();
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
    new ShowMyTitleAreaDialog().run();
  }
}