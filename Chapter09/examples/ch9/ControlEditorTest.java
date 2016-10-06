package examples.ch9;

import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates ControlEditor
 */
public class ControlEditorTest {
  // Create a map to hold all the supported colors
  private static final Map COLORS = new HashMap();
  static {
    COLORS.put("red", new RGB(255, 0, 0));
    COLORS.put("green", new RGB(0, 255, 0));
    COLORS.put("blue", new RGB(0, 0, 255));
    COLORS.put("yellow", new RGB(255, 255, 0));
    COLORS.put("black", new RGB(0, 0, 0));
    COLORS.put("white", new RGB(255, 255, 255));
  }

  private Color color;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Control Editor");
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
    final Text text = new Text(composite, SWT.BORDER);
    text.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        RGB rgb = (RGB) COLORS.get(text.getText());
        if (rgb != null) {
          if (color != null) color.dispose();
          color = new Color(shell.getDisplay(), rgb);
          composite.setBackground(color);
        }
      }
    });

    // Place the editor in the top middle of the parent composite
    editor.horizontalAlignment = SWT.CENTER;
    editor.verticalAlignment = SWT.TOP;
    Point size = text.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    editor.minimumWidth = size.x;
    editor.minimumHeight = size.y;
    editor.setEditor(text);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ControlEditorTest().run();
  }
}
