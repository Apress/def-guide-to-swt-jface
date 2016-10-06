package examples.ch19;

import org.eclipse.jface.resource.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class tests ImageRegistry
 */
public class ImageRegistryTest extends ApplicationWindow {
  // Keys for the registry
  private static final String ONE = "one";
  private static final String TWO = "two";
  private static final String THREE = "three";

  /**
   * ImageRegistryTest constructor
   */
  public ImageRegistryTest() {
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
    composite.setLayout(new FillLayout());

    // Put the images in the registry
    ImageRegistry ir = new ImageRegistry();
    ir.put(ONE, ImageDescriptor.createFromFile(ImageRegistryTest.class,
        "/images/one.gif"));
    ir.put(TWO, ImageDescriptor.createFromFile(ImageRegistryTest.class,
        "/images/two.gif"));
    ir.put(THREE, ImageDescriptor.createFromFile(ImageRegistryTest.class,
        "/images/three.gif"));

    // Create the labels and add the images
    Label label = new Label(composite, SWT.NONE);
    label.setImage(ir.get(ONE));
    label = new Label(composite, SWT.NONE);
    label.setImage(ir.get(TWO));
    label = new Label(composite, SWT.NONE);
    label.setImage(ir.get(THREE));

    getShell().pack();

    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ImageRegistryTest().run();
  }
}