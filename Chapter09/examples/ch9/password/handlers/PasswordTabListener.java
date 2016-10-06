package examples.ch9.password.handlers;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import examples.ch9.password.*;
import examples.ch9.password.ui.*;
import examples.ch9.password.data.*;

/**
 * This class listens for ShellEvents and CTabFolderEvents. It's used to make
 * sure user has the chance to save any dirty files when either the main window
 * or a single tab is closed.
 */
public class PasswordTabListener implements ShellListener, CTabFolderListener {
  // The parent of all the tabs
  private CTabFolder folder;

  /**
   * Constructs a PasswordTabListener
   * 
   * @param folder the parent folder
   */
  public PasswordTabListener(CTabFolder folder) {
    this.folder = folder;
  }

  /**
   * Called when the shell is activated
   */
  public void shellActivated(ShellEvent event) {}

  /**
   * Called when the shell is deactivated
   */
  public void shellDeactivated(ShellEvent event) {}

  /**
   * Called when the shell is iconified
   */
  public void shellIconified(ShellEvent event) {}

  /**
   * Called when the shell is deiconified
   */
  public void shellDeiconified(ShellEvent event) {}

  /**
   * Called when the shell is closed
   */
  public void shellClosed(ShellEvent event) {
    // Go through all the open files, offering to save dirty ones
    CTabItem[] items = folder.getItems();
    for (int i = 0, n = items.length; i < n; i++) {
      boolean doit = prompt(items[i]);
      if (!doit) {
        // If they hit Cancel for any file, don't close the window
        event.doit = false;
        break;
      }
    }
  }

  /**
   * Called when a tab is closed
   * 
   * @param event the event
   */
  public void itemClosed(CTabFolderEvent event) {
    event.doit = prompt((CTabItem) event.item);
  }

  /**
   * Prompts the user to save a "dirty" file
   * 
   * @param item the tab being closed
   * @return boolean
   */
  public boolean prompt(CTabItem item) {
    boolean doit = true;

    // Get the file associated with the tab
    PasswordFileTab tab = (PasswordFileTab) item.getControl();
    PasswordFile file = tab.getFile();

    // Only prompt if the file is dirty
    if (file.isDirty()) {
      Password app = Password.getApp();

      // Ask if they want to save
      MessageBox mb = new MessageBox(app.getMainWindow().getShell(),
          SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
      String fileName = file.getFilename();
      if (fileName == null) {
        fileName = PasswordFileTab.UNTITLED;
      }
      mb.setMessage("Password file " + fileName + " has changed."
          + "\nDo you want to save the changes?");
      switch (mb.open()) {
      case SWT.YES:
        // If they say yes, save it
        if (!app.saveFile()) {
          // They said they wanted to save, but hit cancel on the file dialog,
          // so abort the close
          doit = false;
        }
        break;
      case SWT.NO:
        break;
      case SWT.CANCEL:
        doit = false;
        break;
      }
    }
    return doit;
  }
}
