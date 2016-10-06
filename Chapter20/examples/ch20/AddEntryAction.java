package examples.ch20;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;

/**
 * This class launches the add entry wizard
 */
public class AddEntryAction extends Action {
  /**
   * AddEntryAction constructor
   */
  public AddEntryAction() {
    super("Add Entry", ImageDescriptor.createFromFile(AddEntryAction.class,
        "/images/addEntry.gif"));
    setToolTipText("Add Entry");
  }

  /**
   * Runs the action
   */
  public void run() {
    WizardDialog dlg = new WizardDialog(AddressBook.getApp().getShell(),
        new AddEntryWizard());
    dlg.open();
  }
}