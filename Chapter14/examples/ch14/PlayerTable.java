package examples.ch14;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates TableViewers
 */
public class PlayerTable extends ApplicationWindow {
  // The data model
  private PlayerTableModel model;

  // The table viewer
  private TableViewer tv;

  /**
   * Constructs a PlayerTable
   */
  public PlayerTable() {
    super(null);
    model = new PlayerTableModel();
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
    shell.setSize(400, 400);
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    // Create the composite to hold the controls
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, false));

    // Create the combo to hold the team names
    Combo combo = new Combo(composite, SWT.READ_ONLY);
    combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the table viewer to display the players
    tv = new TableViewer(composite);

    // Set the content and label providers
    tv.setContentProvider(new PlayerContentProvider());
    tv.setLabelProvider(new PlayerLabelProvider());
    tv.setSorter(new PlayerViewerSorter());

    // Set up the table
    Table table = tv.getTable();
    table.setLayoutData(new GridData(GridData.FILL_BOTH));

    // Add the first name column
    TableColumn tc = new TableColumn(table, SWT.LEFT);
    tc.setText("First Name");
    tc.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ((PlayerViewerSorter) tv.getSorter())
            .doSort(PlayerConst.COLUMN_FIRST_NAME);
        tv.refresh();
      }
    });

    // Add the last name column
    tc = new TableColumn(table, SWT.LEFT);
    tc.setText("Last Name");
    tc.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ((PlayerViewerSorter) tv.getSorter())
            .doSort(PlayerConst.COLUMN_LAST_NAME);
        tv.refresh();
      }
    });

    // Add the points column
    tc = new TableColumn(table, SWT.RIGHT);
    tc.setText("Points");
    tc.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ((PlayerViewerSorter) tv.getSorter()).doSort(PlayerConst.COLUMN_POINTS);
        tv.refresh();
      }
    });

    // Add the rebounds column
    tc = new TableColumn(table, SWT.RIGHT);
    tc.setText("Rebounds");
    tc.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ((PlayerViewerSorter) tv.getSorter()).doSort(PlayerConst.COLUMN_REBOUNDS);
        tv.refresh();
      }
    });

    // Add the assists column
    tc = new TableColumn(table, SWT.RIGHT);
    tc.setText("Assists");
    tc.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ((PlayerViewerSorter) tv.getSorter()).doSort(PlayerConst.COLUMN_ASSISTS);
        tv.refresh();
      }
    });

    // Add the team names to the combo
    for (int i = 0, n = model.teams.length; i < n; i++) {
      combo.add(model.teams[i].getName());
    }

    // Add a listener to change the tableviewer's input
    combo.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        update(model.teams[((Combo) event.widget).getSelectionIndex()]);
      }
    });

    // Select the first item
    combo.select(0);
    update(model.teams[0]);

    // Pack the columns
    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
      table.getColumn(i).pack();
    }

    // Turn on the header and the lines
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    return composite;
  }

  /**
   * Updates the application with the selected team
   * 
   * @param team the team
   */
  private void update(Team team) {
    // Update the window's title bar with the new team
    getShell().setText(team.getYear() + " " + team.getName());

    // Set the table viewer's input to the team
    tv.setInput(team);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PlayerTable().run();
  }
}