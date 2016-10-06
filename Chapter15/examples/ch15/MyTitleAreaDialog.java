package examples.ch15;

import java.io.*;

import org.eclipse.jface.dialogs.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class shows an about box, based on TitleAreaDialog
 */
public class MyTitleAreaDialog extends TitleAreaDialog {
  // The image to display
  private Image image;

  /**
   * MyTitleAreaDialog constructor
   * 
   * @param shell the parent shell
   */
  public MyTitleAreaDialog(Shell shell) {
    super(shell);

    // Create the image
    try {
      image = new Image(null, new FileInputStream("images/jface.gif"));
    } catch (FileNotFoundException e) {
      // Ignore
    }
  }

  /**
   * Closes the dialog box Override so we can dispose the image we created
   */
  public boolean close() {
    if (image != null) image.dispose();
    return super.close();
  }

  /**
   * Creates the dialog's contents
   * 
   * @param parent the parent composite
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Control contents = super.createContents(parent);

    // Set the title
    setTitle("About This Application");

    // Set the message
    setMessage("This is a JFace dialog", IMessageProvider.INFORMATION);

    // Set the image
    if (image != null) setTitleImage(image);

    return contents;
  }

  /**
   * Creates the gray area
   * 
   * @param parent the parent composite
   * @return Control
   */
  protected Control createDialogArea(Composite parent) {
    Composite composite = (Composite) super.createDialogArea(parent);

    // Create a table
    Table table = new Table(composite, SWT.FULL_SELECTION | SWT.BORDER);
    table.setLayoutData(new GridData(GridData.FILL_BOTH));

    // Create two columns and show
    TableColumn one = new TableColumn(table, SWT.LEFT);
    one.setText("Real Name");

    TableColumn two = new TableColumn(table, SWT.LEFT);
    two.setText("Preferred Name");

    table.setHeaderVisible(true);

    // Add some data
    TableItem item = new TableItem(table, SWT.NONE);
    item.setText(0, "Robert Harris");
    item.setText(1, "Bobby");

    item = new TableItem(table, SWT.NONE);
    item.setText(0, "Robert Warner");
    item.setText(1, "Rob");

    item = new TableItem(table, SWT.NONE);
    item.setText(0, "Gabor Liptak");
    item.setText(1, "Gabor");

    one.pack();
    two.pack();

    return composite;
  }

  /**
   * Creates the buttons for the button bar
   * 
   * @param parent the parent composite
   */
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
  }
}