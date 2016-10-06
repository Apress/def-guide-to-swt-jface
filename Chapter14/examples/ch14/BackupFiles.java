package examples.ch14;

import java.io.*;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates CheckboxTableViewer. It allows you to check files to
 * copy, and copy them to a backup directory.
 */
public class BackupFiles extends ApplicationWindow {
  // Used to show status
  private Label status;

  /**
   * BackupFiles constructor
   */
  public BackupFiles() {
    super(null);
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

    // Set the title bar text and the size
    shell.setText("Backup Files");
    shell.setSize(400, 400);
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

    // Create the source directory panel and its controls
    final Text sourceDir = createFilePanelHelper(composite, "Source Dir:");

    // Create the CheckboxTableViewer to display the files in the source dir
    final CheckboxTableViewer ctv = CheckboxTableViewer.newCheckList(composite,
        SWT.BORDER);
    ctv.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
    ctv.setContentProvider(new BackupFilesContentProvider());
    ctv.setLabelProvider(new BackupFilesLabelProvider());

    // Create the destination directory panel and its controls
    final Text destDir = createFilePanelHelper(composite, "Dest Dir:");

    // Create the Copy button
    Button copy = new Button(composite, SWT.PUSH);
    copy.setText("Copy");

    // Create the status field
    status = new Label(composite, SWT.NONE);
    status.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // When the source directory changes, change the input for the viewer
    sourceDir.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        ctv.setInput(((Text) event.widget).getText());
      }
    });

    // When copy is pressed, copy the files
    copy.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Get the checked elements
        Object[] files = ctv.getCheckedElements();
        if (files.length > 0) {
          // Some files are checked; make sure we have a valid destination
          File dest = new File(destDir.getText());
          if (dest.isDirectory()) {
            // Go through each file
            for (int i = 0, n = files.length; i < n; i++) {
              copyFile((File) files[i], dest);
            }
          } else
            showMessage("You must select a valid destination directory");
        } else
          showMessage("You must select some files to copy");
      }
    });

    return composite;
  }

  /**
   * Helper method to create the label/text/button for a directory
   * 
   * @param composite the parent composite
   * @param label the text for the label
   * @return Text
   */
  private Text createFilePanelHelper(Composite composite, String label) {
    // Create the composite to hold the controls
    Composite panel = new Composite(composite, SWT.BORDER);
    panel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    panel.setLayout(new GridLayout(3, false));

    // Create the controls
    new Label(panel, SWT.LEFT).setText(label);
    Text text = new Text(panel, SWT.BORDER);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    Button browse = new Button(panel, SWT.PUSH);

    // Add browsing
    browse.setText("...");
    browse.addSelectionListener(new DirectoryBrowser(text));

    // Return the Text that holds the directory
    return text;
  }

  /**
   * Copies a file
   * 
   * @param file the file to copy
   * @param targetDir the directory to copy to
   */
  private void copyFile(File file, File targetDir) {
    BufferedInputStream in = null;
    BufferedOutputStream out = null;
    File destFile = new File(targetDir.getAbsolutePath() + File.separator
        + file.getName());
    try {
      showMessage("Copying " + file.getName());
      in = new BufferedInputStream(new FileInputStream(file));
      out = new BufferedOutputStream(new FileOutputStream(destFile));
      int n;
      while ((n = in.read()) != -1) {
        out.write(n);
      }
      showMessage("Copied " + file.getName());
    } catch (Exception e) {
      showMessage("Cannot copy file " + file.getAbsolutePath());
    } finally {
      if (in != null) try {
        in.close();
      } catch (Exception e) {}
      if (out != null) try {
        out.close();
      } catch (Exception e) {}
    }
  }

  /**
   * Shows a message
   * 
   * @param message the message
   */
  private void showMessage(String message) {
    status.setText(message);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new BackupFiles().run();
  }
}