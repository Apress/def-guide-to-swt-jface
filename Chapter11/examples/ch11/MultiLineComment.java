package examples.ch11;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This program demonstrates multiline comments. It uses MultiLineCommentListener
 * to do the syntax coloring
 */
public class MultiLineComment {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Multiline Comments");
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Creates the main window contents
   * 
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    shell.setLayout(new FillLayout());
    final StyledText styledText = new StyledText(shell, SWT.BORDER | SWT.H_SCROLL
        | SWT.V_SCROLL);

    // Add the line style listener
    final MultiLineCommentListener lineStyleListener = new MultiLineCommentListener();
    styledText.addLineStyleListener(lineStyleListener);

    // Add the modification listener
    styledText.addExtendedModifyListener(new ExtendedModifyListener() {
      public void modifyText(ExtendedModifyEvent event) {
        // Recalculate the comments
        lineStyleListener.refreshMultilineComments(styledText.getText());

        // Redraw the text
        styledText.redraw();
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new MultiLineComment().run();
  }
}
