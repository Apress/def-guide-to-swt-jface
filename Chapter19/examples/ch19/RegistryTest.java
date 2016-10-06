package examples.ch19;

import org.eclipse.jface.resource.*;
import org.eclipse.jface.util.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class tests the various JFace registries
 */
public class RegistryTest extends ApplicationWindow implements
    IPropertyChangeListener {
  // Keys for the registries
  private static final String FOREGROUND = "foreground";
  private static final String BACKGROUND = "background";
  private static final String FONT = "font";

  // The label to display the colors and fonts
  private Label label;

  // The color registry
  private static ColorRegistry CR;

  // The font registry
  private static FontRegistry FR;

  /**
   * RegistryTest constructor
   */
  public RegistryTest() {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run() {
    setBlockOnOpen(true);
    open();
    Display.getCurrent().dispose();
  }

  /**
   * Creates the window's contents
   * 
   * @param parent the parent composite
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new FillLayout(SWT.VERTICAL));

    // Set up the registries
    CR = new ColorRegistry();
    CR.addListener(this);

    FR = new FontRegistry();
    FR.addListener(this);

    // Create the label
    label = new Label(composite, SWT.CENTER);
    label.setText("Hello from JFace");

    // Create the randomize button
    Button button = new Button(composite, SWT.PUSH);
    button.setText("Randomize");
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        CR.put(FOREGROUND, new RGB((int) (Math.random() * 255), (int) (Math
            .random() * 255), (int) (Math.random() * 255)));
        CR.put(BACKGROUND, new RGB((int) (Math.random() * 255), (int) (Math
            .random() * 255), (int) (Math.random() * 255)));
        FontData fontData = new FontData("Times New Roman",
            (int) (Math.random() * 72), SWT.BOLD);
        FR.put(FONT, new FontData[] { fontData});
      }
    });
    return composite;
  }

  /**
   * Called when any property changes
   * 
   * @param event the event
   */
  public void propertyChange(PropertyChangeEvent event) {
    // Properties have changed; set into label
    if (CR.hasValueFor(FOREGROUND)) label.setForeground(CR.get(FOREGROUND));
    if (CR.hasValueFor(BACKGROUND)) label.setBackground(CR.get(BACKGROUND));
    if (FR.hasValueFor(FONT)) label.setFont(FR.get(FONT));

    getShell().pack();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new RegistryTest().run();
  }
}