package examples.ch15;

import java.io.*;

import org.eclipse.jface.dialogs.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates the IconAndMessageDialog class
 */
public class DumbMessageDialog extends IconAndMessageDialog {
  public static final int I_DUNNO_ID = IDialogConstants.CLIENT_ID;
  public static final String I_DUNNO_LABEL = "I Dunno";

  // The image
  private Image image;

  // The label for the "hidden" message
  private Label label;

  /**
   * DumbMessageDialog constructor
   * 
   * @param parent the parent shell
   */
  public DumbMessageDialog(Shell parent) {
    super(parent);

    // Create the image
    try {
      image = new Image(parent.getDisplay(), new FileInputStream(
          "images/loser.gif"));
    } catch (FileNotFoundException e) {}

    // Set the default message
    message = "Are you sure you want to do something that dumb?";
  }

  /**
   * Sets the message
   * 
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Closes the dialog
   * 
   * @return boolean
   */
  public boolean close() {
    if (image != null) image.dispose();
    return super.close();
  }

  /**
   * Creates the dialog area
   * 
   * @param parent the parent composite
   * @return Control
   */
  protected Control createDialogArea(Composite parent) {
    createMessageArea(parent);

    // Create a composite to hold the label
    Composite composite = new Composite(parent, SWT.NONE);
    GridData data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 2;
    composite.setLayoutData(data);
    composite.setLayout(new FillLayout());

    // Create the label for the "hidden" message
    label = new Label(composite, SWT.LEFT);

    return composite;
  }

  /**
   * Creates the buttons
   * 
   * @param parent the parent composite
   */
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.YES_ID, IDialogConstants.YES_LABEL,
        true);
    createButton(parent, IDialogConstants.NO_ID, IDialogConstants.NO_LABEL, false);
    createButton(parent, I_DUNNO_ID, I_DUNNO_LABEL, false);
  }

  /**
   * Handles a button press
   * 
   * @param buttonId the ID of the pressed button
   */
  protected void buttonPressed(int buttonId) {
    // If they press I Dunno, close the dialog
    if (buttonId == I_DUNNO_ID) {
      setReturnCode(buttonId);
      close();
    } else {
      // Otherwise, have some fun
      label.setText("Yeah, right. You know nothing.");
    }
  }

  /**
   * Gets the image to use
   */
  protected Image getImage() {
    return image;
  }
}