package examples.ch20;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This page collects the first and last names
 */
public class NamePage extends WizardPage {
  // The first and last names
  private String firstName = "";
  private String lastName = "";

  /**
   * NamePage constructor
   */
  public NamePage() {
    super("Name", "Name", ImageDescriptor.createFromFile(NamePage.class,
        "/images/name.gif"));
    setDescription("Enter the first and last names");
    setPageComplete(false);
  }

  /**
   * Creates the page contents
   * 
   * @param parent the parent composite
   */
  public void createControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(2, false));

    // Create the label and text field for first name
    new Label(composite, SWT.LEFT).setText("First Name:");
    final Text first = new Text(composite, SWT.BORDER);
    first.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the label and text field for last name
    new Label(composite, SWT.LEFT).setText("Last Name:");
    final Text last = new Text(composite, SWT.BORDER);
    last.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Add the handler to update the first name based on input
    first.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        firstName = first.getText();
        setPageComplete(firstName.length() > 0 && lastName.length() > 0);
      }
    });

    // Add the handler to update the last name based on input
    last.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        lastName = last.getText();
        setPageComplete(firstName.length() > 0 && lastName.length() > 0);
      }
    });

    setControl(composite);
  }

  /**
   * Gets the first name
   * 
   * @return String
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the last name
   * 
   * @return String
   */
  public String getLastName() {
    return lastName;
  }
}