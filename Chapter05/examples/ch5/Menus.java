package examples.ch5;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates menus
 */
public class Menus {
  private Image star;
  private Image circle;
  private Image square;
  private Image triangle;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Menus");
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    if (circle != null) circle.dispose();
    if (star != null) star.dispose();
    if (square != null) square.dispose();
    if (triangle != null) triangle.dispose();
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    shell.setLayout(new FillLayout());
    createBarMenu(shell);
    createPopUpMenu(shell);
    createNoRadioGroupPopUpMenu(shell);
  }

  /**
   * Creates the bar menu for the main window
   * 
   * @param shell the main window
   */
  private void createBarMenu(Shell shell) {
    // Create the bar menu
    Menu menu = new Menu(shell, SWT.BAR);

    // Create all the items in the bar menu
    MenuItem fileItem = new MenuItem(menu, SWT.CASCADE);
    fileItem.setText("File");
    MenuItem editItem = new MenuItem(menu, SWT.CASCADE);
    editItem.setText("Edit");
    MenuItem formatItem = new MenuItem(menu, SWT.CASCADE);
    formatItem.setText("Format");
    MenuItem viewItem = new MenuItem(menu, SWT.CASCADE);
    viewItem.setText("View");
    MenuItem helpItem = new MenuItem(menu, SWT.CASCADE);
    helpItem.setText("Help");

    // Create the File item's dropdown menu
    Menu fileMenu = new Menu(menu);
    fileItem.setMenu(fileMenu);

    // Create all the items in the File dropdown menu
    MenuItem newItem = new MenuItem(fileMenu, SWT.NONE);
    newItem.setText("New");
    MenuItem openItem = new MenuItem(fileMenu, SWT.NONE);
    openItem.setText("Open...");
    MenuItem saveItem = new MenuItem(fileMenu, SWT.NONE);
    saveItem.setText("Save");
    MenuItem saveAsItem = new MenuItem(fileMenu, SWT.NONE);
    saveAsItem.setText("Save As...");
    new MenuItem(fileMenu, SWT.SEPARATOR);
    MenuItem pageSetupItem = new MenuItem(fileMenu, SWT.NONE);
    pageSetupItem.setText("Page Setup...");
    MenuItem printItem = new MenuItem(fileMenu, SWT.NONE);
    printItem.setText("Print...");
    new MenuItem(fileMenu, SWT.SEPARATOR);
    MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
    exitItem.setText("Exit");

    // Set the bar menu as the menu in the shell
    shell.setMenuBar(menu);
  }

  /**
   * Creates the left-half pop-up menu
   * 
   * @param shell the main window
   */
  private void createPopUpMenu(Shell shell) {
    // Create a composite that the pop-up menu will be
    // associated with
    Label label = new Label(shell, SWT.BORDER);
    label.setText("Pop-up Menu");

    // Create the pop-up menu
    Menu menu = new Menu(label);

    // Create the images
    star = new Image(shell.getDisplay(), this.getClass().getResourceAsStream(
        "/images/star.gif"));
    circle = new Image(shell.getDisplay(), this.getClass().getResourceAsStream(
        "/images/circle.gif"));
    square = new Image(shell.getDisplay(), this.getClass().getResourceAsStream(
        "/images/square.gif"));
    triangle = new Image(shell.getDisplay(), this.getClass().getResourceAsStream(
        "/images/triangle.gif"));

    // Create all the items in the pop-up menu
    MenuItem newItem = new MenuItem(menu, SWT.CASCADE);
    newItem.setText("New");
    newItem.setImage(star);
    MenuItem refreshItem = new MenuItem(menu, SWT.NONE);
    refreshItem.setText("Refresh");
    refreshItem.setImage(circle);
    MenuItem deleteItem = new MenuItem(menu, SWT.NONE);
    deleteItem.setText("Delete");

    new MenuItem(menu, SWT.SEPARATOR);

    // Add a check menu item and select it
    MenuItem checkItem = new MenuItem(menu, SWT.CHECK);
    checkItem.setText("Check");
    checkItem.setSelection(true);
    checkItem.setImage(square);

    // Add a push menu item
    MenuItem pushItem = new MenuItem(menu, SWT.PUSH);
    pushItem.setText("Push");

    new MenuItem(menu, SWT.SEPARATOR);

    // Create some radio items
    MenuItem item1 = new MenuItem(menu, SWT.RADIO);
    item1.setText("Radio One");
    item1.setImage(triangle);
    MenuItem item2 = new MenuItem(menu, SWT.RADIO);
    item2.setText("Radio Two");
    MenuItem item3 = new MenuItem(menu, SWT.RADIO);
    item3.setText("Radio Three");

    // Create a new radio group
    new MenuItem(menu, SWT.SEPARATOR);

    // Create some radio items
    MenuItem itema = new MenuItem(menu, SWT.RADIO);
    itema.setText("Radio A");
    MenuItem itemb = new MenuItem(menu, SWT.RADIO);
    itemb.setText("Radio B");
    MenuItem itemc = new MenuItem(menu, SWT.RADIO);
    itemc.setText("Radio C");

    // Create the New item's dropdown menu
    Menu newMenu = new Menu(menu);
    newItem.setMenu(newMenu);

    // Create the items in the New dropdown menu
    MenuItem shortcutItem = new MenuItem(newMenu, SWT.NONE);
    shortcutItem.setText("Shortcut");
    MenuItem iconItem = new MenuItem(newMenu, SWT.NONE);
    iconItem.setText("Icon");

    // Set the pop-up menu as the pop-up for the label
    label.setMenu(menu);
  }

  /**
   * Creates the No Radio Group pop-up menu
   * 
   * @param shell the main window
   */
  private void createNoRadioGroupPopUpMenu(Shell shell) {
    // Create a composite that the pop-up menu will be
    // associated with
    Label label = new Label(shell, SWT.BORDER);
    label.setText("No Radio Group Menu");

    // Create the pop-up menu with the No Radio Group style
    Menu menu = new Menu(shell, SWT.POP_UP | SWT.NO_RADIO_GROUP);
    label.setMenu(menu);

    // Create all the items in the pop-up menu
    MenuItem item1 = new MenuItem(menu, SWT.RADIO);
    item1.setText("Radio One");
    MenuItem item2 = new MenuItem(menu, SWT.RADIO);
    item2.setText("Radio Two");
    MenuItem item3 = new MenuItem(menu, SWT.RADIO);
    item3.setText("Radio Three");

    // Set the pop-up menu as the pop-up for the label
    label.setMenu(menu);
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new Menus().run();
  }
}