package examples.ch16;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This class provides the labels for the library table
 */
public class LibraryLabelProvider implements ITableLabelProvider {
  private Image checked;
  private Image unchecked;

  /**
   * LibraryLabelProvider constructor
   */
  public LibraryLabelProvider() {
    // Create the check mark images
    checked = new Image(null, LibraryLabelProvider.class
        .getResourceAsStream("/images/checked.gif"));
    unchecked = new Image(null, LibraryLabelProvider.class
        .getResourceAsStream("/images/unchecked.gif"));
  }

  /**
   * Gets the column image
   * 
   * @param element the book
   * @param columnIndex the column index
   * @return Image
   */
  public Image getColumnImage(Object element, int columnIndex) {
    // For the "Checked Out" column, return the check mark
    // if the book is checked out
    if (columnIndex == 1)
        return ((Book) element).isCheckedOut() ? checked : unchecked;
    return null;
  }

  /**
   * Gets the column text
   * 
   * @param element the book
   * @param columnIndex the column index
   * @return String
   */
  public String getColumnText(Object element, int columnIndex) {
    Book book = (Book) element;
    String text = null;
    switch (columnIndex) {
    case 0:
      text = book.getTitle();
      break;
    case 2:
      text = book.getCheckedOutTo();
      break;
    }
    return text == null ? "" : text;
  }

  /**
   * Adds a listener
   */
  public void addListener(ILabelProviderListener listener) {
  // Ignore
  }

  /**
   * Disposes any resources
   */
  public void dispose() {
    if (checked != null) checked.dispose();
    if (unchecked != null) unchecked.dispose();
  }

  /**
   * Gets whether this is a label property
   * 
   * @param element the book
   * @param property the property
   * @return boolean
   */
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  /**
   * Removes a listener
   * 
   * @param listener the listener
   */
  public void removeListener(ILabelProviderListener listener) {
  // Ignore
  }
}