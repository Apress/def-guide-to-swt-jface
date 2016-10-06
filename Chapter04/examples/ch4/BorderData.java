package examples.ch4;

/**
 * Each control controlled by a BorderLayout can have a BorderData attached to
 * it, which controls its placement on the layout. Notice that the constructor is
 * private; we don't want people creating new BorderData objects. We have the
 * entire set of possibilities in the public static constants.
 */
public class BorderData {
  /** North */
  public static final BorderData NORTH = new BorderData("North");

  /** South */
  public static final BorderData SOUTH = new BorderData("South");

  /** East */
  public static final BorderData EAST = new BorderData("East");

  /** West */
  public static final BorderData WEST = new BorderData("West");

  /** Center */
  public static final BorderData CENTER = new BorderData("Center");

  private String name;

  private BorderData(String name) {
    this.name = name;
  }
}
