package examples.ch12;

import org.eclipse.swt.*;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This program illustrates dragging
 */
public class SnippetBoard {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    createContents(shell);
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }

    display.dispose();
  }

  private void createContents(Shell shell) {
    shell.setLayout(new FillLayout());

    Table table = new Table(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

    // Create the types
    Transfer[] types = new Transfer[] { TextTransfer.getInstance()};
    
    // Create the drag source
    DragSource source = new DragSource(table, DND.DROP_MOVE | DND.DROP_COPY);
    source.setTransfer(types);
    source.addDragListener(new DragSourceAdapter() {
      public void dragSetData(DragSourceEvent event) {
        // Get the selected items in the drag source
        DragSource ds = (DragSource) event.widget;
        Table table = (Table) ds.getControl();
        TableItem[] selection = table.getSelection();
        
        // Create a buffer to hold the selected items and fill it
        StringBuffer buff = new StringBuffer();
        for (int i = 0, n = selection.length; i < n; i++) {
          buff.append(selection[i].getText());
        }
        
        // Put the data into the event
        event.data = buff.toString();
      }
    });
    
    // Create the drop target
    DropTarget target = new DropTarget(table, 
      DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
    target.setTransfer(types);
    target.addDropListener(new DropTargetAdapter() {
      public void dragEnter(DropTargetEvent event) {
        if (event.detail == DND.DROP_DEFAULT) {
          event.detail = (event.operations & DND.DROP_COPY) != 0 ? DND.DROP_COPY
            : DND.DROP_NONE;
        }

        // Allow dropping text only
        for (int i = 0, n = event.dataTypes.length; i < n; i++) {
          if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
            event.currentDataType = event.dataTypes[i];
          }
        }
      }

      public void dragOver(DropTargetEvent event) {
        // Provide visual feedback
        event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
      }

      public void drop(DropTargetEvent event) {
        // If any text was dropped . . .
        if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
          // Get the dropped data
          DropTarget target = (DropTarget) event.widget;
          Table table = (Table) target.getControl();
          String data = (String) event.data;
          
          // Create a new item in the table to hold the dropped data
          TableItem item = new TableItem(table, SWT.NONE);
          item.setText(new String[] { data});
          table.redraw();
        }
      }
    });

    TableColumn column = new TableColumn(table, SWT.NONE);

    // Seed the table
    TableItem item = new TableItem(table, SWT.NONE);
    item.setText(new String[] { "private static final int"});
    item = new TableItem(table, SWT.NONE);
    item.setText(new String[] { "String"});
    item = new TableItem(table, SWT.BORDER);
    item.setText(new String[] { "private static void main(String[] args) {"});
    
    column.pack();
  }
  
  /**
   * The application entry point
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SnippetBoard().run();
  }
}
