package examples.ch8.xmlview;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

/**
 * This class contains the menu for the XML View application
 */
public class XmlViewMenu {
  Menu menu = null;

  public XmlViewMenu(final Shell shell) {
    // Create the menu
    menu = new Menu(shell, SWT.BAR);

    // Create the File top-level menu
    MenuItem item = new MenuItem(menu, SWT.CASCADE);
    item.setText("File");
    Menu dropMenu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(dropMenu);

    // Create File->Open
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Open...\tCtrl+O");
    item.setAccelerator(SWT.CTRL + 'O');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        XmlView.getApp().openFile();
      }
    });

    // Create File->Close
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Close\tCtrl+C");
    item.setAccelerator(SWT.CTRL + 'C');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        XmlView.getApp().closeFile();
      }
    });

    new MenuItem(dropMenu, SWT.SEPARATOR);

    // Create File->Exit
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Exit\tAlt+F4");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        shell.close();
      }
    });

    // Create Help
    item = new MenuItem(menu, SWT.CASCADE);
    item.setText("Help");
    dropMenu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(dropMenu);

    // Create Help->About
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("About\tCtrl+A");
    item.setAccelerator(SWT.CTRL + 'A');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        XmlView.getApp().about();
      }
    });
  }

  /**
   * Gets the underlying menu
   * 
   * @return Menu
   */
  public Menu getMenu() {
    return menu;
  }
}
