package examples.ch20;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This page requests a string of consonants
 */
public class ConsonantPage extends WizardPage {
  /**
   * ConsonantPage constructor
   */
  public ConsonantPage() {
    // Set the name, title, and image
    super("Consonant", "Consonant", ImageDescriptor.createFromFile(
        ConsonantPage.class, "/images/consonant.gif"));

    // Set the description
    setDescription("Enter a string of consonants");
  }

  /**
   * Creates the controls
   * 
   * @param parent the parent composite
   */
  public void createControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(2, false));

    // Add the label and entry field
    new Label(composite, SWT.LEFT).setText("Consonants:");
    Text text = new Text(composite, SWT.BORDER);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Add a listener to detect when text changes, so we can check for vowels
    text.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        String s = ((Text) event.widget).getText().toUpperCase();
        if (s.length() > 0
            && (s.indexOf('A') != -1 || s.indexOf('E') != -1
                || s.indexOf('I') != -1 || s.indexOf('O') != -1 || s.indexOf('U') != -1)) {
          setErrorMessage("You must enter only consonants");
        } else {
          setErrorMessage(null);
        }
      }
    });

    setControl(composite);
  }

  /**
   * Displays the help
   */
  public void performHelp() {
    MessageDialog.openInformation(getWizard().getContainer().getShell(),
        "Consonant Help", "Enter consonants in the text box");
  }
}