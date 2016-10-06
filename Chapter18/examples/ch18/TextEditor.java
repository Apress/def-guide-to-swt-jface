package examples.ch18;

import org.eclipse.jface.text.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates TextViewer and Document
 */
public class TextEditor extends ApplicationWindow {
  /**
   * TextEditor constructor
   */
  public TextEditor() {
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
    shell.setText("Text Editor");
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
    TextViewer viewer = new TextViewer(parent, SWT.NONE);

    // Create the associated document
    viewer.setDocument(new Document());

    // Return the StyledText
    return viewer.getTextWidget();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new TextEditor().run();
  }
}
