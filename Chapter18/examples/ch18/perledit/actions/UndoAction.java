package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action undoes the last action
 */
public class UndoAction extends Action {
  /**
   * UndoAction constructor
   */
  public UndoAction() {
    super("&Undo@Ctrl+Z", ImageDescriptor.createFromFile(UndoAction.class,
        "/images/undo.gif"));
    setToolTipText("Undo");
  }

  /**
   * Runs the action
   */
  public void run() {
    PerlEditor.getApp().undo();
  }
}
