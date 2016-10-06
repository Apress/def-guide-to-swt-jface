package examples.ch7;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates the ColorDialog class
 */
public class ChooseColor {
  private Color color;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Color Chooser");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    // Dispose the color we created for the Label
    if (color != null) {
      color.dispose();
    }
    display.dispose();
  }

  /**
   * Creates the window contents
   * 
   * @param shell the parent shell
   */
  private void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(2, false));

    // Start with Celtics green
    color = new Color(shell.getDisplay(), new RGB(0, 255, 0));

    // Use a label full of spaces to show the color
    final Label colorLabel = new Label(shell, SWT.NONE);
    colorLabel.setText("                              ");
    colorLabel.setBackground(color);

    Button button = new Button(shell, SWT.PUSH);
    button.setText("Color...");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Create the color-change dialog
        ColorDialog dlg = new ColorDialog(shell);

        // Set the selected color in the dialog from
        // user's selected color
        dlg.setRGB(colorLabel.getBackground().getRGB());

        // Change the title bar text
        dlg.setText("Choose a Color");

        // Open the dialog and retrieve the selected color
        RGB rgb = dlg.open();
        if (rgb != null) {
          // Dispose the old color, create the
          // new one, and set into the label
          color.dispose();
          color = new Color(shell.getDisplay(), rgb);
          colorLabel.setBackground(color);
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
    new ChooseColor().run();
  }
}
