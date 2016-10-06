package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates FontMetrics and extents
 */
public class Extents {
  // The string to display
  private static final String STRING = "Go Celtics!";

  // The size options for the combo
  private static final String[] SIZES = { "8", "10", "12", "14", "16", "18"};

  // The font used to draw the string
  private Font font;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Extents");
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    if (font != null) font.dispose();
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(final Shell shell) {
    // Create a canvas to draw on
    final Canvas canvas = new Canvas(shell, SWT.NONE);

    // Add a listener to the shell to resize the canvas to fill the window
    // any time the window is resized
    shell.addControlListener(new ControlAdapter() {
      public void controlResized(ControlEvent event) {
        canvas.setBounds(shell.getClientArea());
      }
    });

    // Add a listener to the canvas. This is where we draw the text.
    canvas.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Set the font into the gc
        event.gc.setFont(font);

        // Calcalute the width (nad height) of the string
        Point pt = event.gc.stringExtent(STRING);

        // Figure out how big our drawing area is
        Rectangle rect = canvas.getBounds();

        // Calculate the height of the font. We could have used pt.y,
        // but this demonstrates FontMetrics
        int height = event.gc.getFontMetrics().getHeight();

        // Outside loop goes from the top of the window to the bottom.
        // Since the (x, y) passed to drawString represents the upper left
        // corner, subtract the height of the font from the height of the
        // drawing area, so we don't have any partial drawing.
        for (int i = 0, n = rect.height - height; i < n; i += height) {
          // Inside loop goes from the left to the right, stopping far enough
          // from the right to ensure no partial string drawing.
          for (int j = 0, m = rect.width - pt.x; j < m; j += pt.x) {
            // Draw the string
            event.gc.drawString(STRING, j, i);
          }
        }
      }
    });

    // Create an editor to house the dropdown
    ControlEditor editor = new ControlEditor(canvas);

    // Create the combo and fill it
    final Combo combo = new Combo(canvas, SWT.READ_ONLY);
    for (int i = 0, n = SIZES.length; i < n; i++) {
      combo.add(SIZES[i]);
    }

    // Set up the editor
    editor.horizontalAlignment = SWT.CENTER;
    editor.verticalAlignment = SWT.TOP;
    Point size = combo.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    editor.minimumWidth = size.x;
    editor.minimumHeight = size.y;
    editor.setEditor(combo);

    // Add a listener to the combo, so that when the selection changes,
    // we change the font and redraw the canvas
    combo.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        if (font != null) font.dispose();
        font = new Font(shell.getDisplay(), "Helvetica", new Integer(combo
            .getText()).intValue(), SWT.BOLD);
        canvas.redraw();
      }
    });

    // Select the first item in the combo
    combo.select(0);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new Extents().run();
  }
}
