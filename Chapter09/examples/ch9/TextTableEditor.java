package examples.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates TableEditor.
 */
public class TextTableEditor {
  // Number of rows and columns
  private static final int NUM = 5;

  // Colors for each row
  private Color[] colors = new Color[NUM];

  // Options for each dropdown
  private String[] options = { "Option 1", "Option 2", "Option 3"};

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Text Table Editor");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    // Dispose any created colors
    for (int i = 0; i < NUM; i++) {
      if (colors[i] != null) colors[i].dispose();
    }
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(final Shell shell) {
    shell.setLayout(new FillLayout());

    // Create the table
    final Table table = new Table(shell, SWT.SINGLE | SWT.FULL_SELECTION
        | SWT.HIDE_SELECTION);
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    // Create five columns
    for (int i = 0; i < NUM; i++) {
      TableColumn column = new TableColumn(table, SWT.CENTER);
      column.setText("Column " + (i + 1));
      column.pack();
    }

    // Create five table editors for color
    TableEditor[] colorEditors = new TableEditor[NUM];

    // Create five buttons for changing color
    Button[] colorButtons = new Button[NUM];

    // Create five rows and the editors for those rows. The first column has the
    // color change buttons. The second column has dropdowns. The final three
    // have text fields.
    for (int i = 0; i < NUM; i++) {
      // Create the row
      final TableItem item = new TableItem(table, SWT.NONE);

      // Create the editor and button
      colorEditors[i] = new TableEditor(table);
      colorButtons[i] = new Button(table, SWT.PUSH);

      // Set attributes of the button
      colorButtons[i].setText("Color...");
      colorButtons[i].computeSize(SWT.DEFAULT, table.getItemHeight());

      // Set attributes of the editor
      colorEditors[i].grabHorizontal = true;
      colorEditors[i].minimumHeight = colorButtons[i].getSize().y;
      colorEditors[i].minimumWidth = colorButtons[i].getSize().x;

      // Set the editor for the first column in the row
      colorEditors[i].setEditor(colorButtons[i], item, 0);

      // Create a handler for the button
      final int index = i;
      colorButtons[i].addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          ColorDialog dialog = new ColorDialog(shell);
          if (colors[index] != null) dialog.setRGB(colors[index].getRGB());
          RGB rgb = dialog.open();
          if (rgb != null) {
            if (colors[index] != null) colors[index].dispose();
            colors[index] = new Color(shell.getDisplay(), rgb);
            item.setForeground(colors[index]);
          }
        }
      });
    }

    // Create an editor object to use for text editing
    final TableEditor editor = new TableEditor(table);
    editor.horizontalAlignment = SWT.LEFT;
    editor.grabHorizontal = true;

    // Use a mouse listener, not a selection listener, since we're interested
    // in the selected column as well as row
    table.addMouseListener(new MouseAdapter() {
      public void mouseDown(MouseEvent event) {
        // Dispose any existing editor
        Control old = editor.getEditor();
        if (old != null) old.dispose();

        // Determine where the mouse was clicked
        Point pt = new Point(event.x, event.y);

        // Determine which row was selected
        final TableItem item = table.getItem(pt);
        if (item != null) {
          // Determine which column was selected
          int column = -1;
          for (int i = 0, n = table.getColumnCount(); i < n; i++) {
            Rectangle rect = item.getBounds(i);
            if (rect.contains(pt)) {
              // This is the selected column
              column = i;
              break;
            }
          }

          // Column 2 holds dropdowns
          if (column == 1) {
            // Create the dropdown and add data to it
            final CCombo combo = new CCombo(table, SWT.READ_ONLY);
            for (int i = 0, n = options.length; i < n; i++) {
              combo.add(options[i]);
            }

            // Select the previously selected item from the cell
            combo.select(combo.indexOf(item.getText(column)));

            // Compute the width for the editor
            // Also, compute the column width, so that the dropdown fits
            editor.minimumWidth = combo.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
            table.getColumn(column).setWidth(editor.minimumWidth);

            // Set the focus on the dropdown and set into the editor
            combo.setFocus();
            editor.setEditor(combo, item, column);

            // Add a listener to set the selected item back into the cell
            final int col = column;
            combo.addSelectionListener(new SelectionAdapter() {
              public void widgetSelected(SelectionEvent event) {
                item.setText(col, combo.getText());

                // They selected an item; end the editing session
                combo.dispose();
              }
            });
          } else if (column > 1) {
            // Create the Text object for our editor
            final Text text = new Text(table, SWT.NONE);
            text.setForeground(item.getForeground());

            // Transfer any text from the cell to the Text control,
            // set the color to match this row, select the text,
            // and set focus to the control
            text.setText(item.getText(column));
            text.setForeground(item.getForeground());
            text.selectAll();
            text.setFocus();

            // Recalculate the minimum width for the editor
            editor.minimumWidth = text.getBounds().width;

            // Set the control into the editor
            editor.setEditor(text, item, column);

            // Add a handler to transfer the text back to the cell
            // any time it's modified
            final int col = column;
            text.addModifyListener(new ModifyListener() {
              public void modifyText(ModifyEvent event) {
                // Set the text of the editor's control back into the cell
                item.setText(col, text.getText());
              }
            });
          }
        }
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new TextTableEditor().run();
  }
}
