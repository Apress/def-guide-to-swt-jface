package examples.ch8;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class displays a vertical sash
 */
public class VerticalSash {
  public static void main(String[] args) {
    new VerticalSash().run();
  }

  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Vertical Sash");
    shell.setLayout(new FillLayout());
    Sash sash = new Sash(shell, SWT.VERTICAL);
    new Composite(shell, SWT.NONE);
    new Composite(shell, SWT.NONE);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
