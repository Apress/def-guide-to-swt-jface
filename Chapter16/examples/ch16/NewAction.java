package examples.ch16;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This action class reponds to requests for a new file
 */
public class NewAction extends Action {
  /**
   * NewAction constructor
   */
  public NewAction() {
    super("&New@Ctrl+N", ImageDescriptor.createFromFile(NewAction.class,
        "/images/new.gif"));
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,
        "/images/disabledNew.gif"));
    setToolTipText("New");
  }

  /**
   * Creates a new file
   */
  public void run() {
    Librarian.getApp().newFile();
  }
}