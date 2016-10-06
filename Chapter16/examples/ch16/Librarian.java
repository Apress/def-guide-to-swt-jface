package examples.ch16;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class keeps track of you library, and who you've loaned books to
 */
public class Librarian extends ApplicationWindow {
  // A static instance to the running application
  private static Librarian APP;

  // Table column names/properties
  public static final String TITLE = "Title";
  public static final String CHECKED_OUT = "?";
  public static final String WHO = "By Whom";
  public static final String[] PROPS = { TITLE, CHECKED_OUT, WHO};

  // The viewer
  private TableViewer viewer;

  // The current library
  private Library library;

  // The actions
  private NewAction newAction;
  private OpenAction openAction;
  private SaveAction saveAction;
  private SaveAsAction saveAsAction;
  private ExitAction exitAction;
  private AddBookAction addBookAction;
  private RemoveBookAction removeBookAction;
  private AboutAction aboutAction;
  private ShowBookCountAction showBookCountAction;

  /**
   * Gets the running application
   */
  public static final Librarian getApp() {
    return APP;
  }

  /**
   * Librarian constructor
   */
  public Librarian() {
    super(null);

    APP = this;

    // Create the data model
    library = new Library();

    // Create the actions
    newAction = new NewAction();
    openAction = new OpenAction();
    saveAction = new SaveAction();
    saveAsAction = new SaveAsAction();
    exitAction = new ExitAction();
    addBookAction = new AddBookAction();
    removeBookAction = new RemoveBookAction();
    aboutAction = new AboutAction();
    showBookCountAction = new ShowBookCountAction();

    addMenuBar();
    addCoolBar(SWT.NONE);
    addStatusLine();
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
    shell.setText("Librarian");
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, false));

    viewer = new TableViewer(composite, SWT.FULL_SELECTION | SWT.BORDER);
    Table table = viewer.getTable();
    table.setLayoutData(new GridData(GridData.FILL_BOTH));

    // Set up the viewer
    viewer.setContentProvider(new LibraryContentProvider());
    viewer.setLabelProvider(new LibraryLabelProvider());
    viewer.setInput(library);
    viewer.setColumnProperties(PROPS);
    viewer.setCellEditors(new CellEditor[] { new TextCellEditor(table),
        new CheckboxCellEditor(table), new TextCellEditor(table)});
    viewer.setCellModifier(new LibraryCellModifier());

    // Set up the table
    for (int i = 0, n = PROPS.length; i < n; i++)
      new TableColumn(table, SWT.LEFT).setText(PROPS[i]);
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    // Add code to hide or display the book count based on the action
    showBookCountAction.addPropertyChangeListener(new IPropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent event) {
        // The value has changed; refresh the view
        refreshView();
      }
    });

    // Rfresh the view to get the columns right-sized
    refreshView();

    return composite;
  }

  /**
   * Creates the menu for the application
   * 
   * @return MenuManager
   */
  protected MenuManager createMenuManager() {
    // Create the main menu
    MenuManager mm = new MenuManager();

    // Create the File menu
    MenuManager fileMenu = new MenuManager("File");
    mm.add(fileMenu);

    // Add the actions to the File menu
    fileMenu.add(newAction);
    fileMenu.add(openAction);
    fileMenu.add(saveAction);
    fileMenu.add(saveAsAction);
    fileMenu.add(new Separator());
    fileMenu.add(exitAction);

    // Create the Book menu
    MenuManager bookMenu = new MenuManager("Book");
    mm.add(bookMenu);

    // Add the actions to the Book menu
    bookMenu.add(addBookAction);
    bookMenu.add(removeBookAction);

    // Create the View menu
    MenuManager viewMenu = new MenuManager("View");
    mm.add(viewMenu);

    // Add the actions to the View menu
    viewMenu.add(showBookCountAction);

    // Create the Help menu
    MenuManager helpMenu = new MenuManager("Help");
    mm.add(helpMenu);

    // Add the actions to the Help menu
    helpMenu.add(aboutAction);

    return mm;
  }

  /**
   * Creates the toolbar for the application
   */
  protected ToolBarManager createToolBarManager(int style) {
    // Create the toolbar manager
    ToolBarManager tbm = new ToolBarManager(style);

    // Add the file actions
    tbm.add(newAction);
    tbm.add(openAction);
    tbm.add(saveAction);
    tbm.add(saveAsAction);

    // Add a separator
    tbm.add(new Separator());

    // Add the book actions
    tbm.add(addBookAction);
    tbm.add(removeBookAction);

    // Add a separator
    tbm.add(new Separator());

    // Add the show book count, which will appear as a toggle button
    tbm.add(showBookCountAction);

    // Add a separator
    tbm.add(new Separator());

    // Add the about action
    tbm.add(aboutAction);

    return tbm;
  }

  /**
   * Creates the coolbar for the application
   */
  protected CoolBarManager createCoolBarManager(int style) {
    // Create the coolbar manager
    CoolBarManager cbm = new CoolBarManager(style);

    // Add the toolbar
    cbm.add(createToolBarManager(SWT.FLAT));

    return cbm;
  }

  /**
   * Creates the status line manager
   */
  protected StatusLineManager createStatusLineManager() {
    return new StatusLineManager();
  }

  /**
   * Adds a book
   */
  public void addBook() {
    library.add(new Book("[Enter Title]"));
    refreshView();
  }

  /**
   * Removes the selected book
   */
  public void removeSelectedBook() {
    Book book = (Book) ((IStructuredSelection) viewer.getSelection())
        .getFirstElement();
    if (book != null) library.remove(book);
    refreshView();
  }

  /**
   * Opens a file
   * 
   * @param fileName the file name
   */
  public void openFile(final String fileName) {
    if (checkOverwrite()) {
      // Disable the actions, so user can't change library while loading
      enableActions(false);

      library = new Library();
      try {
        // Launch the Open runnable
        ModalContext.run(new IRunnableWithProgress() {
          public void run(IProgressMonitor progressMonitor) {
            try {
              progressMonitor.beginTask("Loading", IProgressMonitor.UNKNOWN);
              library.load(fileName);
              progressMonitor.done();
              viewer.setInput(library);
              refreshView();
            } catch (IOException e) {
              showError("Can't load file " + fileName + "\r" + e.getMessage());
            }
          }
        }, true, getStatusLineManager().getProgressMonitor(), getShell()
            .getDisplay());
      } catch (InterruptedException e) {} catch (InvocationTargetException e) {} finally {
        // Enable actions
        enableActions(true);
      }
    }
  }

  /**
   * Creates a new file
   */
  public void newFile() {
    if (checkOverwrite()) {
      library = new Library();
      viewer.setInput(library);
    }
  }

  /**
   * Saves the current file
   */
  public void saveFile() {
    String fileName = library.getFileName();
    if (fileName == null) {
      fileName = new SafeSaveDialog(getShell()).open();
    }
    saveFileAs(fileName);
  }

  /**
   * Saves the current file using the specified file name
   * 
   * @param fileName the file name
   */
  public void saveFileAs(final String fileName) {
    // Disable the actions, so user can't change file while it's saving
    enableActions(false);

    try {
      // Launch the Save runnable
      ModalContext.run(new IRunnableWithProgress() {
        public void run(IProgressMonitor progressMonitor) {
          try {
            progressMonitor.beginTask("Saving", IProgressMonitor.UNKNOWN);
            library.save(fileName);
            progressMonitor.done();
          } catch (IOException e) {
            showError("Can't save file " + library.getFileName() + "\r"
                + e.getMessage());
          }
        }
      }, true, getStatusLineManager().getProgressMonitor(), getShell()
          .getDisplay());
    } catch (InterruptedException e) {} catch (InvocationTargetException e) {} finally {
      // Enable the actions
      enableActions(true);
    }
  }

  /**
   * Shows an error
   * 
   * @param msg the error
   */
  public void showError(String msg) {
    MessageDialog.openError(getShell(), "Error", msg);
  }

  /**
   * Refreshes the view
   */
  public void refreshView() {
    // Refresh the view
    viewer.refresh();

    // Repack the columns
    for (int i = 0, n = viewer.getTable().getColumnCount(); i < n; i++) {
      viewer.getTable().getColumn(i).pack();
    }

    getStatusLineManager().setMessage(
        showBookCountAction.isChecked() ? "Book Count: "
            + library.getBooks().size() : "");
  }

  /**
   * Checks the current file for unsaved changes. If it has unsaved changes,
   * confirms that user wants to overwrite
   * 
   * @return boolean
   */
  public boolean checkOverwrite() {
    boolean proceed = true;
    if (library.isDirty()) {
      proceed = MessageDialog.openConfirm(getShell(), "Are you sure?",
          "You have unsaved changes--are you sure you want to lose them?");
    }
    return proceed;
  }

  /**
   * Sets the current library dirty
   */
  public void setLibraryDirty() {
    library.setDirty();
  }

  /**
   * Closes the application
   */
  public boolean close() {
    if (checkOverwrite()) return super.close();
    return false;
  }

  /**
   * Enables or disables the actions
   * 
   * @param enable true to enable, false to disable
   */
  private void enableActions(boolean enable) {
    newAction.setEnabled(enable);
    openAction.setEnabled(enable);
    saveAction.setEnabled(enable);
    saveAsAction.setEnabled(enable);
    exitAction.setEnabled(enable);
    addBookAction.setEnabled(enable);
    removeBookAction.setEnabled(enable);
    aboutAction.setEnabled(enable);
    showBookCountAction.setEnabled(enable);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new Librarian().run();
  }
}