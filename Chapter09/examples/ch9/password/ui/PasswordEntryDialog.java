package examples.ch9.password.ui;

import java.util.Iterator;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import examples.ch9.password.Password;
import examples.ch9.password.data.*;

/**
 * This class is the dialog to input a new password
 */
public class PasswordEntryDialog extends Dialog {
  private PasswordEntry entry;

  /**
   * Constructs a PasswordEntryDialog
   * 
   * @param shell the parent shell
   */
  public PasswordEntryDialog(Shell shell) {
    super(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
  }

  /**
   * Opens the dialog
   * 
   * @return PasswordEntry
   */
  public PasswordEntry open() {
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
    return entry;
  }

  /**
   * Creates the window's contents
   * 
   * @param shell the parent shell
   */
  private void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(2, true));

    // Create the category dropdown and fill it
    new Label(shell, SWT.NONE).setText("Category:");
    final Combo category = new Combo(shell, SWT.NONE);
    category.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    for (Iterator itr = Password.getApp().getMainWindow().getCurrentFile()
        .getCategories().iterator(); itr.hasNext();) {
      category.add((String) itr.next());
    }

    // Create the Name input
    new Label(shell, SWT.NONE).setText("Name:");
    final Text name = new Text(shell, SWT.BORDER);
    name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the User ID input
    new Label(shell, SWT.NONE).setText("User ID:");
    final Text userId = new Text(shell, SWT.BORDER);
    userId.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the Password input
    new Label(shell, SWT.NONE).setText("Password:");
    final Text password = new Text(shell, SWT.BORDER);
    password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the OK button
    final Button ok = new Button(shell, SWT.PUSH);
    ok.setText("OK");
    ok.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
    ok.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        entry = new PasswordEntry(category.getText(), name.getText(), userId
            .getText(), password.getText());
        shell.close();
      }
    });

    // Create the Cancel button
    final Button cancel = new Button(shell, SWT.PUSH);
    cancel.setText("Cancel");
    cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
    cancel.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        entry = null;
        shell.close();
      }
    });

    // Fill inputs with entry data (if we're editing an existing entry)
    if (entry != null) {
      category.setText(entry.getCategory() == null ? "" : entry.getCategory());
      name.setText(entry.getName() == null ? "" : entry.getName());
      userId.setText(entry.getUserId() == null ? "" : entry.getUserId());
      password.setText(entry.getPassword() == null ? "" : entry.getPassword());
    }

    // Allow user to press Enter to accept and close
    shell.setDefaultButton(ok);
  }

  /**
   * Gets the entry
   * 
   * @return PasswordEntry
   */
  public PasswordEntry getEntry() {
    return entry;
  }

  /**
   * Sets the entry
   * 
   * @param entry The entry to set.
   */
  public void setEntry(PasswordEntry entry) {
    this.entry = entry;
  }
}
