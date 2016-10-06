package examples.ch17;

import java.io.IOException;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates JFace preferences and field editors
 */
public class ShowFieldPrefs {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();

    // Create the preference manager
    PreferenceManager mgr = new PreferenceManager();

    // Create the nodes
    PreferenceNode one = new PreferenceNode("one", "One", null,
        FieldEditorPageOne.class.getName());
    PreferenceNode two = new PreferenceNode("two", "Two", null,
        FieldEditorPageTwo.class.getName());

    // Add the nodes
    mgr.addToRoot(one);
    mgr.addToRoot(two);

    // Create the preferences dialog
    PreferenceDialog dlg = new PreferenceDialog(null, mgr);

    // Set the preference store
    PreferenceStore ps = new PreferenceStore("showfieldprefs.properties");
    try {
      ps.load();
    } catch (IOException e) {
      // Ignore
    }
    dlg.setPreferenceStore(ps);

    // Open the dialog
    dlg.open();

    try {
      // Save the preferences
      ps.save();
    } catch (IOException e) {
      e.printStackTrace();
    }
    display.dispose();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ShowFieldPrefs().run();
  }
}
