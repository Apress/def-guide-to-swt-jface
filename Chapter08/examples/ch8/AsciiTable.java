package examples.ch8;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Displays ASCII Codes
 */
public class AsciiTable {
  // The number of characters to show.
  private static final int MAX_CHARS = 128;

  // Names for each of the columns
  private static final String[] COLUMN_NAMES = { "Char", "Dec", "Hex", "Oct",
      "Bin", "Name"};

  // The names of the first 32 characters
  private static final String[] CHAR_NAMES = { "NUL", "SOH", "STX", "ETX", "EOT",
      "ENQ", "ACK", "BEL", "BS", "TAB", "LF", "VT", "FF", "CR", "SO", "SI",
      "DLE", "DC1", "DC2", "DC3", "DC4", "NAK", "SYN", "ETB", "CAN", "EM", "SUB",
      "ESC", "FS", "GS", "RS", "US", "Space"};

  // The font to use for displaying characters
  private Font font;

  // The background colors to use for the rows
  private Color[] colors = new Color[MAX_CHARS];

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("ASCII Codes");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    // Call dispose to dispose any resources created
    // we have created
    dispose();
    display.dispose();
  }

  /**
   * Disposes the resources created
   */
  private void dispose() {
    // We created this font; we must dispose it
    if (font != null) {
      font.dispose();
    }

    // We created the colors; we must dispose them
    for (int i = 0, n = colors.length; i < n; i++) {
      if (colors[i] != null) {
        colors[i].dispose();
      }
    }
  }

  /**
   * Creates the font
   */
  private void createFont() {
    // Create a font that will display the range
    // of characters. "Terminal" works well in
    // Windows
    font = new Font(Display.getCurrent(), "Terminal", 10, SWT.NORMAL);
  }

  /**
   * Creates the columns for the table
   * 
   * @param table the table
   * @return TableColumn[]
   */
  private TableColumn[] createColumns(Table table) {
    TableColumn[] columns = new TableColumn[COLUMN_NAMES.length];
    for (int i = 0, n = columns.length; i < n; i++) {
      // Create the TableColumn with right alignment
      columns[i] = new TableColumn(table, SWT.RIGHT);

      // This text will appear in the column header
      columns[i].setText(COLUMN_NAMES[i]);
    }
    return columns;
  }

  /**
   * Creates the window's contents (the table)
   * 
   * @param composite the parent composite
   */
  private void createContents(Composite composite) {
    composite.setLayout(new FillLayout());

    // The system font will not display the lower 32
    // characters, so create one that will
    createFont();

    // Create a table with visible headers
    // and lines, and set the font that we
    // created
    Table table = new Table(composite, SWT.SINGLE | SWT.FULL_SELECTION);
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    table.setRedraw(false);
    table.setFont(font);

    // Create the columns
    TableColumn[] columns = createColumns(table);

    for (int i = 0; i < MAX_CHARS; i++) {
      // Create a background color for this row
      colors[i] = new Color(table.getDisplay(), 255 - i, 127 + i, i);

      // Create the row in the table by creating
      // a TableItem and setting text for each
      // column
      int c = 0;
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText(c++, String.valueOf((char) i));
      item.setText(c++, String.valueOf(i));
      item.setText(c++, Integer.toHexString(i).toUpperCase());
      item.setText(c++, Integer.toOctalString(i));
      item.setText(c++, Integer.toBinaryString(i));
      item.setText(c++, i < CHAR_NAMES.length ? CHAR_NAMES[i] : "");
      item.setBackground(colors[i]);
    }

    // Now that we've set the text into the columns,
    // we call pack() on each one to size it to the
    // contents.
    for (int i = 0, n = columns.length; i < n; i++) {
      columns[i].pack();
    }

    // Set redraw back to true so that the table
    // will paint appropriately
    table.setRedraw(true);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new AsciiTable().run();
  }
}
