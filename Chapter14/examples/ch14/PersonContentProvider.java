package examples.ch14;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class provides the content for the person table
 */
public class PersonContentProvider implements IStructuredContentProvider {
  /**
   * Returns the Person objects
   */
  public Object[] getElements(Object inputElement) {
    return ((List) inputElement).toArray();
  }

  /**
   * Disposes any created resources
   */
  public void dispose() {
  // Do nothing
  }

  /**
   * Called when the input changes
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  // Ignore
  }
}