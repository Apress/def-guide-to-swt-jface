package examples.ch14;

import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates TableTreeViewer.
 */
public class PlayerTableTree extends ApplicationWindow {
  // The TableTreeViewer
  private TableTreeViewer ttv;

  /**
   * PlayerTableTree constructor
   */
  public PlayerTableTree() {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run() {
    // Don't return from open() until window closes
    setBlockOnOpen(true);

    // Open the main window
    open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  /**
   * Configures the shell
   * 
   * @param shell the shell
   */
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText("Team Tree");
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    // Create the table viewer to display the players
    ttv = new TableTreeViewer(parent);
    ttv.getTableTree().setLayoutData(new GridData(GridData.FILL_BOTH));

    // Set the content and label providers
    ttv.setContentProvider(new PlayerTreeContentProvider());
    ttv.setLabelProvider(new PlayerTreeLabelProvider());
    ttv.setInput(new PlayerTableModel());

    // Set up the table
    Table table = ttv.getTableTree().getTable();
    new TableColumn(table, SWT.LEFT).setText("First Name");
    new TableColumn(table, SWT.LEFT).setText("Last Name");
    new TableColumn(table, SWT.RIGHT).setText("Points");
    new TableColumn(table, SWT.RIGHT).setText("Rebounds");
    new TableColumn(table, SWT.RIGHT).setText("Assists");

    // Expand everything
    ttv.expandAll();

    // Pack the columns
    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
      table.getColumn(i).pack();
    }

    // Turn on the header and the lines
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    // Pack the window
    parent.pack();

    // Scroll to top
    ttv.reveal(ttv.getElementAt(0));

    return ttv.getTableTree();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PlayerTableTree().run();
  }
}