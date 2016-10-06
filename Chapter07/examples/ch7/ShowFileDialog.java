package examples.ch7;

import java.io.File;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates FileDialog
 */
public class ShowFileDialog {
  // These filter names are displayed to the user in the file dialog. Note that
  // the inclusion of the actual extension in parentheses is optional, and
  // doesn't have any effect on which files are displayed.
  private static final String[] FILTER_NAMES = {
      "OpenOffice.org Spreadsheet Files (*.sxc)",
      "Microsoft Excel Spreadsheet Files (*.xls)",
      "Comma Separated Values Files (*.csv)", "All Files (*.*)"};

  // These filter extensions are used to filter which files are displayed.
  private static final String[] FILTER_EXTS = { "*.sxc", "*.xls", "*.csv", "*.*"};

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("File Dialog");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Creates the contents for the window
   * 
   * @param shell the parent shell
   */
  public void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(5, true));

    new Label(shell, SWT.NONE).setText("File Name:");

    final Text fileName = new Text(shell, SWT.BORDER);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 4;
    fileName.setLayoutData(data);

    Button multi = new Button(shell, SWT.PUSH);
    multi.setText("Open Multiple...");
    multi.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // User has selected to open multiple files
        FileDialog dlg = new FileDialog(shell, SWT.MULTI);
        dlg.setFilterNames(FILTER_NAMES);
        dlg.setFilterExtensions(FILTER_EXTS);
        String fn = dlg.open();
        if (fn != null) {
          // Append all the selected files. Since getFileNames() returns only 
          // the names, and not the path, prepend the path, normalizing
          // if necessary
          StringBuffer buf = new StringBuffer();
          String[] files = dlg.getFileNames();
          for (int i = 0, n = files.length; i < n; i++) {
            buf.append(dlg.getFilterPath());
            if (buf.charAt(buf.length() - 1) != File.separatorChar) {
              buf.append(File.separatorChar);
            }
            buf.append(files[i]);
            buf.append(" ");
          }
          fileName.setText(buf.toString());
        }
      }
    });

    Button open = new Button(shell, SWT.PUSH);
    open.setText("Open...");
    open.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // User has selected to open a single file
        FileDialog dlg = new FileDialog(shell, SWT.OPEN);
        dlg.setFilterNames(FILTER_NAMES);
        dlg.setFilterExtensions(FILTER_EXTS);
        String fn = dlg.open();
        if (fn != null) {
          fileName.setText(fn);
        }
      }
    });

    Button save = new Button(shell, SWT.PUSH);
    save.setText("Save...");
    save.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // User has selected to save a file
        FileDialog dlg = new FileDialog(shell, SWT.SAVE);
        dlg.setFilterNames(FILTER_NAMES);
        dlg.setFilterExtensions(FILTER_EXTS);
        String fn = dlg.open();
        if (fn != null) {
          fileName.setText(fn);
        }
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ShowFileDialog().run();
  }
}
