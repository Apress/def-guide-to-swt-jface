package examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import examples.ch18.perledit.PerlEditor;
import examples.ch18.perledit.ui.FindReplaceDialog;

/**
 * This action searches text
 */
public class FindAction extends Action {
  /**
   * FindAction constructor
   */
  public FindAction() {
    super("&Find@Ctrl+F", ImageDescriptor.createFromFile(FindAction.class,
        "/images/find.gif"));
    setToolTipText("Find");
  }

  /**
   * Runs the action
   */
  public void run() {
    FindReplaceDialog dlg = new FindReplaceDialog(PerlEditor.getApp()
        .getMainWindow().getShell(), PerlEditor.getApp().getDocument(),
        PerlEditor.getApp().getMainWindow().getViewer());
    dlg.open();
  }
}
