package examples.ch9.password;

import java.io.IOException;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

import examples.ch9.password.data.*;
import examples.ch9.password.ui.*;

/**
 * This application safely stores passwords
 */
public class Password {
  private static final Password app = new Password();

  /**
   * Gets a pointer to the running application
   * 
   * @return Password
   */
  public static Password getApp() {
    return app;
  }

  // Retain a reference to the main window of the application
  private PasswordMainWindow mainWindow;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    mainWindow = new PasswordMainWindow(shell);
    newFile();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Gets the main window
   * 
   * @return PasswordMainWindow
   */
  public PasswordMainWindow getMainWindow() {
    return mainWindow;
  }

  /**
   * New file
   */
  public void newFile() {
    PasswordFile passwordFile = new PasswordFile();
    mainWindow.add(passwordFile);
  }

  /**
   * Open file
   */
  public void openFile() {
    // Use FileDialog to get the desired file
    FileDialog dlg = new FileDialog(mainWindow.getShell());
    dlg.setFilterNames(PasswordConst.FILTER_NAMES);
    dlg.setFilterExtensions(PasswordConst.FILTER_EXTS);
    String fileName = dlg.open();
    if (fileName != null) {
      // Create a PasswordFile object and set the selected filename
      PasswordFile passwordFile = new PasswordFile();
      passwordFile.setFilename(fileName);
      try {
        // Open the file, add it to the app, and refresh the filenames
        passwordFile.open();
        mainWindow.add(passwordFile);
        mainWindow.refreshTabs();
      } catch (IllegalStateException e) {
        showError(e.getMessage());
      } catch (IOException e) {
        showError("Cannot open file " + passwordFile.getFilename());
      }
    }
  }

  /**
   * Save file
   * 
   * @return boolean
   */
  public boolean saveFile() {
    boolean saved = false;
    PasswordFile passwordFile = mainWindow.getCurrentFile();
    if (passwordFile != null) {
      // If the filename hasn't been specified, prompt for a name
      if (passwordFile.getFilename() == null) {
        saveFileAs();
      } else {
        try {
          // Save the file and refresh the filenames
          passwordFile.save();
          mainWindow.refreshTabs();
          saved = true;
        } catch (IllegalStateException e) {
          showError(e.getMessage());
        } catch (IOException e) {
          showError("Cannot save file " + passwordFile.getFilename());
        }
      }
    }
    return saved;
  }

  /**
   * Save file as
   */
  public void saveFileAs() {
    PasswordFile passwordFile = mainWindow.getCurrentFile();
    if (passwordFile != null) {
      // Use the SafeSaveDialog so we don't inadvertently overwrite
      // an existing file
      SafeSaveDialog dlg = new SafeSaveDialog(mainWindow.getShell());
      if (passwordFile.getFilename() != null) {
        dlg.setFileName(passwordFile.getFilename());
      }
      dlg.setFilterNames(PasswordConst.FILTER_NAMES);
      dlg.setFilterExtensions(PasswordConst.FILTER_EXTS);
      String filename = dlg.open();
      if (filename != null) {
        // Save the file and refresh the filenames
        passwordFile.setFilename(filename);
        saveFile();
        mainWindow.refreshTabs();
      }
    }
  }

  /**
   * Creates a new password entry
   */
  public void newPassword() {
    // Make sure that a file is open
    PasswordFile passwordFile = mainWindow.getCurrentFile();
    if (passwordFile != null) {
      // Pop up the password entry dialog
      PasswordEntryDialog dlg = new PasswordEntryDialog(mainWindow.getShell());
      PasswordEntry entry = dlg.open();
      if (entry != null) {
        // Add the new password entry and refresh the filenames
        passwordFile.add(entry);
        mainWindow.getCurrentFile().setDirty(true);
        mainWindow.getCurrentTab().refresh();
        mainWindow.refreshTabs();
      }
    }
  }

  /**
   * Edits an existing password entry
   */
  public void editPassword() {
    // Determine the entry to edit
    PasswordFileTab tab = mainWindow.getCurrentTab();
    if (tab != null) {
      editPassword(tab.getSelection());
    }
  }

  /**
   * Edits an existing password entry
   * 
   * @param entry the entry to edit
   */
  public void editPassword(PasswordEntry entry) {
    if (entry != null) {
      // Pop up the password entry dialog and fill it with the entry to be edited
      PasswordEntryDialog dlg = new PasswordEntryDialog(mainWindow.getShell());
      dlg.setEntry(entry);
      PasswordEntry tempEntry = dlg.open();
      if (tempEntry != null) {
        // See if category changed
        if (!entry.getCategory().equals(tempEntry.getCategory())) {
          // Category changed, so remove entry from the old category
          // and add to the new one
          mainWindow.getCurrentFile().getEntries(entry.getCategory()).remove(
              entry);
          mainWindow.getCurrentFile().getEntries(tempEntry.getCategory()).add(
              entry);
        }

        // Copy the data from the temp entry into the real entry
        entry.clone(tempEntry);
        mainWindow.getCurrentFile().setDirty(true);
        mainWindow.getCurrentTab().refresh();
        mainWindow.refreshTabs();
      }
    }
  }

  /**
   * Deletes an existing password
   */
  public void deletePassword() {
    PasswordFileTab tab = mainWindow.getCurrentTab();
    if (tab != null) {
      PasswordEntry entry = tab.getSelection();
      if (entry != null) {
        // Ask for confirmation before deleting
        MessageBox mb = new MessageBox(mainWindow.getShell(), SWT.ICON_QUESTION
            | SWT.YES | SWT.NO);
        mb.setMessage("Are you sure?");
        if (mb.open() == SWT.YES) {
          mainWindow.getCurrentFile().remove(entry);
          tab.refresh();
          mainWindow.refreshTabs();
        }
      }
    }
  }

  /**
   * Shows an about box
   */
  public void about() {
    MessageBox mb = new MessageBox(mainWindow.getShell(), SWT.ICON_INFORMATION
        | SWT.OK);
    mb.setMessage("Password\nWritten in SWT");
    mb.open();
  }

  /**
   * Gets the master password from user input
   * 
   * @return String
   */
  public String getMasterPassword() {
    MasterPasswordInputDialog dlg = new MasterPasswordInputDialog(mainWindow
        .getShell());
    dlg.setMessage("Please enter the master password");
    return dlg.open();
  }

  /**
   * Shows an error
   * 
   * @param s the error to show
   */
  private void showError(String s) {
    MessageBox mb = new MessageBox(mainWindow.getShell(), SWT.ICON_ERROR | SWT.OK);
    mb.setMessage(s);
    mb.open();
  }

  /**
   * The entry point for the application
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Password.getApp().run();
  }
}
