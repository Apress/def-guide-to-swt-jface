package examples.ch11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class implements syntax coloring using the StyledText API
 */
public class SyntaxTest {
  // Punctuation
  private static final String PUNCTUATION = "(){};!&|.+-*/";

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

    // No need to dispose red

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
    final StyledText styledText = new StyledText(shell, SWT.BORDER);

    // Add the syntax coloring handler
    styledText.addExtendedModifyListener(new ExtendedModifyListener() {
      public void modifyText(ExtendedModifyEvent event) {
        // Determine the ending offset
        int end = event.start + event.length - 1;

        // If they typed something, get it
        if (event.start <= end) {
          // Get the text
          String text = styledText.getText(event.start, end);

          // Create a collection to hold the StyleRanges
          java.util.List ranges = new java.util.ArrayList();

          // Turn any punctuation red
          for (int i = 0, n = text.length(); i < n; i++) {
            if (PUNCTUATION.indexOf(text.charAt(i)) > -1) {
              ranges.add(new StyleRange(event.start + i, 1, red, null, SWT.BOLD));
            }
          }

          // If we have any ranges to set, set them
          if (!ranges.isEmpty()) {
            styledText.replaceStyleRanges(event.start, event.length,
                (StyleRange[]) ranges.toArray(new StyleRange[0]));
          }
        }
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SyntaxTest().run();
  }
}
