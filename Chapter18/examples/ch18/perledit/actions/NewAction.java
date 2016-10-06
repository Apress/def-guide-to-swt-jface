package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action class reponds to requests for a new file
 */
public class NewAction extends Action {
  /**
   * NewAction constructor
   */
  public NewAction() {
    super("&New@Ctrl+N", ImageDescriptor.createFromFile(NewAction.class,
        "/images/new.gif"));
    setToolTipText("New");
  }

  /**
   * Creates a new file
   */
  public void run() {
    PerlEditor.getApp().newFile();
  }
}
