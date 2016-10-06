package examples.ch4;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.SWT;

public class RowLayoutTest {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    RowLayout layout = new RowLayout(SWT.VERTICAL);
    layout.marginLeft = 12;
    layout.marginTop = 0;
    layout.justify = true;
    shell.setLayout(layout);
    new Button(shell, SWT.PUSH).setText("one");
    new Button(shell, SWT.PUSH).setText("two");
    new Button(shell, SWT.PUSH).setText("three");
    new Button(shell, SWT.PUSH).setText("four");
    new Button(shell, SWT.PUSH).setText("five");
    new Button(shell, SWT.PUSH).setText("six");
    Button b = new Button(shell, SWT.PUSH);
    b.setText("seven");
    b.setLayoutData(new RowData(100, 100));
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
