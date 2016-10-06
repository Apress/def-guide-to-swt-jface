package examples.ch20;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * This page displays a welcome message
 */
public class WelcomePage extends WizardPage {
  /**
   * WelcomePage constructor
   */
  protected WelcomePage() {
    super("Welcome", "Welcome", ImageDescriptor.createFromFile(WelcomePage.class,
        "/images/welcome.gif"));
    setDescription("Welcome to the Address Book Entry Wizard");
  }

  /**
   * Creates the page contents
   * 
   * @param parent the parent composite
   */
  public void createControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new FillLayout(SWT.VERTICAL));
    new Label(composite, SWT.CENTER)
        .setText("Welcome to the Address Book Entry Wizard!");
    new Label(composite, SWT.LEFT)
        .setText("This wizard guides you through creating an Address Book entry.");
    new Label(composite, SWT.LEFT).setText("Click Next to continue.");
    setControl(composite);
  }
}