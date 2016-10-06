package examples.ch14;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

/**
 * This class allows users to browse for a directory
 */
public class DirectoryBrowser extends SelectionAdapter {
  // The Text this browser is tied to
  private Text text;

  /**
   * DirectoryBrowser constructor
   * 
   * @param text
   */
  public DirectoryBrowser(Text text) {
    this.text = text;
  }

  /**
   * Called when the browse button is pushed
   * 
   * @param event the generated event
   */
  public void widgetSelected(SelectionEvent event) {
    DirectoryDialog dlg = new DirectoryDialog(Display.getCurrent()
        .getActiveShell());
    dlg.setFilterPath(text.getText());
    String dir = dlg.open();
    if (dir != null) {
      text.setText(dir);
    }
  }
}