package examples.ch20;

import org.eclipse.jface.wizard.Wizard;

/**
 * This class shows a satisfaction survey
 */
public class SurveyWizard extends Wizard {
  public SurveyWizard() {
    // Add the pages
    addPage(new ComplaintsPage());
    addPage(new MoreInformationPage());
    addPage(new ThanksPage());
  }

  /**
   * Called when user clicks Finish
   * 
   * @return boolean
   */
  public boolean performFinish() {
    // Dismiss the wizard
    return true;
  }
}