package examples.ch8;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Displays a table
 */
public class TableTest {
  public static void main(String[] args) {
    new TableTest().run();
  }

  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    Table table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
    for (int i = 0; i < 5; i++) {
      TableColumn column = new TableColumn(table, SWT.NONE);
    }
    for (int i = 0; i < 10; i++) {
      TableItem item = new TableItem(table, SWT.NONE);
      for (int j = 0; j < 5; j++) {
        item.setText(j, "Row " + i + ", Column " + j);
      }
    }
    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
      table.getColumn(i).pack();
    }
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
