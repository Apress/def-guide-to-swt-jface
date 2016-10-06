package examples.ch16;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.*;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This action class deletes a book
 */
public class RemoveBookAction extends Action {
  /**
   * RemoveBookAction constructor
   */
  public RemoveBookAction() {
    super("&Remove Book@Ctrl+X", ImageDescriptor.createFromFile(
        RemoveBookAction.class, "/images/removeBook.gif"));
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(
        RemoveBookAction.class, "/images/disabledRemoveBook.gif"));
    setToolTipText("Remove");
  }

  /**
   * Removes the selected book after confirming
   */
  public void run() {
    if (MessageDialog.openConfirm(Librarian.getApp().getShell(), "Are you sure?",
        "Are you sure you want to remove the selected book?")) {
      Librarian.getApp().removeSelectedBook();
    }
  }
}