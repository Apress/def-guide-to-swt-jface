package examples.ch18.perledit.ui;

import org.eclipse.jface.action.*;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.*;
import org.eclipse.jface.text.source.*;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.*;

import examples.ch18.perledit.*;
import examples.ch18.perledit.actions.*;
import examples.ch18.perledit.source.PerlEditorSourceViewerConfiguration;

/**
 * This class provides the main window of PerlEditor
 */
public class MainWindow extends ApplicationWindow implements
    IPropertyChangeListener {
  // The viewer
  private SourceViewer viewer;

  // The actions
  private AboutAction aboutAction = new AboutAction();
  private CopyAction copyAction = new CopyAction();
  private CutAction cutAction = new CutAction();
  private ExitAction exitAction = new ExitAction();
  private FindAction findAction = new FindAction();
  private NewAction newAction = new NewAction();
  private OpenAction openAction = new OpenAction();
  private PasteAction pasteAction = new PasteAction();
  private PreferencesAction prefsAction = new PreferencesAction();
  private PrintAction printAction = new PrintAction();
  private RedoAction redoAction = new RedoAction();
  private SaveAction saveAction = new SaveAction();
  private SaveAsAction saveAsAction = new SaveAsAction();
  private UndoAction undoAction = new UndoAction();

  // The font
  private Font font;

  // The undo manager
  private IUndoManager undoManager;

  /**
   * MainWindow constructor
   */
  public MainWindow() {
    super(null);
    addMenuBar();
    addToolBar(SWT.FLAT);
  }

  /**
   * Runs the application
   */
  public void run() {
    setBlockOnOpen(true);
    open();
    Display.getCurrent().dispose();
  }

  /**
   * Configures the shell
   * 
   * @param shell the shell
   */
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText("Perl Editor");
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    // Create the viewer
    viewer = new SourceViewer(parent, new VerticalRuler(10), SWT.V_SCROLL
        | SWT.H_SCROLL);

    // Configure it and set the document
    viewer.configure(new PerlEditorSourceViewerConfiguration());
    viewer.setDocument(PerlEditor.getApp().getDocument());

    // Set any preferences
    loadPreferences();

    // Create the undo manager
    undoManager = new DefaultUndoManager(100);
    undoManager.connect(viewer);

    viewer.getTextWidget().setFocus();

    // Return the StyledText
    return viewer.getTextWidget();
  }

  protected void loadPreferences() {
    IPreferenceStore ps = PerlEditor.getApp().getPreferences();
    setWrap(ps.getBoolean(Constants.WRAP));

    String fontProp = ps.getString(Constants.FONT);
    if (fontProp.length() > 0) {
      FontData[] fd = new FontData[1];
      fd[0] = new FontData(fontProp);
      setFont(fd);
    }
  }

  /**
   * Updates the view with the preferences
   *  
   */
  public void propertyChange(PropertyChangeEvent event) {
    if (Constants.WRAP.equals(event.getProperty()))
      setWrap(((Boolean) event.getNewValue()).booleanValue());
    else if (Constants.FONT.equals(event.getProperty()))
        setFont((FontData[]) event.getNewValue());
  }

  /**
   * Creates the menu manager
   * 
   * @return MenuManager
   */
  protected MenuManager createMenuManager() {
    MenuManager mm = new MenuManager();
    MenuManager fileMenu = new MenuManager("&File");
    MenuManager editMenu = new MenuManager("&Edit");
    MenuManager helpMenu = new MenuManager("&Help");

    mm.add(fileMenu);
    mm.add(editMenu);
    mm.add(helpMenu);

    fileMenu.add(newAction);
    fileMenu.add(openAction);
    fileMenu.add(saveAction);
    fileMenu.add(saveAsAction);
    fileMenu.add(new Separator());
    fileMenu.add(printAction);
    fileMenu.add(new Separator());
    fileMenu.add(exitAction);

    editMenu.add(undoAction);
    editMenu.add(redoAction);
    editMenu.add(new Separator());
    editMenu.add(cutAction);
    editMenu.add(copyAction);
    editMenu.add(pasteAction);
    editMenu.add(new Separator());
    editMenu.add(findAction);
    editMenu.add(new Separator());
    editMenu.add(prefsAction);

    helpMenu.add(aboutAction);

    return mm;
  }

  /**
   * Creates the toolbar
   * 
   * @param style the style for the toolbar
   * @return ToolBarManager
   */
  protected ToolBarManager createToolBarManager(int style) {
    ToolBarManager tm = new ToolBarManager(style);

    // Add all the actions
    tm.add(newAction);
    tm.add(openAction);
    tm.add(saveAction);
    tm.add(saveAsAction);
    tm.add(new Separator());
    tm.add(printAction);
    tm.add(new Separator());
    tm.add(undoAction);
    tm.add(redoAction);
    tm.add(new Separator());
    tm.add(cutAction);
    tm.add(copyAction);
    tm.add(pasteAction);
    tm.add(new Separator());
    tm.add(findAction);
    tm.add(new Separator());
    tm.add(prefsAction);
    tm.add(new Separator());
    tm.add(aboutAction);

    return tm;
  }

  /**
   * Gets the text viewer
   * 
   * @return ITextViewer
   */
  public ITextViewer getViewer() {
    return viewer;
  }

  /**
   * Sets the font
   * 
   * @param fontData
   */
  public void setFont(FontData[] fontData) {
    // Create the font
    Font temp = new Font(getShell().getDisplay(), fontData);

    // If creation succeeded, dispose the old font
    if (font != null) font.dispose();

    // Use the new font
    font = temp;
    viewer.getTextWidget().setFont(font);
  }

  /**
   * Turns on/off word wrap
   * 
   * @param wrap true to wrap
   */
  public void setWrap(boolean wrap) {
    viewer.getTextWidget().setWordWrap(wrap);
  }

  /**
   * Gets the undo manager
   * 
   * @return IUndoManager
   */
  public IUndoManager getUndoManager() {
    return undoManager;
  }

  /**
   * Closes the main window
   */
  public boolean close() {
    boolean close = false;
    if (PerlEditor.getApp().checkOverwrite()) {
      close = super.close();
      if (close) {
        if (font != null) font.dispose();
      }
    }
    return close;
  }
}
