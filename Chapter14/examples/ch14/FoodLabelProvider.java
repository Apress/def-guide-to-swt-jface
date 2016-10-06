package examples.ch14;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * This class provides the labels for the FoodList application
 */
public class FoodLabelProvider implements ILabelProvider {

  /**
   * ListViewers don't support images
   * 
   * @param arg0 the element
   * @return Image
   */
  public Image getImage(Object arg0) {
    return null;
  }

  /**
   * Gets the text for an element
   * 
   * @param arg0 the element
   * @return String
   */
  public String getText(Object arg0) {
    return ((Food) arg0).getName();
  }

  /**
   * Adds a listener
   * 
   * @param arg0 the listener
   */
  public void addListener(ILabelProviderListener arg0) {
  // Throw it away
  }

  /**
   * Disposes any resources
   */
  public void dispose() {
  // Nothing to dispose
  }

  /**
   * Returns whether changing the specified property for the specified element
   * affect the label
   * 
   * @param arg0 the element
   * @param arg1 the property
   * @return boolean
   */
  public boolean isLabelProperty(Object arg0, String arg1) {
    return false;
  }

  /**
   * Removes a listener
   * 
   * @param arg0 the listener
   */
  public void removeListener(ILabelProviderListener arg0) {
  // Ignore
  }
}