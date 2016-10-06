package examples.ch13;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * Your first JFace application
 */
public class HelloWorld extends ApplicationWindow {
  /**
   * HelloWorld constructor
   */
  public HelloWorld() {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run() {
    // Don't return from open() until window closes
    setBlockOnOpen(true);

    // Open the main window
    open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    // Create a Hello, World label
    Label label = new Label(parent, SWT.CENTER);
    label.setText("Hello, World");
    return label;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new HelloWorld().run();
  }
}
