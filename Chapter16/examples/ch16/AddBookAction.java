package examples.ch16;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This action class adds a book
 */
public class AddBookAction extends Action {
  /**
   * AddBookAction constructor
   */
  public AddBookAction() {
    super("&Add Book@Ctrl+B", ImageDescriptor.createFromFile(AddBookAction.class,
        "/images/addBook.gif"));
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(
        AddBookAction.class, "/images/disabledAddBook.gif"));
    setToolTipText("Add");
  }

  /**
   * Adds a book to the current library
   */
  public void run() {
    Librarian.getApp().addBook();
  }
}