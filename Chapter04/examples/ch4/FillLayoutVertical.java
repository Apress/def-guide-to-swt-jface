package examples.ch4;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;

public class FillLayoutVertical {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout(SWT.VERTICAL));
    new Button(shell, SWT.PUSH).setText("one");
    new Button(shell, SWT.PUSH).setText("two");
    new Button(shell, SWT.PUSH).setText("three");
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
