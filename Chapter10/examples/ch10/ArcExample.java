package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates drawing an Arc
 */
public class ArcExample {
  private Text txtWidth = null;
  private Text txtHeight = null;
  private Text txtBeginAngle = null;
  private Text txtAngle = null;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Arc Example");
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
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    shell.setLayout(new FillLayout(SWT.VERTICAL));

    // Create the composite that holds the input fields
    Composite widgetComposite = new Composite(shell, SWT.NONE);
    widgetComposite.setLayout(new GridLayout(2, false));

    // Create the input fields
    new Label(widgetComposite, SWT.NONE).setText("Width:");
    txtWidth = new Text(widgetComposite, SWT.BORDER);

    new Label(widgetComposite, SWT.NONE).setText("Height");
    txtHeight = new Text(widgetComposite, SWT.BORDER);

    new Label(widgetComposite, SWT.NONE).setText("Begin Angle:");
    txtBeginAngle = new Text(widgetComposite, SWT.BORDER);

    new Label(widgetComposite, SWT.NONE).setText("Angle:");
    txtAngle = new Text(widgetComposite, SWT.BORDER);

    // Create the button that launches the redraw
    Button button = new Button(widgetComposite, SWT.PUSH);
    button.setText("Redraw");
    shell.setDefaultButton(button);

    // Create the canvas to draw the arc on
    final Canvas drawingCanvas = new Canvas(shell, SWT.NONE);
    drawingCanvas.addPaintListener(new ArcExamplePaintListener());

    // Add a handler to redraw the arc when pressed
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        drawingCanvas.redraw();
      }
    });
  }

  /**
   * This class gets the user input and draws the requested arc
   */
  private class ArcExamplePaintListener implements PaintListener {
    public void paintControl(PaintEvent e) {
      // Get the canvas for drawing and its dimensions
      Canvas canvas = (Canvas) e.widget;
      int x = canvas.getBounds().width;
      int y = canvas.getBounds().height;

      // Determine user input, defaulting everything to zero.
      // Any blank fields are converted to zero
      int width = 0;
      int height = 0;
      int begin = 0;
      int angle = 0;

      try {
        width = txtWidth.getText().length() == 0 ? 0 : Integer.parseInt(txtWidth
            .getText());
        height = txtHeight.getText().length() == 0 ? 0 : Integer
            .parseInt(txtHeight.getText());
        begin = txtBeginAngle.getText().length() == 0 ? 0 : Integer
            .parseInt(txtBeginAngle.getText());
        angle = txtAngle.getText().length() == 0 ? 0 : Integer.parseInt(txtAngle
            .getText());
      } catch (NumberFormatException ex) {
        // Any problems, reset them all to zero
        width = 0;
        height = 0;
        begin = 0;
        angle = 0;
      }
      // Set the drawing color to black
      e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));

      // Draw the arc, centered on the canvas
      e.gc
          .fillArc((x - width) / 2, (y - height) / 2, width, height, begin, angle);
    }
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ArcExample().run();
  }
}
