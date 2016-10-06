package examples.ch16;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class provides the content for the library table
 */
public class LibraryContentProvider implements IStructuredContentProvider {
  /**
   * Gets the books
   * 
   * @param inputElement the library
   * @return Object[]
   */
  public Object[] getElements(Object inputElement) {
    return ((Library) inputElement).getBooks().toArray();
  }

  /**
   * Disposes any resources
   */
  public void dispose() {
  // Do nothing
  }

  /**
   * Called when the input changes
   * 
   * @param viewer the viewer
   * @param oldInput the old library
   * @param newInput the new library
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  // Ignore
  }
}