package examples.ch4;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;

public class FormLayoutFormData {
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
    data.width = 50;
    button.setLayoutData(data);
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
