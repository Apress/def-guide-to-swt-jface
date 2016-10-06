package examples.ch16;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

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
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(OpenAction.class,
        "/images/disabledOpen.gif"));
    setToolTipText("Open");
  }

  /**
   * Opens an existing file
   */
  public void run() {
    // Use the file dialog
    FileDialog dlg = new FileDialog(Librarian.getApp().getShell(), SWT.OPEN);
    String fileName = dlg.open();
    if (fileName != null) {
      Librarian.getApp().openFile(fileName);
    }
  }
}