package examples.ch8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * This class creates a toolbar.
 */
public class ToolBarTest {
  public static void main(String[] args) {
    new ToolBarTest().run();
  }

  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Toolbar Test");
    shell.setLayout(new FillLayout());
    createToolbar(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  public void createToolbar(Shell shell) {
    ToolBar toolBar = new ToolBar(shell, SWT.HORIZONTAL);

    ToolItem item = new ToolItem(toolBar, SWT.PUSH);
    item.setText("Button One");
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setText("Button Two");
    new ToolItem(toolBar, SWT.SEPARATOR);
    item = new ToolItem(toolBar, SWT.CHECK);
    item.setText("Check One");
    item = new ToolItem(toolBar, SWT.CHECK);
    item.setText("Check Two");
    new ToolItem(toolBar, SWT.SEPARATOR);
    item = new ToolItem(toolBar, SWT.RADIO);
    item.setText("Radio One");
    item = new ToolItem(toolBar, SWT.RADIO);
    item.setText("Radio Two");
    new ToolItem(toolBar, SWT.SEPARATOR);
    item = new ToolItem(toolBar, SWT.DROP_DOWN);
    item.setText("Dropdown One");
    item = new ToolItem(toolBar, SWT.DROP_DOWN);
    item.setText("Dropdown Two");
  }
}
