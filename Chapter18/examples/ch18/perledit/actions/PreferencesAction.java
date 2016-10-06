package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.*;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.*;
import examples.ch18.perledit.preferences.*;

/**
 * This action displays the preferences dialog
 */
public class PreferencesAction extends Action {
  /**
   * PreferencesAction constructor
   */
  public PreferencesAction() {
    super("P&references...@Ctrl+R", ImageDescriptor.createFromFile(
        PreferencesAction.class, "/images/prefs.gif"));
    setToolTipText("Preferences");
  }

  /**
   * Runs the action
   */
  public void run() {
    PreferenceManager mgr = new PreferenceManager();
    mgr.addToRoot(new PreferenceNode("text", "Text", null,
        TextPreferencePage.class.getName()));

    PreferenceDialog dlg = new PreferenceDialog(PerlEditor.getApp()
        .getMainWindow().getShell(), mgr);
    dlg.setPreferenceStore(PerlEditor.getApp().getPreferences());
    dlg.open();
  }
}
