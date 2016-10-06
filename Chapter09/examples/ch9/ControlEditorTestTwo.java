package examples.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates ControlEditor
 */
public class ControlEditorTestTwo {
  private Color color;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Control Editor Two");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    if (color != null) color.dispose();
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(final Shell shell) {
    color = new Color(shell.getDisplay(), 255, 0, 0);

    // Create a composite that will be the parent of the editor
    final Composite composite = new Composite(shell, SWT.NONE);
    composite.setBackground(color);
    composite.setBounds(0, 0, 300, 100);

    // Create the editor
    ControlEditor editor = new ControlEditor(composite);

    // Create the control associated with the editor
    Button button = new Button(composite, SWT.PUSH);
    button.setText("Change Color...");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ColorDialog dialog = new ColorDialog(shell);
        if (color != null) dialog.setRGB(color.getRGB());
        RGB rgb = dialog.open();
        if (rgb != null) {
          if (color != null) color.dispose();
          color = new Color(shell.getDisplay(), rgb);
          composite.setBackground(color);
        }
      }
    });

    // Place the editor along the bottom of the parent composite
    editor.grabHorizontal = true;
    editor.verticalAlignment = SWT.BOTTOM;
    Point size = button.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    editor.minimumHeight = size.y;
    editor.setEditor(button);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ControlEditorTestTwo().run();
  }
}
