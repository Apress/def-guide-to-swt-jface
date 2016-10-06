package examples.ch20;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.*;

/**
 * This class displays a survey using a wizard
 */
public class Survey {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();

    // Create the parent shell for the dialog, but don't show it
    Shell shell = new Shell(display);

    // Create the dialog
    WizardDialog dlg = new WizardDialog(shell, new SurveyWizard());
    dlg.open();

    // Dispose the display
    display.dispose();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new Survey().run();
  }
}