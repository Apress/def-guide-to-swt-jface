package examples.ch8;

import java.io.*;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;

public class CoolBarTest {
  private static final String IMAGE_PATH = "images"
      + System.getProperty("file.separator");

  private Image circle;
  private Image square;
  private Image star;
  private Image triangle;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("CoolBar Test");
    createImages(shell);
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    disposeImages();
    display.dispose();
  }

  /**
   * Creates the window contents
   * 
   * @param shell the parent shell
   */
  private void createContents(Shell shell) {
    shell.setLayout(new GridLayout(1, false));
    CoolBar coolbar = createCoolBar(shell);
    coolbar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * Creates the CoolBar
   * 
   * @param shell the parent shell
   * @return CoolBar
   */
  private CoolBar createCoolBar(Shell shell) {
    CoolBar coolbar = new CoolBar(shell, SWT.NONE);

    // Create toolbar coolitem
    final CoolItem item = new CoolItem(coolbar, SWT.DROP_DOWN);
    item.setControl(createToolBar(coolbar));
    calcSize(item);

    // Add a listener to handle clicks on the chevron button
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        calcSize(item);
      }
    });

    // Create combo coolitem
    CoolItem item2 = new CoolItem(coolbar, SWT.NONE);
    item2.setControl(createCombo(coolbar));
    calcSize(item2);

    // Create a dropdown coolitem
    item2 = new CoolItem(coolbar, SWT.NONE);
    item2.setControl(createStackedButtons(coolbar));
    calcSize(item2);

    return coolbar;
  }

  /**
   * Creates the ToolBar
   * 
   * @param composite the parent composite
   * @return Control
   */
  private Control createToolBar(Composite composite) {
    ToolBar toolBar = new ToolBar(composite, SWT.NONE);
    ToolItem item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(circle);
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(square);
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(star);
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(triangle);
    return toolBar;
  }

  /**
   * Creates the Combo
   * 
   * @param composite the parent composite
   * @return Control
   */
  private Control createCombo(Composite composite) {
    // A bug with Windows causes the Combo not to drop
    // down if you add it directly to the CoolBar.
    // To work around this, create a Composite, add the
    // Combo to it, and add the Composite to the CoolBar.
    // This should work both on Windows and on all other
    // platforms.
    Composite c = new Composite(composite, SWT.NONE);
    c.setLayout(new FillLayout());
    Combo combo = new Combo(c, SWT.DROP_DOWN);
    combo.add("Option One");
    combo.add("Option Two");
    combo.add("Option Three");
    return c;
  }

  /**
   * Creates two stacked buttons
   * 
   * @param composite the parent composite
   * @return Control
   */
  private Control createStackedButtons(Composite composite) {
    Composite c = new Composite(composite, SWT.NONE);
    c.setLayout(new GridLayout(1, false));
    new Button(c, SWT.PUSH).setText("Button One");
    new Button(c, SWT.PUSH).setText("Button Two");
    return c;
  }

  /**
   * Helper method to calculate the size of the cool item
   * 
   * @param item the cool item
   */
  private void calcSize(CoolItem item) {
    Control control = item.getControl();
    Point pt = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    pt = item.computeSize(pt.x, pt.y);
    item.setSize(pt);
  }

  /**
   * Creates the images
   * 
   * @param shell the parent shell
   */
  private void createImages(Shell shell) {
    try {
      circle = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "circle.gif"));
      square = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "square.gif"));
      star = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "star.gif"));
      triangle = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "triangle.gif"));
    } catch (IOException e) {
      // Images not found; handle gracefully
    }
  }
  
  /**
   * Disposes the images
   */
  private void disposeImages() {
    if (circle != null)
      circle.dispose();
    if (square != null)
      square.dispose();
    if (star != null)
      star.dispose();
    if (triangle != null)
      triangle.dispose();
  }

  /**
   * The entry point for the application
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new CoolBarTest().run();
  }
}
