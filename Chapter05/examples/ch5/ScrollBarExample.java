package examples.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates scroll bars
 */
public class ScrollBarExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    
    // Create a List with a vertical scroll bar
    List list = new List(shell, SWT.V_SCROLL);
    
    // Add a bunch of items to it
    for (int i = 0; i < 500; i++) {
      list.add("A list item");
    }
    
    // Scroll to the bottom
    list.select(list.getItemCount() - 1);
    list.showSelection();
    
    // Get the scroll bar
    ScrollBar sb = list.getVerticalBar();

    // Add one more item that shows the selection value
    list.add("Selection: " + sb.getSelection());
    
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
