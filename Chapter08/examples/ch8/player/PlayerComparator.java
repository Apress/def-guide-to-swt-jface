package examples.ch8.player;

import java.util.Comparator;

/**
 * This class does the comparisons for sorting Player objects.
 */
public class PlayerComparator implements Comparator {
  /** Constant for First Name column */
  public static final int FIRST_NAME = 0;

  /** Constant for Last Name column */
  public static final int LAST_NAME = 1;

  /** Constant for Batting Average column */
  public static final int BATTING_AVERAGE = 2;

  /** Constant for ascending */
  public static final int ASCENDING = 0;

  /** Constant for descending */
  public static final int DESCENDING = 1;

  private int column;
  private int direction;

  /**
   * Compares two Player objects
   * 
   * @param obj1 the first Player
   * @param obj2 the second Player
   * @return int
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  public int compare(Object obj1, Object obj2) {
    int rc = 0;
    Player p1 = (Player) obj1;
    Player p2 = (Player) obj2;

    // Determine which field to sort on, then sort
    // on that field
    switch (column) {
    case FIRST_NAME:
      rc = p1.getFirstName().compareTo(p2.getFirstName());
      break;
    case LAST_NAME:
      rc = p1.getLastName().compareTo(p2.getLastName());
      break;
    case BATTING_AVERAGE:
      rc = (p1.getBattingAverage() < p2.getBattingAverage()) ? -1 : 1;
      break;
    }

    // Check the direction for sort and flip the sign
    // if appropriate
    if (direction == DESCENDING) {
      rc = -rc;
    }
    return rc;
  }

  /**
   * Sets the column for sorting
   * 
   * @param column the column
   */
  public void setColumn(int column) {
    this.column = column;
  }

  /**
   * Sets the direction for sorting
   * 
   * @param direction the direction
   */
  public void setDirection(int direction) {
    this.direction = direction;
  }

  /**
   * Reverses the direction
   */
  public void reverseDirection() {
    direction = 1 - direction;
  }
}
