package examples.ch11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates StyleRanges
 */
public class StyleRangeTest {
  private Color orange;
  private Color blue;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);

    // Create colors for style ranges
    orange = new Color(display, 255, 127, 0);
    blue = display.getSystemColor(SWT.COLOR_BLUE);

    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }

    // We created orange, but not blue
    orange.dispose();

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
    StyledText styledText = new StyledText(shell, SWT.BORDER);

    // Set the text
    styledText.setText("Go Gators");

    /*
     * The multiple setStyleRange() method // Turn all of the text orange, with
     * the default background color styledText.setStyleRange(new StyleRange(0, 9,
     * orange, null));
     *  // Turn "Gators" blue styledText.setStyleRange(new StyleRange(3, 6, blue,
     * null));
     */

    /*
     * The setStyleRanges() method // Create the array to hold the StyleRanges
     * StyleRange[] ranges = new StyleRange[2];
     *  // Create the first StyleRange, making sure not to overlap. Include the
     * space. ranges[0] = new StyleRange(0, 3, orange, null);
     *  // Create the second StyleRange ranges[1] = new StyleRange(3, 6, blue,
     * null);
     *  // Replace all the StyleRanges for the StyledText
     * styledText.setStyleRanges(ranges);
     */

    /* The replaceStyleRanges() method */
    // Create the array to hold the StyleRanges
    StyleRange[] ranges = new StyleRange[2];

    // Create the first StyleRange, making sure not to overlap. Include the
    // space.
    ranges[0] = new StyleRange(0, 3, orange, null);

    // Create the second StyleRange
    ranges[1] = new StyleRange(3, 6, blue, null);

    // Replace only the StyleRanges in the affected area
    styledText.replaceStyleRanges(0, 9, ranges);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new StyleRangeTest().run();
  }
}
