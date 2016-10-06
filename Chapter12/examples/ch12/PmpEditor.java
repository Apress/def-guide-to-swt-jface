package examples.ch12;

import java.io.IOException;
import java.util.Stack;

// import all the old files for PmpEditor
import examples.ch11.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.printing.*;
import org.eclipse.swt.widgets.*;

/**
 * This program demonstrates StyledText
 */
public class PmpEditor {
  // The number of operations that can be undone
  private static final int UNDO_LIMIT = 500;

  // Contains a reference to this application
  private static PmpEditor app;

  // Contains a reference to the main window
  private Shell shell;

  // Displays the file
  private StyledText st;

  // The full path of the current file
  private String filename;

  // The font for the StyledText
  private Font font;

  // The label to display statistics
  private Label status;

  // The print options and printer
  private StyledTextPrintOptions options;
  private Printer printer;

  // The stack used to store the undo information
  private Stack changes;

  // Flag to set before performaing an undo, so the undo
  // operation doesn't get stored with the rest of the undo
  // information
  private boolean ignoreUndo = false;

  // Syntax data for the current extension
  private SyntaxData sd;

  // Line style listener
  private PmpeLineStyleListener lineStyleListener;

  /**
   * Gets the reference to this application
   * 
   * @return PmpEditor
   */
  public static PmpEditor getApp() {
    return app;
  }

  /**
   * Constructs a PmpEditor
   */
  public PmpEditor() {
    app = this;
    changes = new Stack();

    // Set up the printing options
    options = new StyledTextPrintOptions();
    options.footer = StyledTextPrintOptions.SEPARATOR
        + StyledTextPrintOptions.PAGE_TAG + StyledTextPrintOptions.SEPARATOR
        + "Confidential";
  }

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    shell = new Shell(display);

    // Choose a monospaced font
    font = new Font(display, "Terminal", 12, SWT.NONE);

