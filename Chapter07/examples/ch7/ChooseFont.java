package examples.ch7;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates the FontDialog class
 */
public class ChooseFont {
  private Font font;
  private Color color;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Font Chooser");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    // Dispose the font and color we created
    if (font != null) font.dispose();
    if (color != null) color.dispose();

    display.dispose();
  }

  /**
   * Creates the window contents
   * 
   * @param shell the parent shell
   */
  private void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(2, false));

    final Label fontLabel = new Label(shell, SWT.NONE);
    fontLabel.setText("The selected font");

    Button button = new Button(shell, SWT.PUSH);
    button.setText("Font...");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Create the color-change dialog
        FontDialog dlg = new FontDialog(shell);

        // Pre-fill the dialog with any previous selection
        if (font != null) dlg.setFontList(fontLabel.getFont().getFontData());
        if (color != null) dlg.setRGB(color.getRGB());

        if (dlg.open() != null) {
          // Dispose of any fonts or colors we have created
          if (font != null) font.dispose();
          if (color != null) color.dispose();

          // Create the new font and set it into the label
          font = new Font(shell.getDisplay(), dlg.getFontList());
          fontLabel.setFont(font);

          // Create the new color and set it
          color = new Color(shell.getDisplay(), dlg.getRGB());
          fontLabel.setForeground(color);

          // Call pack() to resize the window to fit the new font
          shell.pack();
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
    new ChooseFont().run();
  }
}
