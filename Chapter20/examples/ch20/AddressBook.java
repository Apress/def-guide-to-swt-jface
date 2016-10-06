package examples.ch20;

import java.util.*;

import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * This class displays an address book, using a wizard to add a new entry
 */
public class AddressBook extends ApplicationWindow {
  // The running instance of the application
  private static AddressBook APP;

  // The action that launches the wizard
  AddEntryAction addEntryAction;

  // The entries in the address book
  java.util.List entries;

  // The view
  private TableViewer viewer;

  /**
   * AddressBook constructor
   */
  public AddressBook() {
    super(null);

    // Store a reference to the running app
    APP = this;

    // Create the action and the entries collection
    addEntryAction = new AddEntryAction();
    entries = new LinkedList();

    // Create the toolbar
    addToolBar(SWT.NONE);
  }

  /**
   * Gets a reference to the running application
   * 
   * @return AddressBook
   */
  public static AddressBook getApp() {
    return APP;
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
   * Adds an entry
   * 
   * @param entry the entry
   */
  public void add(AddressEntry entry) {
    entries.add(entry);
    refresh();
  }

  /**
   * Configures the shell
   * 
   * @param shell the shell
   */
  protected void configureShell(Shell shell) {
    super.configureShell(shell);

    // Set the title bar text
    shell.setText("Address Book");
    shell.setSize(600, 400);
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    // Create the table viewer
    viewer = new TableViewer(parent);
    viewer.setContentProvider(new AddressBookContentProvider());
    viewer.setLabelProvider(new AddressBookLabelProvider());
    viewer.setInput(entries);

    // Set up the table
    Table table = viewer.getTable();
    new TableColumn(table, SWT.LEFT).setText("First Name");
    new TableColumn(table, SWT.LEFT).setText("Last Name");
    new TableColumn(table, SWT.LEFT).setText("E-mail Address");
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    // Update the column widths
    refresh();

    return table;
  }

  /**
   * Creates the toolbar
   * 
   * @param style the toolbar style
   * @return ToolBarManager
   */
  protected ToolBarManager createToolBarManager(int style) {
    ToolBarManager tbm = new ToolBarManager(style);

    // Add the action to launch the wizard
    tbm.add(addEntryAction);

    return tbm;
  }

  /**
   * Updates the column widths
   */
  private void refresh() {
    viewer.refresh();

    // Pack the columns
    Table table = viewer.getTable();
    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
      table.getColumn(i).pack();
    }
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new AddressBook().run();
  }
}