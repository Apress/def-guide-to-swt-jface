package examples.ch9.password.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import examples.ch9.password.Password;

/**
 * This class contains the toolbar for the Password application
 */
public class PasswordToolbarFactory {
  private static final String IMAGE_PATH = "/images/";

  // These contain the images for the toolbar buttons
  private static Image NEW;
  private static Image OPEN;
  private static Image SAVE;
  private static Image SAVE_AS;
  private static Image NEW_PASSWORD;
  private static Image EDIT_PASSWORD;
  private static Image DELETE_PASSWORD;

  /**
   * Factory create method
   * 
   * @param composite the parent composite
   * @return ToolBar
   */
  public static ToolBar create(Composite composite) {
    createImages(composite);
    ToolBar toolbar = new ToolBar(composite, SWT.HORIZONTAL);
    createItems(toolbar);
    return toolbar;
  }

  /**
   * Creates the toolbar items
   * 
   * @param toolbar the parent toolbar
   */
  private static void createItems(ToolBar toolbar) {
    // Create the New item
    ToolItem item = createItemHelper(toolbar, NEW, "New");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().newFile();
      }
    });

    // Create the Open item
    item = createItemHelper(toolbar, OPEN, "Open");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().openFile();
      }
    });

    // Create the Save item
    item = createItemHelper(toolbar, SAVE, "Save");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().saveFile();
      }
    });

    // Create the Save As item
    item = createItemHelper(toolbar, SAVE_AS, "Save As");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().saveFileAs();
      }
    });

    new ToolItem(toolbar, SWT.SEPARATOR);

    // Create the New Password item
    item = createItemHelper(toolbar, NEW_PASSWORD, "New Password");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().newPassword();
      }
    });

    // Create the Edit Password item
    item = createItemHelper(toolbar, EDIT_PASSWORD, "Edit Password");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().editPassword();
      }
    });

    // Create the Delete Password item
    item = createItemHelper(toolbar, DELETE_PASSWORD, "Delete Password");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Password.getApp().deletePassword();
      }
    });
  }

  /**
   * Helper method to create a toolbar item
   * 
   * @param toolbar the parent toolbar
   * @param image the image to use
   * @param text the text to use
   * @return ToolItem
   */
  private static ToolItem createItemHelper(ToolBar toolbar, Image image,
      String text) {
    ToolItem item = new ToolItem(toolbar, SWT.PUSH);
    if (image == null) {
      item.setText(text);
    } else {
      item.setImage(image);
      item.setToolTipText(text);
    }
    return item;
  }

  private static void createImages(Composite composite) {
    if (NEW == null) {
      Display display = composite.getDisplay();
      Class c = Password.getApp().getClass();
      NEW = new Image(display, c.getResourceAsStream(IMAGE_PATH + "new.gif"));
      OPEN = new Image(display, c.getResourceAsStream(IMAGE_PATH + "open.gif"));
      SAVE = new Image(display, c.getResourceAsStream(IMAGE_PATH + "save.gif"));
      SAVE_AS = new Image(display, c.getResourceAsStream(IMAGE_PATH
          + "saveAs.gif"));
      NEW_PASSWORD = new Image(display, c.getResourceAsStream(IMAGE_PATH
          + "password.gif"));
      EDIT_PASSWORD = new Image(display, c.getResourceAsStream(IMAGE_PATH
          + "editPassword.gif"));
      DELETE_PASSWORD = new Image(display, c.getResourceAsStream(IMAGE_PATH
          + "delPassword.gif"));
    }
  }
}
