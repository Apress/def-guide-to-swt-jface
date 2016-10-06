package examples.ch16;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This action class responds to requests to save a file as . . .
 */
public class SaveAsAction extends Action {
  /**
   * SaveAsAction constructor
   */
  public SaveAsAction() {
    super("Save As...", ImageDescriptor.createFromFile(SaveAsAction.class,
        "/images/saveAs.gif"));
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(SaveAsAction.class,
        "/images/disabledSaveAs.gif"));
    setToolTipText("Save As");
  }

  /**
   * Saves the file
   */
  public void run() {
    SafeSaveDialog dlg = new SafeSaveDialog(Librarian.getApp().getShell());
    String fileName = dlg.open();
    if (fileName != null) {
      Librarian.getApp().saveFileAs(fileName);
    }
  }
}