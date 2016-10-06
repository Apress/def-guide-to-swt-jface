package examples.ch8.xmlview;

import java.io.*;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This application displays an XML file. It uses JDOM (http://www.jdom.org) to
 * parse the file.
 */
public class XmlView {
  /** The path to the directory containing the images */
  public static final String IMAGE_PATH = "images"
      + System.getProperty("file.separator");

  private static final String[] FILTER_NAMES = { "XML Files (*.xml)",
      "All Files (*.*)"};

  private static final String[] FILTER_EXTS = { "*.xml", "*.*"};

  private static final XmlView app = new XmlView();

  /**
   * Gets the running application
   * 
   * @return XmlView
   */
  public static XmlView getApp() {
    return app;
  }

  private Shell shell;
  private String filePath;
  private TabFolder tabFolder;
  private XmlViewToolbar toolBar;
  private Image icon;

  /**
   * Gets the shell (main window) of the application
   * 
   * @return Shell
   * @throws IllegalStateException if the main window has not yet been created
   */
  public Shell getShell() {
    if (shell == null) { throw new IllegalStateException(
        "The main window has not yet been created"); }
    return shell;
  }

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    shell = new Shell(display);
    shell.setText("XML View");
    try {
      // Set the icon in the title bar
      icon = new Image(display, new FileInputStream(XmlView.IMAGE_PATH
          + "xmlview.gif"));
      shell.setImage(icon);
    } catch (IOException e) {
      // Just do without the icon
    }
    createContents();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    dispose();
    display.dispose();
  }

  /**
   * Disposes any resources associated with this program
   */
  private void dispose() {
    if (icon != null) {
      icon.dispose();
    }
    toolBar.dispose();
  }

  /**
   * Creates the window contents
   */
  private void createContents() {
    shell.setLayout(new FormLayout());

    // Create the menu
    shell.setMenuBar(new XmlViewMenu(shell).getMenu());

    // Create the toolbar and attach it to the top of the window
    toolBar = new XmlViewToolbar(shell);
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    toolBar.getToolBar().setLayoutData(data);

    // Create the containing tab folder
    tabFolder = new TabFolder(shell, SWT.TOP);
    data = new FormData();
    data.top = new FormAttachment(toolBar.getToolBar(), 0);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    tabFolder.setLayoutData(data);
  }

  /**
   * Shows an error
   * 
   * @param s the error to show
   */
  private void showError(String s) {
    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    mb.setText("Error");
    mb.setMessage(s);
    mb.open();
  }

  /**
   * Opens a file
   */
  public void openFile() {
    // Show the file open dialog
    FileDialog dlg = new FileDialog(shell);
    dlg.setFilterNames(FILTER_NAMES);
    dlg.setFilterExtensions(FILTER_EXTS);
    String fileName = dlg.open();
    if (fileName != null) {
      // A file was selected, so load it
      XmlDocument doc = new XmlDocument(fileName);
      try {
        // Open the file
        doc.open();

        // Create a tab to hold the file
        TabItem item = new TabItem(tabFolder, SWT.NONE);

        // Create the contents for the tab, set it into the tab,
        // and set its text to the filename
        XmlViewTab tab = new XmlViewTab(tabFolder, doc);
        item.setControl(tab);
        item.setText(tab.getText());

        // Select the tab
        tabFolder.setSelection(new TabItem[] { item});
      } catch (IOException e) {
        showError(e.getMessage());
      }
    }
  }

  /**
   * Closes a file
   */
  public void closeFile() {
    TabItem[] items = tabFolder.getSelection();
    for (int i = 0, n = items.length; i < n; i++) {
      items[i].dispose();
    }
  }

  /**
   * Shows the about box
   */
  public void about() {
    MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
    mb.setText("About");
    mb.setMessage("XML View\nAn XML file viewer written in SWT"
        + "\nUses JDOM (http://www.jdom.org/)");
    mb.open();
  }

  /**
   * The entry point for the application
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    XmlView.getApp().run();
  }
}
