package examples.ch8;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * This class provides the "drop down" functionality for our dropdown tool items.
 */
public class DropdownSelectionListener extends SelectionAdapter {
  private ToolItem dropdown;
  private Menu menu;

  /**
   * Constructs a DropdownSelectionListener
   * 
   * @param dropdown the dropdown this listener belongs to
   */
  public DropdownSelectionListener(ToolItem dropdown) {
    this.dropdown = dropdown;
    menu = new Menu(dropdown.getParent().getShell());
  }

  /**
   * Adds an item to the dropdown list
   * 
   * @param item the item to add
   */
  public void add(String item) {
    MenuItem menuItem = new MenuItem(menu, SWT.NONE);
    menuItem.setText(item);
    menuItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        MenuItem selected = (MenuItem) event.widget;
        dropdown.setText(selected.getText());
      }
    });
  }

  /**
   * Called when either the button itself or the dropdown arrow is clicked
   * 
   * @param event the event that trigged this call
   */
  public void widgetSelected(SelectionEvent event) {
    // If they clicked the arrow, we show the list
    if (event.detail == SWT.ARROW) {
      // Determine where to put the dropdown list
      ToolItem item = (ToolItem) event.widget;
      Rectangle rect = item.getBounds();
      Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
      menu.setLocation(pt.x, pt.y + rect.height);
      menu.setVisible(true);
    } else {
      // They pushed the button; take appropriate action
      ToolBarComplex.showMessage(dropdown.getParent().getShell(), dropdown
          .getText()
          + " Pressed");
    }
  }
}
