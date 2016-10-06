package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action paste the contents of the clipboard into the document
 */
public class PasteAction extends Action {
  /**
   * PasteAction constructor
   */
  public PasteAction() {
    super("&Paste@Ctrl+V", ImageDescriptor.createFromFile(PasteAction.class,
        "/images/paste.gif"));
    setToolTipText("Paste");
  }

  /**
   * Runs the action
   */
  public void run() {
    PerlEditor.getApp().paste();
  }
}
