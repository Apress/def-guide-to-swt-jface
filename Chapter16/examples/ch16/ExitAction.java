package examples.ch16;

import org.eclipse.jface.action.Action;

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
    Librarian.getApp().close();
  }
}