package examples.ch9.password.ui;

import java.io.File;
import java.util.Iterator;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import examples.ch9.password.*;
import examples.ch9.password.data.*;

/**
 * This class contains the content displayed in a tab
 */
public class PasswordFileTab extends Composite {
  public static final String UNTITLED = "Untitled";

  private PasswordFile file;
  private CTabFolder tabFolder;
  private TableTree tableTree;

  /**
   * Constructs a PasswordFileTab
   * 
   * @param tabFolder the parent tab folder
   * @param file the file this tab is associated with
   */
  public PasswordFileTab(CTabFolder tabFolder, PasswordFile file) {
    super(tabFolder, SWT.NONE);
    this.file = file;
    this.tabFolder = tabFolder;

    createContents();
    refresh();
  }

  /**
   * Creates the contents to display in the tab
   */
  private void createContents() {
    setLayout(new FillLayout());
    tableTree = new TableTree(this, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);

    Table table = tableTree.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    new TableColumn(table, 0).setText("Category");
    new TableColumn(table, 1).setText("Name");
    new TableColumn(table, 2).setText("User ID");
    new TableColumn(table, 3).setText("Password");

    // Add a TableTreeEditor to edit the items in place
    // Create an editor object to use for text editing
    final TableTreeEditor editor = new TableTreeEditor(tableTree);
    editor.horizontalAlignment = SWT.LEFT;
    editor.grabHorizontal = true;

    // Add a double click handler to launch the edit dialog
    // and a click handler to begin an editing session
    table.addMouseListener(new MouseAdapter() {
      public void mouseDoubleClick(MouseEvent event) {
        PasswordEntry entry = getSelection();
        if (entry != null) {
          Password.getApp().editPassword(entry);
        }
      }

      public void mouseDown(MouseEvent event) {
        // Dispose any existing editor
        Control old = editor.getEditor();
        if (old != null) old.dispose();

        // Determine where the mouse was clicked
        Point pt = new Point(event.x, event.y);

        // Determine which row was selected
        final TableTreeItem item = tableTree.getItem(pt);
        if (item != null) {
          // Determine which column was selected
          int column = -1;
          for (int i = 0, n = tableTree.getTable().getColumnCount(); i < n; i++) {
            Rectangle rect = item.getBounds(i);
            if (rect.contains(pt)) {
              // This is the selected column
              column = i;
              break;
            }
          }
          if (column > 0) {
            final PasswordEntry entry = (PasswordEntry) item.getData();
            if (entry != null) {
              // Create the Text object for our editor
              final Text text = new Text(tableTree, SWT.NONE);

              // Transfer any text from the cell to the Text control,
              // set the color to match this row, select the text,
              // and set focus to the control
              text.setText(item.getText(column));
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
                  switch (col) {
                  case 1:
                    entry.setName(text.getText());
                    break;
                  case 2:
                    entry.setUserId(text.getText());
                    break;
                  case 3:
                    entry.setPassword(text.getText());
                    break;
                  }
                  file.setDirty(true);
                  Password.getApp().getMainWindow().refreshTabs();
                }
              });
            }
          }
        }
      }
    });
  }

  /**
   * Gets the text for this tab
   * 
   * @return String
   */
  public String getText() {
    String text = file.getFilename();
    if (text == null) {
      text = UNTITLED;
    } else {
      // Parse the file name off the full path
      int pos = text.lastIndexOf(File.separator);
      if (pos != -1 && pos < text.length() - 1) {
        text = text.substring(pos + 1);
      }
    }
    if (file.isDirty()) {
      text = "*" + text;
    }
    return text;
  }

  /**
   * Gets the file associated with this tab
   * 
   * @return PasswordFile
   */
  public PasswordFile getFile() {
    return file;
  }

  /**
   * Clears the view
   */
  private void clearView() {
    tableTree.removeAll();
  }

  /**
   * Refreshes the view
   */
  public void refresh() {
    // Turn off drawing and clear the view
    tableTree.setRedraw(false);
    clearView();

    // Go through categories, creating top-level items
    for (Iterator itr = file.getCategories().iterator(); itr.hasNext();) {
      String category = (String) itr.next();

      // Display category only if it has entries
      if (!file.getEntries(category).isEmpty()) {
        TableTreeItem item = new TableTreeItem(tableTree, SWT.NONE);
        item.setText(category);

        // Go through the entries for the category, creating the sub-items
        for (Iterator itrEntries = file.getEntries(category).iterator(); itrEntries
            .hasNext();) {
          PasswordEntry entry = (PasswordEntry) itrEntries.next();
          TableTreeItem subItem = new TableTreeItem(item, SWT.NONE);
          int c = 0;
          subItem.setText(c++, "");
          subItem.setText(c++, entry.getName());
          subItem.setText(c++, entry.getUserId());
          subItem.setText(c++, entry.getPassword());
          subItem.setData(entry);
        }
        item.setExpanded(true);
      }
    }

    // If no items, create a dummy item. Otherwise, the TableCursor will fail
    if (tableTree.getItemCount() == 0) {
      new TableTreeItem(tableTree, SWT.NONE);
    }

    // Pack all the columns
    for (int i = 0, n = tableTree.getTable().getColumnCount(); i < n; i++) {
      tableTree.getTable().getColumn(i).pack();
    }

    // Turn drawing back on
    tableTree.setRedraw(true);
  }

  /**
   * Gets the selected item
   * 
   * @return PasswordEntry
   */
  public PasswordEntry getSelection() {
    PasswordEntry entry = null;
    TableTreeItem[] items = tableTree.getSelection();
    if (items.length == 1) {
      entry = (PasswordEntry) items[0].getData();
    }
    return entry;
  }
}
