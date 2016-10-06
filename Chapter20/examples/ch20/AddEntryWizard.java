package examples.ch20;

import org.eclipse.jface.wizard.Wizard;

/**
 * This class represents the wizard for adding entries to the address book
 */
public class AddEntryWizard extends Wizard {
  // The pages in the wizard
  private WelcomePage welcomePage;
  private NamePage namePage;
  private EmailPage emailPage;

  /**
   * AddEntryWizard constructor
   */
  public AddEntryWizard() {
    // Create the pages
    welcomePage = new WelcomePage();
    namePage = new NamePage();
    emailPage = new EmailPage();

    // Add the pages to the wizard
    addPage(welcomePage);
    addPage(namePage);
    addPage(emailPage);

    // Set the dialog window title
    setWindowTitle("Address Book Entry Wizard");
  }

  /**
   * Called when user clicks Finish Creates the entry in the address book
   */
  public boolean performFinish() {
    // Create the entry based on the inputs
    AddressEntry entry = new AddressEntry();
    entry.setFirstName(namePage.getFirstName());
    entry.setLastName(namePage.getLastName());
    entry.setEmail(emailPage.getEmail());

    AddressBook.getApp().add(entry);

    // Return true to exit wizard
    return true;
  }
}