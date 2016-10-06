package examples.ch14;

import java.util.*;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates CellEditors It allows you to create and edit Person
 * objects
 */
public class PersonEditor extends ApplicationWindow {
  // Table column names/properties
  public static final String NAME = "Name";
  public static final String MALE = "Male?";
  public static final String AGE = "Age Range";
  public static final String SHIRT_COLOR = "Shirt Color";

  public static final String[] PROPS = { NAME, MALE, AGE, SHIRT_COLOR};

  // The data model
  private java.util.List people;

  /**
   * Constructs a PersonEditor
   */
  public PersonEditor() {
    super(null);
    people = new ArrayList();
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
    shell.setText("Person Editor");
    shell.setSize(400, 400);
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, false));

    // Add a button to create the new person
    Button newPerson = new Button(composite, SWT.PUSH);
    newPerson.setText("Create New Person");

    // Add the TableViewer
    final TableViewer tv = new TableViewer(composite, SWT.FULL_SELECTION);
    tv.setContentProvider(new PersonContentProvider());
    tv.setLabelProvider(new PersonLabelProvider());
    tv.setInput(people);

    // Set up the table
    Table table = tv.getTable();
    table.setLayoutData(new GridData(GridData.FILL_BOTH));

    new TableColumn(table, SWT.CENTER).setText(NAME);
    new TableColumn(table, SWT.CENTER).setText(MALE);
    new TableColumn(table, SWT.CENTER).setText(AGE);
    new TableColumn(table, SWT.CENTER).setText(SHIRT_COLOR);

    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
      table.getColumn(i).pack();
    }

    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    // Add a new person when the user clicks button
    newPerson.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Person p = new Person();
        p.setName("Name");
        p.setMale(true);
        p.setAgeRange(Integer.valueOf("0"));
        p.setShirtColor(new RGB(255, 0, 0));
        people.add(p);
        tv.refresh();
      }
    });

    // Create the cell editors
    CellEditor[] editors = new CellEditor[4];
    editors[0] = new TextCellEditor(table);
    editors[1] = new CheckboxCellEditor(table);
    editors[2] = new ComboBoxCellEditor(table, AgeRange.INSTANCES, SWT.READ_ONLY);
    editors[3] = new ColorCellEditor(table);

    // Set the editors, cell modifier, and column properties
    tv.setColumnProperties(PROPS);
    tv.setCellModifier(new PersonCellModifier(tv));
    tv.setCellEditors(editors);

    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new PersonEditor().run();
  }
}