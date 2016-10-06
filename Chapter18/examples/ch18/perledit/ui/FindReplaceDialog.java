package examples.ch18.perledit.ui;

import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class displays a find/replace dialog
 */
public class FindReplaceDialog extends Dialog {
  // The adapter that does the finding/replacing
  private FindReplaceDocumentAdapter frda;

  // The associated viewer
  private ITextViewer viewer;

  // The find and replace buttons
  private Button doFind;
  private Button doReplace;
  private Button doReplaceFind;

  /**
   * FindReplaceDialog constructor
   * 
   * @param shell the parent shell
   * @param document the associated document
   * @param viewer the associated viewer
   */
  public FindReplaceDialog(Shell shell, IDocument document, ITextViewer viewer) {
    super(shell, SWT.DIALOG_TRIM | SWT.MODELESS);
    frda = new FindReplaceDocumentAdapter(document);
    this.viewer = viewer;
  }

  /**
   * Opens the dialog box
   */
  public void open() {
    Shell shell = new Shell(getParent(), getStyle());
    shell.setText("Find/Replace");
    createContents(shell);
    shell.pack();
    shell.open();
    Display display = getParent().getDisplay();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Performs a find
   * 
   * @param find the find string
   * @param forward whether to search forward
   * @param matchCase whether to match case
   * @param wholeWord whether to search on whole word
   * @param regexp whether find string is a regular expression
   */
  protected void doFind(String find, boolean forward, boolean matchCase, 
      boolean wholeWord, boolean regexp) {
    // You can't mix whole word and regexp
    if (wholeWord && regexp) {
      showError("You can't search on both Whole Words and Regular Expressions");
    } else {
      IRegion region = null;
      try {
        // Get the current offset
        int offset = viewer.getTextWidget().getCaretOffset();
        
        // If something is currently selected, and they're searching backwards,
        // move offset to beginning of selection. Otherwise, repeated backwards
        // finds will only find the same text
        if (!forward) {
          Point pt = viewer.getSelectedRange();
          if (pt.x != pt.y) {
            offset = pt.x - 1;
          }
        }

        // Make sure we're in the document
        if (offset >= frda.length()) offset = frda.length() - 1;
        if (offset < 0) offset = 0;

        // Perform the find
        region = frda.find(offset, find, forward, matchCase, wholeWord, regexp);

        // Update the viewer with found selection
        if (region != null) {
          viewer.setSelectedRange(region.getOffset(), region.getLength());
        }

        // If find succeeded, enable Replace buttons.
        // Otherwise, disable Replace buttons.
        // We know find succeeded if region is not null
        enableReplaceButtons(region != null);
      } catch (BadLocationException e) {
        // Ignore
      } catch (PatternSyntaxException e) {
        // Show the error to the user
        showError(e.getMessage());
      }
    }
  }
  
  /**
   * Performs a replace
   * @param replaceText the replacement text
   */
  protected void doReplace(String replaceText) {
    try {
      frda.replace(replaceText, false);
    } catch (BadLocationException e) {
      // Ignore
    }
  }

  /**
   * Creates the dialog's contents
   * 
   * @param shell
   */
  protected void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(2, false));

    // Add the text input fields
    Composite text = new Composite(shell, SWT.NONE);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    text.setLayout(new GridLayout(3, true));

    new Label(text, SWT.LEFT).setText("&Find:");
    final Text findText = new Text(text, SWT.BORDER);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 2;
    findText.setLayoutData(data);

    new Label(text, SWT.LEFT).setText("R&eplace With:");
    final Text replaceText = new Text(text, SWT.BORDER);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 2;
    replaceText.setLayoutData(data);

    // Add the match case checkbox
    final Button match = new Button(text, SWT.CHECK);
    match.setText("&Match Case");

    // Add the whole word checkbox
    final Button wholeWord = new Button(text, SWT.CHECK);
    wholeWord.setText("&Whole Word");

    // Add the regular expression checkbox
    final Button regexp = new Button(text, SWT.CHECK);
    regexp.setText("RegE&xp");

    // Add the direction radio buttons
    final Button down = new Button(text, SWT.RADIO);
    down.setText("D&own");

    final Button up = new Button(text, SWT.RADIO);
    up.setText("&Up");

    // Add the buttons
    Composite buttons = new Composite(shell, SWT.NONE);
    buttons.setLayout(new GridLayout());

    // Create the Find button
    doFind = new Button(buttons, SWT.PUSH);
    doFind.setText("Fi&nd");
    doFind.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Set the initial find operation to FIND_FIRST
    //doFind.setData(FindReplaceOperationCode.FIND_FIRST);

    // Create the Replace button
    doReplace = new Button(buttons, SWT.PUSH);
    doReplace.setText("&Replace");
    doReplace.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the Replace/Find button
    doReplaceFind = new Button(buttons, SWT.PUSH);
    doReplaceFind.setText("Replace/Fin&d");
    doReplaceFind.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    doReplaceFind.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        doReplace(replaceText.getText());
        doFind(findText.getText(), down.getSelection(), match.getSelection(),
            wholeWord.getSelection(), regexp.getSelection());
      }
    });

    // Create the Close button
    Button close = new Button(buttons, SWT.PUSH);
    close.setText("Close");
    close.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    close.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        shell.close();
      }
    });

    // Disable the replace button when find text is modified
    findText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        enableReplaceButtons(false);
      }
    });

    // Do a find
    doFind.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        doFind(findText.getText(), down.getSelection(), match.getSelection(), 
            wholeWord.getSelection(), regexp.getSelection());
      }
    });

    // Replace loses "find" state, so disable buttons
    doReplace.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        doReplace(replaceText.getText());
        enableReplaceButtons(false);
      }
    });

    // Set defaults
    down.setSelection(true);
    findText.setFocus();
    enableReplaceButtons(false);
    shell.setDefaultButton(doFind);
  }

  /**
   * Enables/disables the Replace and Replace/Find buttons
   * 
   * @param enable whether to enable or disable
   */
  protected void enableReplaceButtons(boolean enable) {
    doReplace.setEnabled(enable);
    doReplaceFind.setEnabled(enable);
  }

  /**
   * Shows an error
   * 
   * @param message the error message
   */
  protected void showError(String message) {
    MessageDialog.openError(getParent(), "Error", message);
  }
}
