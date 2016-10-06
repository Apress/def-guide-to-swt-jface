package examples.ch5;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.graphics.*;

/**
 * This class demonstrates Labels
 */
public class LabelExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell();
    shell.setLayout(new GridLayout(1, false));

    // Create a label
    new Label(shell, SWT.NONE).setText("This is a plain label.");

    // Create a vertical separator
    new Label(shell, SWT.SEPARATOR);

    // Create a label with a border
    new Label(shell, SWT.BORDER).setText("This is a label with a border.");

    // Create a horizontal separator
    Label separator = new Label(shell, SWT.HORIZONTAL | SWT.SEPARATOR);
    separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create a label with an image
    Image image = new Image(display, "interspatial.gif");
    Label imageLabel = new Label(shell, SWT.NONE);
    imageLabel.setImage(image);
    
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}