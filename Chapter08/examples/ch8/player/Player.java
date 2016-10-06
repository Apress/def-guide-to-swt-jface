package examples.ch8.player;

/**
 * This class represents a player.
 */
public class Player {
  private String firstName;
  private String lastName;
  private float battingAverage;

  /**
   * Constructs a Player
   * 
   * @param firstName the first name
   * @param lastName the last name
   * @param battingAverage the batting average
   */
  public Player(String firstName, String lastName, float battingAverage) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.battingAverage = battingAverage;
  }

  /**
   * Gets the batting average
   * 
   * @return float
   */
  public float getBattingAverage() {
    return battingAverage;
  }

  /**
   * Gets the first name
   * 
   * @return String
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the last name
   * 
   * @return String
   */
  public String getLastName() {
    return lastName;
  }
}
