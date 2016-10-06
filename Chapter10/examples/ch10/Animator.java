package examples.ch10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates animation.
 */
public class Animator {
  // The width (and height) of the image
  private static final int IMAGE_WIDTH = 100;

  // The timer interval in milliseconds
  private static final int TIMER_INTERVAL = 10;

  // The location of the "ball"
  private int x = 0;
  private int y = 0;

  // The direction the "ball" is moving
  private int directionX = 1;
  private int directionY = 1;

  // We draw everything on this canvas
  private Canvas canvas;

  /**
   * Runs the application
   */
  public void run() {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Animator");
    createContents(shell);
    shell.open();

    // Set up the timer for the animation
    Runnable runnable = new Runnable() {
      public void run() {
        animate();
        display.timerExec(TIMER_INTERVAL, this);
      }
    };

    // Launch the timer
    display.timerExec(TIMER_INTERVAL, runnable);

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }

    // Kill the timer
    display.timerExec(-1, runnable);
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(final Shell shell) {
    shell.setLayout(new FillLayout());

    // Create the canvas for drawing
    canvas = new Canvas(shell, SWT.NO_BACKGROUND);
    canvas.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Draw the background
        event.gc.fillRectangle(canvas.getBounds());

        // Set the color of the ball
        event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));

        // Draw the ball
        event.gc.fillOval(x, y, IMAGE_WIDTH, IMAGE_WIDTH);
      }
    });
  }

  /**
   * Animates the next frame
   */
  public void animate() {
    // Determine the ball's location
    x += directionX;
    y += directionY;

    // Determine out of bounds
    Rectangle rect = canvas.getClientArea();
    if (x < 0) {
      x = 0;
      directionX = 1;
    } else if (x > rect.width - IMAGE_WIDTH) {
      x = rect.width - IMAGE_WIDTH;
      directionX = -1;
    }
    if (y < 0) {
      y = 0;
      directionY = 1;
    } else if (y > rect.height - IMAGE_WIDTH) {
      y = rect.height - IMAGE_WIDTH;
      directionY = -1;
    }

    // Force a redraw
    canvas.redraw();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new Animator().run();
  }
}
