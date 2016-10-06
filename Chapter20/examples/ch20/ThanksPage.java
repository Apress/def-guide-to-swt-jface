package examples.ch20;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This page thanks the user for taking the survey
 */
public class ThanksPage extends WizardPage {
  /**
   * ThanksPage constructor
   */
  public ThanksPage() {
    super("Thanks");
  }

  /**
   * Creates the controls for this page
   */
  public void createControl(Composite parent) {
    Label label = new Label(parent, SWT.CENTER);
    label.setText("Thanks!");
    setControl(label);
  }
}