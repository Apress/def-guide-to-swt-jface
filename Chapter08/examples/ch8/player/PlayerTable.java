package examples.ch8.player;

import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * This program displays a table of baseball players and allows sorting by first
 * name, last name, or lifetime batting average.
 */
public class PlayerTable {
  private PlayerComparator comparator;

  // Must fully-qualify List to avoid ambiguity with
  // org.eclipse.swt.widgets.List
  private java.util.List players;

  /**
   * Constructs a PlayerTable
   */
  public PlayerTable() {
    // Create the comparator used for sorting
    comparator = new PlayerComparator();
    comparator.setColumn(PlayerComparator.FIRST_NAME);
    comparator.setDirection(PlayerComparator.ASCENDING);

    // Create the players
    players = new ArrayList();
    players.add(new Player("Gil", "Hodges", 0.273f));
    players.add(new Player("Jim", "Gilliam", 0.265f));
    players.add(new Player("Jackie", "Robinson", 0.311f));
    players.add(new Player("Pee Wee", "Reese", 0.269f));
    players.add(new Player("Roy", "Campanella", 0.276f));
    players.add(new Player("Carl", "Furillo", 0.299f));
    players.add(new Player("Sandy", "Amoros", 0.255f));
    players.add(new Player("Duke", "Snider", 0.295f));
  }

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Player Table");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Create the contents of the main window
   * 
   * @param composite the parent composite
   */
  private void createContents(Composite composite) {
    composite.setLayout(new FillLayout());

    // Create the table
    final Table table = new Table(composite, SWT.NONE);
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    // Create each of the columns, adding an event
    // listener that will set the appropriate fields
    // into the comparator and then call the fillTable
    // helper method
    TableColumn[] columns = new TableColumn[3];
    columns[0] = new TableColumn(table, SWT.NONE);
    columns[0].setText("First Name");
    columns[0].addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        comparator.setColumn(PlayerComparator.FIRST_NAME);
        comparator.reverseDirection();
        fillTable(table);
      }
    });

    columns[1] = new TableColumn(table, SWT.NONE);
    columns[1].setText("Last Name");
    columns[1].addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        comparator.setColumn(PlayerComparator.LAST_NAME);
        comparator.reverseDirection();
        fillTable(table);
      }
    });

    columns[2] = new TableColumn(table, SWT.RIGHT);
    columns[2].setText("Batting Average");
    columns[2].addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        comparator.setColumn(PlayerComparator.BATTING_AVERAGE);
        comparator.reverseDirection();
        fillTable(table);
      }
    });

    // Do the initial fill of the table
    fillTable(table);

    // Pack each column so inital display is good
    for (int i = 0, n = columns.length; i < n; i++) {
      columns[i].pack();
    }
  }

  private void fillTable(Table table) {
    // Turn off drawing to avoid flicker
    table.setRedraw(false);

    // We remove all the table entries, sort our
    // rows, then add the entries
    table.removeAll();
    Collections.sort(players, comparator);
    for (Iterator itr = players.iterator(); itr.hasNext();) {
      Player player = (Player) itr.next();
      TableItem item = new TableItem(table, SWT.NONE);
      int c = 0;
      item.setText(c++, player.getFirstName());
      item.setText(c++, player.getLastName());
      item.setText(c++, String.valueOf(player.getBattingAverage()));
    }

    // Turn drawing back on
    table.setRedraw(true);
  }

  /**
   * The application's entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PlayerTable().run();
  }
}
