package examples.ch17;

import java.io.IOException;

import org.eclipse.jface.preference.PreferenceStore;

/**
 * This class demonstrates PreferenceStore
 */
public class PreferenceStoreTest {
  public static void main(String[] args) throws IOException {
    // Create the preference store
    PreferenceStore preferenceStore = new PreferenceStore("foo.properties");

    // Load it
    preferenceStore.load();

    // Set some defaults
    preferenceStore.setDefault("name1", true);
    preferenceStore.setDefault("name2", 42);
    preferenceStore.setDefault("name3", "Stack");

    // List the preferences
    preferenceStore.list(System.out);
  }
}
