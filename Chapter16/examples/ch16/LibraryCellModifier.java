package examples.ch16;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.Item;

/**
 * This class is the cell modifier for the Librarian program
 */
public class LibraryCellModifier implements ICellModifier {
  /**
   * Gets whether the specified property can be modified
   * 
   * @param element the book
   * @param property the property
   * @return boolean
   */
  public boolean canModify(Object element, String property) {
    return true;
  }

  /**
   * Gets the value for the property
   * 
   * @param element the book
   * @param property the property
   * @return Object
   */
  public Object getValue(Object element, String property) {
    Book book = (Book) element;
    if (Librarian.TITLE.equals(property))
      return book.getTitle();
    else if (Librarian.CHECKED_OUT.equals(property))
      return Boolean.valueOf(book.isCheckedOut());
    else if (Librarian.WHO.equals(property))
      return book.getCheckedOutTo() == null ? "" : book.getCheckedOutTo();
    else
      return null;
  }

  /**
   * Modifies the element
   * 
   * @param element the book
   * @param property the property
   * @param value the new value
   */
  public void modify(Object element, String property, Object value) {
    if (element instanceof Item) element = ((Item) element).getData();

    Book book = (Book) element;
    if (Librarian.TITLE.equals(property))
      book.setTitle((String) value);
    else if (Librarian.CHECKED_OUT.equals(property)) {
      boolean b = ((Boolean) value).booleanValue();
      if (b)
        book.checkOut("[Enter Name]");
      else
        book.checkIn();
    } else if (Librarian.WHO.equals(property)) book.checkOut((String) value);

    // Refresh the view
    Librarian.getApp().refreshView();

    // Set the library dirty
    Librarian.getApp().setLibraryDirty();
  }
}