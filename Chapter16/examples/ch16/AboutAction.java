package examples.ch16;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;

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
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(AboutAction.class,
        "/images/disabledAbout.gif"));
    setToolTipText("About");
  }

  /**
   * Shows an about box
   */
  public void run() {
    MessageDialog.openInformation(Librarian.getApp().getShell(), "About",
        "Librarian--to manage your books");
  }
}