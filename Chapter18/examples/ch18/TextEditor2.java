package examples.ch18;

import org.eclipse.jface.text.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates TextViewer and Document. It adds a vertical scrollbar,
 * word wrap, and printing
 */
public class TextEditor2 extends ApplicationWindow {
  /**
   * TextEditor2 constructor
   */
  public TextEditor2() {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run() {
    setBlockOnOpen(true);
    open();
    Display.getCurrent().dispose();
  }

  /**
   * Configures the shell
   * 
   * @param shell the shell
   */
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText("Text Editor 2");
    shell.setSize(600, 400);
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    // Create the viewer
    TextViewer viewer = new TextViewer(parent, SWT.V_SCROLL);

    // Get the StyledText
    final StyledText styledText = viewer.getTextWidget();

    // Turn on word wrap
    styledText.setWordWrap(true);

    // Add a listener to detect Ctrl+P
    styledText.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent event) {
        if (event.keyCode == 'p' && (event.stateMask & SWT.CTRL) != 0) {
          // Ctrl+P pressed; print the document
          styledText.print();
        }
      }
    });

    // Create the associated document
    viewer.setDocument(new Document());

    // Return the StyledText
    return styledText;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new TextEditor2().run();
  }
}
