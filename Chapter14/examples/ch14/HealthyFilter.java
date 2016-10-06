package examples.ch14;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * This class filters only healthy items from the grocery list
 */
public class HealthyFilter extends ViewerFilter {
  /**
   * Returns whether the specified element passes this filter
   * 
   * @param arg0 the viewer
   * @param arg1 the parent element
   * @param arg2 the element
   * @return boolean
   */
  public boolean select(Viewer arg0, Object arg1, Object arg2) {
    return ((Food) arg2).isHealthy();
  }
}