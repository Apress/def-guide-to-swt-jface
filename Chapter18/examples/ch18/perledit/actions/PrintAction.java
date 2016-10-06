package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;

/**
 * This action class prints the document
 */
public class PrintAction extends Action {
  /**
   * PrintAction constructor
   */
  public PrintAction() {
    super("&Print...@Ctrl+P", ImageDescriptor.createFromFile(PrintAction.class,
        "/images/print.gif"));
    setToolTipText("Print");
  }

  /**
   * Prints the document
   */
  public void run() {
    PerlEditor.getApp().print();
  }
}
