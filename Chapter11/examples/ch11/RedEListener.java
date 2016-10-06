package examples.ch11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class turns 'e' characters red using a LineStyleListener
 */
public class RedEListener {
  // Color for the StyleRanges
  private Color red;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);

    // Get color for style ranges
    red = display.getSystemColor(SWT.COLOR_RED);

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

    // Create the StyledText
    final StyledText styledText = new StyledText(shell, SWT.BORDER | SWT.H_SCROLL
        | SWT.V_SCROLL);

    // Add the syntax coloring handler
    styledText.addLineStyleListener(new LineStyleListener() {
      public void lineGetStyle(LineStyleEvent event) {
        // Create a collection to hold the StyleRanges
        java.util.List styles = new java.util.ArrayList();

        // Iterate through the text
        for (int i = 0, n = event.lineText.length(); i < n; i++) {
          // Check for 'e'
          if (event.lineText.charAt(i) == 'e') {
            // Found an 'e'; combine all subsequent e's into the same StyleRange
            int start = i;
            for (; i < n && event.lineText.charAt(i) == 'e'; i++);

            // Create the StyleRange and add it to the collection
            styles.add(new StyleRange(event.lineOffset + start, i - start, red,
                null));
          }
        }
        // Set the styles for the line
        event.styles = (StyleRange[]) styles.toArray(new StyleRange[0]);
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new RedEListener().run();
  }
}
