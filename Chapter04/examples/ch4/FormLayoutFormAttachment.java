package examples.ch4;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;

public class FormLayoutFormAttachment {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    FormLayout layout = new FormLayout();
    layout.marginHeight = 5;
    layout.marginWidth = 10;
    shell.setLayout(layout);
    Button button = new Button(shell, SWT.PUSH);
    button.setText("Button");
    FormData data = new FormData();
    data.height = 50;
    data.right = new FormAttachment(100, -50);
    data.left = new FormAttachment(0, 10);
    data.top = new FormAttachment(1, 4, 0);
    button.setLayoutData(data);

    Button button2 = new Button(shell, SWT.PUSH);
    button2.setText("Button 2");
    data = new FormData();
    button2.setLayoutData(data);
    data.bottom = new FormAttachment(100, 0);
    data.top = new FormAttachment(button, 5);
    data.left = new FormAttachment(button, 0, SWT.LEFT);
    data.right = new FormAttachment(button, 0, SWT.RIGHT);

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
