package examples.ch17;

import java.io.IOException;

import org.eclipse.jface.preference.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates JFace preferences
 */
public class ShowPrefs {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();

    // Create the preference manager
    PreferenceManager mgr = new PreferenceManager();

    // Create the nodes
    PreferenceNode one = new PreferenceNode("one", "One", ImageDescriptor
        .createFromFile(ShowPrefs.class, "/images/about.gif"), PrefPageOne.class
        .getName());
    PreferenceNode two = new PreferenceNode("two", new PrefPageTwo());

    // Add the nodes
    mgr.addToRoot(one);
    mgr.addTo(one.getId(), two);

    // Create the preferences dialog
    PreferenceDialog dlg = new PreferenceDialog(null, mgr);

    // Set the preference store
    PreferenceStore ps = new PreferenceStore("showprefs.properties");
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
    new ShowPrefs().run();
  }
}
