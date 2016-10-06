package examples.ch8;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Displays a single-selection tree, a multi-selection tree, and a checkbox tree
 */
public class TreeExample {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("TreeExample");
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  private void createContents(Composite composite) {
    // Set the single-selection tree in the upper left,
    // the multi-selection tree in the upper right,
    // and the checkbox tree across the bottom.
    // To do this, create a 1x2 grid, and in the top
    // cell, a 2x1 grid.
    composite.setLayout(new GridLayout(1, true));
    Composite top = new Composite(composite, SWT.NONE);
    GridData data = new GridData(GridData.FILL_BOTH);
    top.setLayoutData(data);

    top.setLayout(new GridLayout(2, true));
    Tree single = new Tree(top, SWT.SINGLE | SWT.BORDER);
    data = new GridData(GridData.FILL_BOTH);
    single.setLayoutData(data);
    fillTree(single);

    Tree multi = new Tree(top, SWT.MULTI | SWT.BORDER);
    data = new GridData(GridData.FILL_BOTH);
    multi.setLayoutData(data);
    fillTree(multi);

    Tree check = new Tree(composite, SWT.CHECK | SWT.BORDER);
    data = new GridData(GridData.FILL_BOTH);
    check.setLayoutData(data);
    fillTree(check);
  }

  /**
   * Helper method to fill a tree with data
   * 
   * @param tree the tree to fill
   */
  private void fillTree(Tree tree) {
    // Turn off drawing to avoid flicker
    tree.setRedraw(false);

    // Create five root items
    for (int i = 0; i < 5; i++) {
      TreeItem item = new TreeItem(tree, SWT.NONE);
      item.setText("Root Item " + i);

      // Create three children below the root
      for (int j = 0; j < 3; j++) {
        TreeItem child = new TreeItem(item, SWT.NONE);
        child.setText("Child Item " + i + " - " + j);

        // Create three grandchildren under the child
        for (int k = 0; k < 3; k++) {
          TreeItem grandChild = new TreeItem(child, SWT.NONE);
          grandChild.setText("Grandchild Item " + i + " - " + j + " - " + k);
        }
      }
    }
    // Turn drawing back on!
    tree.setRedraw(true);
  }

  /**
   * The entry point for the application
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new TreeExample().run();
  }
}
