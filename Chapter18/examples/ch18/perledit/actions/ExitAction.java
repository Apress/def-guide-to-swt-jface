package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;

import examples.ch18.perledit.PerlEditor;

/**
 * This action class exits the application
 */
public class ExitAction extends Action {
  /**
   * ExitAction constructor
   */
  public ExitAction() {
    super("E&xit@Alt+F4");
    setToolTipText("Exit");
  }

  /**
   * Exits the application
   */
  public void run() {
    PerlEditor.getApp().getMainWindow().close();
  }
}
