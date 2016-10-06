package examples.ch16;

/**
 * This class represents a book
 */
public class Book {
  private String title;
  private String checkedOutTo;

  /**
   * Book constructor
   * @param title the title
   */
  public Book(String title) {
    setTitle(title);
  }

  /**
   * Sets the title
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the title
   * @return String
   */
  public String getTitle() {
    return title;
  }

  /**
   * Check out
   * @param who the person checking this book out
   */
  public void checkOut(String who) {
    checkedOutTo = who;
    if (checkedOutTo.length() == 0) checkedOutTo = null;
  }

  public boolean isCheckedOut() {
    return checkedOutTo != null && checkedOutTo.length() > 0;
  }

  public void checkIn() {
    checkedOutTo = null;
  }

  /**
   * Gets who this book is checked out to
   * @return String
   */
  public String getCheckedOutTo() {
    return checkedOutTo;
  }
}