    createContents(shell);
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }

    font.dispose();
    display.dispose();
    if (printer != null) printer.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    // Set the layout and the menu bar
    shell.setLayout(new FormLayout());
    shell.setMenuBar(new PmpEditorMenu(shell).getMenu());

    // Create the status bar
    status = new Label(shell, SWT.BORDER);

    FormData data = new FormData();
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    data.bottom = new FormAttachment(100, 0);
    data.height = status.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
    status.setLayoutData(data);

    // Create the styled text
    st = new StyledText(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
    data = new FormData();
    data.left = new FormAttachment(0);
    data.right = new FormAttachment(100);
    data.top = new FormAttachment(0);
    data.bottom = new FormAttachment(status);
    st.setLayoutData(data);

    // Set the font
    st.setFont(font);

    // Add Brief delete next word
    // Use SWT.MOD1 instead of SWT.CTRL for portability
    st.setKeyBinding('k' | SWT.MOD1, ST.DELETE_NEXT);

    // Add vi end of line (kind of)
    // Use SWT.MOD1 instead of SWT.CTRL for portability
    // Use SWT.MOD2 instead of SWT.SHIFT for portability
    // Shift+4 is $
    st.setKeyBinding('4' | SWT.MOD1 | SWT.MOD2, ST.LINE_END);

    // Handle key presses
    st.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent event) {
        // Update the status bar
        updateStatus();
      }
    });

    // Handle text modifications
    st.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        // Update the status bar
        updateStatus();

        // Update the comments
        if (lineStyleListener != null) {
          lineStyleListener.refreshMultilineComments(st.getText());
          st.redraw();
        }
      }
    });

    // Store undo information
    st.addExtendedModifyListener(new ExtendedModifyListener() {
      public void modifyText(ExtendedModifyEvent event) {
        if (!ignoreUndo) {
          // Push this change onto the changes stack
          changes.push(new TextChange(event.start, event.length,
              event.replacedText));

          if (changes.size() > UNDO_LIMIT) changes.remove(0);
        }
      }
    });

    // Create the drag and drop types
    Transfer[] types = new Transfer[] { FileTransfer.getInstance()};

    // Create the drop target
    DropTarget target = new DropTarget(st, DND.DROP_MOVE | DND.DROP_COPY
        | DND.DROP_DEFAULT);
    target.setTransfer(types);
    target.addDropListener(new DropTargetAdapter() {
      /**
       * Called when the cursor enters
       */
      public void dragEnter(DropTargetEvent event) {
        // Allow a copy
        if (event.detail == DND.DROP_DEFAULT) {
          event.detail = (event.operations & DND.DROP_COPY) != 0 ? DND.DROP_COPY
            : DND.DROP_NONE;
        }
      }

      /**
       * Called when the cursor drags over the target
       */
      public void dragOver(DropTargetEvent event) {
        // Give feedback
        event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
      }

      /**
       * Called when user drops the files
       */
      public void drop(DropTargetEvent event) {
        // See if it's a file
        if (FileTransfer.getInstance().isSupportedType(event.currentDataType)) {
          String[] files = (String[]) event.data;

          // Since we support only one file, open the first one
          if (files.length > 0) openFile(files[0]);
        }
      }
    });

    // Update the title bar and the status bar
    updateTitle();
    updateStatus();
  }

  /**
   * Opens a file
   */
  public void openFile() {
    openFile(null);
  }
  
  /**
   * Opens a file
   */
  public void openFile(String temp) {
    if (temp == null) {
      FileDialog dlg = new FileDialog(shell);
      temp = dlg.open();
    }

    if (temp != null) {
      try {
        // Get the file's contents
        String text = PmpeIoManager.getFile(temp);

        // File loaded, so save the file name
        filename = temp;

        // Update the syntax properties to use
        updateSyntaxData();

        // Put the new file's data in the StyledText
        st.setText(text);

        // Update the title bar
        updateTitle();

        // Delete any undo information
        changes.clear();
      } catch (IOException e) {
        showError(e.getMessage());
      }
    }
  }

  /**
   * Saves a file
   */
  public void saveFile() {
    if (filename == null) {
      saveFileAs();
    } else {
      try {
        // Save the file and update the title bar based on the new file
        // name
        PmpeIoManager.saveFile(filename, st.getText().getBytes());
        updateTitle();
      } catch (IOException e) {
        showError(e.getMessage());
      }
    }
  }

  /**
   * Saves a file under a different name
   */
  public void saveFileAs() {
    SafeSaveDialog dlg = new SafeSaveDialog(shell);

    if (filename != null) {
      dlg.setFileName(filename);
    }

    String temp = dlg.open();

    if (temp != null) {
      filename = temp;

      // The extension may have changed; update the syntax data
      // accordingly
      updateSyntaxData();
      saveFile();
    }
  }

  /**
   * Prints the document to the default printer
   */
  public void print() {
    if (printer == null) printer = new Printer();
    options.header = StyledTextPrintOptions.SEPARATOR + filename
        + StyledTextPrintOptions.SEPARATOR;
    st.print(printer, options).run();
  }

  /**
   * Cuts the current selection to the clipboard
   */
  public void cut() {
    st.cut();
  }

  /**
   * Copies the current selection to the clipboard
   */
  public void copy() {
    st.copy();
  }

  /**
   * Pastes the clipboard's contents
   */
  public void paste() {
    st.paste();
  }

  /**
   * Selects all the text
   */
  public void selectAll() {
    st.selectAll();
  }

  /**
   * Undoes the last change
   */
  public void undo() {
    // Make sure undo stack isn't empty
    if (!changes.empty()) {
      // Get the last change
      TextChange change = (TextChange) changes.pop();

      // Set the flag. Otherwise, the replaceTextRange call will get
      // placed
      // on the undo stack
      ignoreUndo = true;

      // Replace the changed text
      st.replaceTextRange(change.getStart(), change.getLength(), change
          .getReplacedText());

      // Move the caret
      st.setCaretOffset(change.getStart());

      // Scroll the screen
      st.setTopIndex(st.getLineAtOffset(change.getStart()));
      ignoreUndo = false;
    }
  }

  /**
   * Toggles word wrap
   */
  public void toggleWordWrap() {
    st.setWordWrap(!st.getWordWrap());
  }

  /**
   * Gets the current word wrap settings
   * 
   * @return boolean
   */
  public boolean getWordWrap() {
    return st.getWordWrap();
  }

  /**
   * Shows an about box
   */
  public void about() {
    MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
    mb.setMessage("Poor Man's Programming Editor");
    mb.open();
  }

  /**
   * Updates the title bar
   */
  private void updateTitle() {
    String fn = filename == null ? "Untitled" : filename;
    shell.setText(fn + " -- PmPe");
  }

  /**
   * Updates the status bar
   */
  private void updateStatus() {
    // Show the offset into the file, the total number of characters in the
    // file,
    // the current line number (1-based) and the total number of lines
    StringBuffer buf = new StringBuffer();
    buf.append("Offset: ");
    buf.append(st.getCaretOffset());
    buf.append("\tChars: ");
    buf.append(st.getCharCount());
    buf.append("\tLine: ");
    buf.append(st.getLineAtOffset(st.getCaretOffset()) + 1);
    buf.append(" of ");
    buf.append(st.getLineCount());
    status.setText(buf.toString());
  }

  /**
   * Updates the syntax data based on the filename's extension
   */
  private void updateSyntaxData() {
    // Determine the extension of the current file
    String extension = "";

    if (filename != null) {
      int pos = filename.lastIndexOf(".");

      if (pos > -1 && pos < filename.length() - 2) {
        extension = filename.substring(pos + 1);
      }
    }

    // Get the syntax data for the extension
    sd = SyntaxManager.getSyntaxData(extension);

    // Reset the line style listener
    if (lineStyleListener != null) {
      st.removeLineStyleListener(lineStyleListener);
    }

    lineStyleListener = new PmpeLineStyleListener(sd);
    st.addLineStyleListener(lineStyleListener);

    // Redraw the contents to reflect the new syntax data
    st.redraw();
  }

  /**
   * Shows an error message
   * 
   * @param error the text to show
   */
  private void showError(String error) {
    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    mb.setMessage(error);
    mb.open();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PmpEditor().run();
  }
}
