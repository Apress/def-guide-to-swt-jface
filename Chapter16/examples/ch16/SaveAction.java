package examples.ch16;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This action class responds to requests to save a file
 */
public class SaveAction extends Action {
  /**
   * SaveAction constructor
   */
  public SaveAction() {
    super("&Save@Ctrl+S", ImageDescriptor.createFromFile(SaveAction.class,
        "/images/save.gif"));
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(SaveAction.class,
        "/images/disabledSave.gif"));
    setToolTipText("Save");
  }

  /**
   * Saves the file
   */
  public void run() {
    Librarian.getApp().saveFile();
  }
}