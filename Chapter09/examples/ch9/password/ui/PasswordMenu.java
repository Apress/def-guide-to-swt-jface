package examples.ch9.password.ui;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import examples.ch9.password.Password;

/**
 * This class contains the menu for the Password application
 */
public class PasswordMenu {
  Menu menu = null;

  /**
   * Constructs a PasswordMenu
   * 
   * @param shell the parent shell
   */
  public PasswordMenu(final Shell shell) {
    // Create the menu
    menu = new Menu(shell, SWT.BAR);

    // Create the File top-level menu
    MenuItem item = new MenuItem(menu, SWT.CASCADE);
    item.setText("File");
    Menu dropMenu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(dropMenu);

    // Create File->New
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("New...\tCtrl+N");
    item.setAccelerator(SWT.CTRL + 'N');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().newFile();
      }
    });

    // Create File->Open
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Open...\tCtrl+O");
    item.setAccelerator(SWT.CTRL + 'O');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().openFile();
      }
    });

    // Create File->Save
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Save\tCtrl+S");
    item.setAccelerator(SWT.CTRL + 'S');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().saveFile();
      }
    });

    // Create File->Save As
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Save As...");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().saveFileAs();
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

    // Create Password
    item = new MenuItem(menu, SWT.CASCADE);
    item.setText("Password");
    dropMenu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(dropMenu);

    // Create Password->New
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("New...\tCtrl+N");
    item.setAccelerator(SWT.CTRL + 'N');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().newPassword();
      }
    });

    // Create Password->Edit
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Edit...\tCtrl+E");
    item.setAccelerator(SWT.CTRL + 'E');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {

        Password.getApp().editPassword();
      }
    });

    // Create Password->Delete
    item = new MenuItem(dropMenu, SWT.NULL);
    item.setText("Delete...\tDel");
    item.setAccelerator(SWT.DEL);
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().deletePassword();
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
        Password.getApp().about();
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
