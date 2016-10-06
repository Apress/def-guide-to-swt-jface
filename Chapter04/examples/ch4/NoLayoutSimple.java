package examples.ch4;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;

public class NoLayoutSimple {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    Button button = new Button(shell, SWT.PUSH);
    button.setText("No layout");
    button.setBounds(new Rectangle(5, 5, 100, 100));
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
