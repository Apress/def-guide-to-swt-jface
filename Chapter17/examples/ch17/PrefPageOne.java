package examples.ch17;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class creates a preference page
 */
public class PrefPageOne extends PreferencePage {
  // Names for preferences
  private static final String ONE = "one.one";
  private static final String TWO = "one.two";
  private static final String THREE = "one.three";

  // Text fields for user to enter preferences
  private Text fieldOne;
  private Text fieldTwo;
  private Text fieldThree;

  /**
   * Creates the controls for this page
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(2, false));

    // Get the preference store
    IPreferenceStore preferenceStore = getPreferenceStore();

    // Create three text fields.
    // Set the text in each from the preference store
    new Label(composite, SWT.LEFT).setText("Field One:");
    fieldOne = new Text(composite, SWT.BORDER);
    fieldOne.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    fieldOne.setText(preferenceStore.getString(ONE));

    new Label(composite, SWT.LEFT).setText("Field Two:");
    fieldTwo = new Text(composite, SWT.BORDER);
    fieldTwo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    fieldTwo.setText(preferenceStore.getString(TWO));

    new Label(composite, SWT.LEFT).setText("Field Three:");
    fieldThree = new Text(composite, SWT.BORDER);
    fieldThree.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    fieldThree.setText(preferenceStore.getString(THREE));

    return composite;
  }

  /**
   * Called when user clicks Restore Defaults
   */
  protected void performDefaults() {
    // Get the preference store
    IPreferenceStore preferenceStore = getPreferenceStore();

    // Reset the fields to the defaults
    fieldOne.setText(preferenceStore.getDefaultString(ONE));
    fieldTwo.setText(preferenceStore.getDefaultString(TWO));
    fieldThree.setText(preferenceStore.getDefaultString(THREE));
  }

  /**
   * Called when user clicks Apply or OK
   * 
   * @return boolean
   */
  public boolean performOk() {
    // Get the preference store
    IPreferenceStore preferenceStore = getPreferenceStore();

    // Set the values from the fields
    if (fieldOne != null) preferenceStore.setValue(ONE, fieldOne.getText());
    if (fieldTwo != null) preferenceStore.setValue(TWO, fieldTwo.getText());
    if (fieldThree != null)
        preferenceStore.setValue(THREE, fieldThree.getText());

    // Return true to allow dialog to close
    return true;
  }
}
