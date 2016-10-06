package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.*;
import examples.ch18.perledit.ui.SafeSaveDialog;

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
    setToolTipText("Save As");
  }

  /**
   * Saves the file
   */
  public void run() {
    SafeSaveDialog dlg = new SafeSaveDialog(PerlEditor.getApp().getMainWindow()
        .getShell());
    dlg.setFilterNames(Constants.FILTER_NAMES);
    dlg.setFilterExtensions(Constants.FILTER_EXTENSIONS);
    String fileName = dlg.open();
    if (fileName != null) {
      PerlEditor.getApp().saveFileAs(fileName);
    }
  }
}
