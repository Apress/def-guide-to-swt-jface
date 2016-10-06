package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action copies the current selection to the clipboard
 */
public class CopyAction extends Action {
  /**
   * CopyAction constructor
   */
  public CopyAction() {
    super("&Copy@Ctrl+C", ImageDescriptor.createFromFile(CopyAction.class,
        "/images/copy.gif"));
    setToolTipText("Copy");
  }

  /**
   * Runs the action
   */
  public void run() {
    PerlEditor.getApp().copy();
  }
}
