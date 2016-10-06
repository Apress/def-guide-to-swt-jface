package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

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
    setToolTipText("Save");
  }

  /**
   * Saves the file
   */
  public void run() {
    PerlEditor.getApp().saveFile();
  }
}
