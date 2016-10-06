package examples.ch20;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This page collects the e-mail address
 */
public class EmailPage extends WizardPage {
  // The e-mail address
  private String email = "";

  /**
   * EmailPage constructor
   */
  public EmailPage() {
    super("E-mail", "E-mail Address", ImageDescriptor.createFromFile(
        EmailPage.class, "/images/email.gif"));
    setDescription("Enter the e-mail address");

    // Page isn't complete until an e-mail address has been added
    setPageComplete(false);
  }

  /**
   * Creates the contents of the page
   * 
   * @param parent the parent composite
   */
  public void createControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(2, false));

    // Create the label and text box to hold email address
    new Label(composite, SWT.LEFT).setText("E-mail Address:");
    final Text ea = new Text(composite, SWT.BORDER);
    ea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Add handler to update e-mail based on input
    ea.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        email = ea.getText();
        setPageComplete(email.length() > 0);
      }
    });

    setControl(composite);
  }

  /**
   * Gets the e-mail
   * 
   * @return String
   */
  public String getEmail() {
    return email;
  }
}