package examples.ch8.xmlview;

import java.io.FileInputStream;
import java.io.IOException;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

/**
 * This class contains the toolbar for the Xml View application
 */
public class XmlViewToolbar {
  private ToolBar toolBar;
  private Image open;
  private Image close;
  private Image about;

  /**
   * XmlViewToolbar constructor
   * 
   * @param composite the parent composite
   */
  public XmlViewToolbar(Composite composite) {
    // Create the images used in the toolbar
    createImages(composite);

    // Create the toolbar
    toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.FLAT);

    // Add the items to the toolbar
    createItems();
  }

  /**
   * Gets the underlying toolbar
   * 
   * @return ToolBar
   */
  public ToolBar getToolBar() {
    return toolBar;
  }

  /**
   * Disposes any resources
   */
  public void dispose() {
    disposeImages();
  }

  /**
   * Disposes the images
   */
  private void disposeImages() {
    if (open != null) {
      open.dispose();
      open = null;
    }
    if (close != null) {
      close.dispose();
      close = null;
    }
    if (about != null) {
      about.dispose();
      about = null;
    }
  }

  /**
   * Creates the toolbar items
   */
  private void createItems() {
    // Create the Open item
    ToolItem item = createItemHelper(open, "Open");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        XmlView.getApp().openFile();
      }
    });

    // Create the Close item
    item = createItemHelper(close, "Close");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        XmlView.getApp().closeFile();
      }
    });

    // Insert a separator
    new ToolItem(toolBar, SWT.SEPARATOR);

    // Create the About item
    item = createItemHelper(about, "About");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        XmlView.getApp().about();
      }
    });
  }

  /**
   * Helper method to create a toolbar item
   * 
   * @param image the image to use
   * @param text the text to use
   * @return ToolItem
   */
  private ToolItem createItemHelper(Image image, String text) {
    // Create a push button item
    ToolItem item = new ToolItem(toolBar, SWT.PUSH);

    // If image loading failed, use text for the button.
    // Otherwise, use the image for the button, and
    // the text for the tool tip
    if (image == null) {
      item.setText(text);
    } else {
      item.setImage(image);
      item.setToolTipText(text);
    }
    return item;
  }

  /**
   * Creates the images
   * 
   * @param composite the parent composite
   */
  private void createImages(Composite composite) {
    Display display = composite.getDisplay();
    try {
      open = new Image(display, new FileInputStream(XmlView.IMAGE_PATH
          + "open.gif"));
      close = new Image(display, new FileInputStream(XmlView.IMAGE_PATH
          + "close.gif"));
      about = new Image(display, new FileInputStream(XmlView.IMAGE_PATH
          + "about.gif"));
    } catch (IOException e) {
      // If any images can't be loaded, we'll use text instead
      disposeImages();
    }
  }
}
