package examples.ch8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * Creates a tabbed display with a single tab
 */
public class TabSimple {
  public static void main(String[] args) {
    new TabSimple().run();
  }

  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    shell.setText("Simple Tabs");
    TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
    TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
