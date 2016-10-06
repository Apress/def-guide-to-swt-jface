package examples.ch18.perledit;

import java.io.*;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.DefaultPartitioner;

import examples.ch18.perledit.model.*;
import examples.ch18.perledit.source.*;
import examples.ch18.perledit.ui.*;

/**
 * This class demonstrates TextViewer and Document.
 */
public class PerlEditor {
  // Set up the name of the partitioner
  public static final String PERL_PARTITIONING = "perl_partitioning";

  // A reference to the current app
  private static PerlEditor APP;

  // The current document
  private PersistentDocument document;

  // The main window
  private MainWindow mainWindow;

  // The stored preferences
  private PreferenceStore prefs;

  // The partition scanner
  private PerlPartitionScanner scanner;

  // The code scanner
  private PerlCodeScanner codeScanner;

  // The color manager
  private ColorManager colorManager;

  /**
   * Returns the application instance
   * @return PerlEditor
   */
  public static PerlEditor getApp() {
    return APP;
  }

  /**
   * PerlEditor constructor
   */
  public PerlEditor() {
    APP = this;

    colorManager = new ColorManager();
    codeScanner = new PerlCodeScanner();

    // Set up the document
    setUpDocument();

    mainWindow = new MainWindow();
    prefs = new PreferenceStore("perlEditor.properties");
    prefs.addPropertyChangeListener(mainWindow);
    try {
      prefs.load();
    } catch (IOException e) {
      // Ignore
    }
  }

  /**
   * Sets up the document
   */
  protected void setUpDocument() {
    // Create the document
    document = new PersistentDocument();
    
    // Create the partition scanner
    scanner = new PerlPartitionScanner();
    
    // Create the partitioner
    IDocumentPartitioner partitioner = new DefaultPartitioner(scanner,
      PerlPartitionScanner.TYPES);
    
    // Connect the partitioner and document
    document.setDocumentPartitioner(PERL_PARTITIONING, partitioner);
    partitioner.connect(document);
  }

  /**
   * Runs the application
   */
  public void run() {
    mainWindow.run();

    // Dispose the colors
    if (colorManager != null) colorManager.dispose();
  }

  /**
   * Creates a new file
   */
  public void newFile() {
    if (checkOverwrite()) document.clear();
  }

  /**
   * Opens a file
   * @param fileName the file name
   */
  public void openFile(String fileName) {
    if (checkOverwrite()) {
      try {
        document.clear();
        document.setFileName(fileName);
        document.open();
      } catch (IOException e) {
        showError(e.getMessage());
      }
    }
  }

  /**
   * Saves the current file
   */
  public void saveFile() {
    String fileName = document.getFileName();
    if (fileName == null) {
      SafeSaveDialog dlg = new SafeSaveDialog(mainWindow.getShell());
      dlg.setFilterNames(Constants.FILTER_NAMES);
      dlg.setFilterExtensions(Constants.FILTER_EXTENSIONS);
      fileName = dlg.open();
    }
    if (fileName != null) saveFileAs(fileName);
  }

  /**
   * Saves the current file using the specified file name
   * @param fileName the file name
   */
  public void saveFileAs(String fileName) {
    try {
      document.setFileName(fileName);
      document.save();
    } catch (IOException e) {
      showError("Can't save file " + fileName + "; " + e.getMessage());
    }
  }

  /**
   * Prints the document
   */
  public void print() {
    mainWindow.getViewer().getTextWidget().print();
  }

  /**
   * Cuts the selection to the clipboard
   */
  public void cut() {
    mainWindow.getViewer().getTextWidget().cut();
  }

  /**
   * Copies the selection to the clipboard
   */
  public void copy() {
    mainWindow.getViewer().getTextWidget().copy();
  }

  /**
   * Pastes the clipboard
   */
  public void paste() {
    mainWindow.getViewer().getTextWidget().paste();
  }

  /**
   * Undoes the last operation
   */
  public void undo() {
    mainWindow.getUndoManager().undo();
  }

  /**
   * Redoes the last operation
   */
  public void redo() {
    mainWindow.getUndoManager().redo();
  }

  /**
   * Checks the current file for unsaved changes. If it has unsaved changes,
   * confirms that user wants to overwrite
   * @return boolean
   */
  public boolean checkOverwrite() {
    boolean proceed = true;
    if (document.isDirty()) {
      proceed = MessageDialog.openConfirm(mainWindow.getShell(), "Are you sure?",
          "You have unsaved changes--are you sure you want to lose them?");
    }
    return proceed;
  }

  /**
   * Gets the main window
   * @return MainWindow
   */
  public MainWindow getMainWindow() {
    return mainWindow;
  }

  /**
   * Gets the document
   * @return PersistentDocument
   */
  public PersistentDocument getDocument() {
    return document;
  }

  /**
   * Gets the preferences
   * @return PreferenceStore
   */
  public PreferenceStore getPreferences() {
    return prefs;
  }

  /**
   * Gets the color manager
   * @return ColorManager
   */
  public ColorManager getColorManager() {
    return colorManager;
  }

  /**
   * Gets the code scanner
   * @return PerlCodeScanner
   */
  public PerlCodeScanner getCodeScanner() {
    return codeScanner;
  }

  /**
   * Shows an error
   * @param msg the error
   */
  public void showError(String msg) {
    MessageDialog.openError(mainWindow.getShell(), "Error", msg);
  }

  /**
   * The application entry point
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PerlEditor().run();
  }
}
