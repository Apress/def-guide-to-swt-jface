package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates drawing ovals
 */
public class OvalExample {
  private Text txtWidth = null;
  private Text txtHeight = null;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Oval Example");
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

    // Create the button that launches the redraw
    Button button = new Button(widgetComposite, SWT.PUSH);
    button.setText("Redraw");
    shell.setDefaultButton(button);

    // Create the canvas to draw the oval on
    final Canvas drawingCanvas = new Canvas(shell, SWT.NONE);
    drawingCanvas.addPaintListener(new OvalExamplePaintListener());

    // Add a handler to redraw the oval when pressed
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        drawingCanvas.redraw();
      }
    });
  }

  /**
   * This class gets the user input and draws the requested oval
   */
  private class OvalExamplePaintListener implements PaintListener {
    public void paintControl(PaintEvent e) {
      // Get the canvas for drawing and its width and height
      Canvas canvas = (Canvas) e.widget;
      int x = canvas.getBounds().width;
      int y = canvas.getBounds().height;

      // Determine user input, defaulting everything to zero.
      // Any blank fields are converted to zero
      int width = 0;
      int height = 0;
      try {
        width = txtWidth.getText().length() == 0 ? 0 : Integer.parseInt(txtWidth
            .getText());
        height = txtHeight.getText().length() == 0 ? 0 : Integer
            .parseInt(txtHeight.getText());
      } catch (NumberFormatException ex) {
        // Any problems, set them both to zero
        width = 0;
        height = 0;
      }

      // Set the drawing width for the oval
      e.gc.setLineWidth(4);

      // Draw the requested oval
      e.gc.drawOval((x - width) / 2, (y - height) / 2, width, height);
    }
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new OvalExample().run();
  }
}