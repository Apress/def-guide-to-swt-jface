package examples.ch20;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This class provides the labels for the Address Book application
 */
public class AddressBookLabelProvider implements ITableLabelProvider {
  /**
   * Gets the image for the column
   * 
   * @param element the element
   * @param columnIndex the column index
   */
  public Image getColumnImage(Object element, int columnIndex) {
    return null;
  }

  /**
   * Gets the text for the column
   * 
   * @param element the element
   * @param columnIndex the column index
   */
  public String getColumnText(Object element, int columnIndex) {
    AddressEntry ae = (AddressEntry) element;
    switch (columnIndex) {
    case 0:
      return ae.getFirstName();
    case 1:
      return ae.getLastName();
    case 2:
      return ae.getEmail();
    }
    return "";
  }

  /**
   * Adds a listener
   * 
   * @param listener the listener
   */
  public void addListener(ILabelProviderListener listener) {
  // Do nothing
  }

  /**
   * Disposes any resources
   */
  public void dispose() {
  // Do nothing
  }

  /**
   * Returns true if changing the property for the element would change the label
   * 
   * @param element the element
   * @param property the property
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
  // Do nothing
  }
}