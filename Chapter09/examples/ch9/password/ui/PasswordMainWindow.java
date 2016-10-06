package examples.ch9.password.ui;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import examples.ch9.password.data.*;
import examples.ch9.password.handlers.*;

/**
 * This class represents the main window of the application
 */
public class PasswordMainWindow {
  private Shell shell;
  private CTabFolder tabFolder;

  /**
   * Constructs a PasswordMainWindow
   * 
   * @param newShell the parent shell
   */
  public PasswordMainWindow(Shell newShell) {
    shell = newShell;
    shell.setText("Password");
    createContents();
  }

  /**
   * Gets the shell (main window) of the application
   * 
   * @return Shell
   */
  public Shell getShell() {
    return shell;
  }

  /**
   * Creates the window contents
   */
  private void createContents() {
    shell.setLayout(new FormLayout());

    // Set the menu
    shell.setMenuBar(new PasswordMenu(shell).getMenu());

    // Set the toolbar
    ToolBar toolbar = PasswordToolbarFactory.create(shell);
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    toolbar.setLayoutData(data);

    // Set up the tabs
    tabFolder = new CTabFolder(shell, SWT.TOP);
    data = new FormData();
    data.top = new FormAttachment(toolbar, 0);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    tabFolder.setLayoutData(data);

    // Set a gradient background
    Display display = shell.getDisplay();
    tabFolder.setSelectionBackground(new Color[] {
        display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW),
        display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW),
        display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW),
        display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)}, new int[] { 33,
        66, 100});

    // Create a listener for when the app or a tab is closed
    PasswordTabListener listener = new PasswordTabListener(tabFolder);
    tabFolder.addCTabFolderListener(listener);
    shell.addShellListener(listener);
  }

  /**
   * Adds a new file
   * 
   * @param file the file to add
   */
  public void add(PasswordFile file) {
    // Create a tab for this file
    CTabItem item = new CTabItem(tabFolder, SWT.NONE);

    // Create the content for this tab
    PasswordFileTab tab = new PasswordFileTab(tabFolder, file);
    item.setControl(tab);
    item.setText(tab.getText());

    // Select the new tab
    tabFolder.setSelection(item);
  }

  /**
   * Gets the current file
   * 
   * @return PasswordFile
   */
  public PasswordFile getCurrentFile() {
    PasswordFileTab tab = getCurrentTab();
    return tab == null ? null : tab.getFile();
  }

  /**
   * Gets the current tab
   * 
   * @return PasswordFileTab
   */
  public PasswordFileTab getCurrentTab() {
    CTabItem item = tabFolder.getSelection();
    return item == null ? null : (PasswordFileTab) item.getControl();
  }

  /**
   * Refreshes all the tabs with their file names
   */
  public void refreshTabs() {
    CTabItem[] items = tabFolder.getItems();
    for (int i = 0, n = items.length; i < n; i++) {
      items[i].setText(((PasswordFileTab) items[i].getControl()).getText());
    }
  }
}
