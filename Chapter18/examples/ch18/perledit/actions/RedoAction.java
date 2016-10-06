package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action redoes the last action
 */
public class RedoAction extends Action {
  /**
   * RedoAction constructor
   */
  public RedoAction() {
    super("&Redo@Ctrl+Y", ImageDescriptor.createFromFile(RedoAction.class,
        "/images/redo.gif"));
    setToolTipText("Redo");
  }

  /**
   * Runs the action
   */
  public void run() {
    PerlEditor.getApp().redo();
  }
}
