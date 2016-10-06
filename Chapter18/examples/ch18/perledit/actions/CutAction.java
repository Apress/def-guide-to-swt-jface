package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action cuts the current selection to the clipboard
 */
public class CutAction extends Action {
  /**
   * CutAction constructor
   */
  public CutAction() {
    super("Cu&t@Ctrl+X", ImageDescriptor.createFromFile(CutAction.class,
        "/images/cut.gif"));
    setToolTipText("Cut");
  }

  /**
   * Runs the action
   */
  public void run() {
    PerlEditor.getApp().cut();
  }
}
