package examples.ch18.perledit.actions;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import examples.ch18.perledit.*;

/**
 * This action class responds to requests open a file
 */
public class OpenAction extends Action {
  /**
   * OpenAction constructor
   */
  public OpenAction() {
    super("&Open...@Ctrl+O", ImageDescriptor.createFromFile(OpenAction.class,
        "/images/open.gif"));
    setToolTipText("Open");
  }

  /**
   * Opens an existing file
   */
  public void run() {
    // Use the file dialog
    FileDialog dlg = new FileDialog(PerlEditor.getApp().getMainWindow()
        .getShell(), SWT.OPEN);
    dlg.setFilterNames(Constants.FILTER_NAMES);
    dlg.setFilterExtensions(Constants.FILTER_EXTENSIONS);
    String fileName = dlg.open();
    if (fileName != null) {
      PerlEditor.getApp().openFile(fileName);
    }
  }
}
