package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action class shows an About box
 */
public class AboutAction extends Action {
  /**
   * AboutAction constructor
   */
  public AboutAction() {
    super("&About@Ctrl+A", ImageDescriptor.createFromFile(AboutAction.class,
        "/images/about.gif"));
    setToolTipText("About");
  }

  /**
   * Shows an about box
   */
  public void run() {
    MessageDialog.openInformation(PerlEditor.getApp().getMainWindow().getShell(),
        "About", "Perl Editor--a Perl source code editor");
  }
}
