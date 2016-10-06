package examples.ch20;

/**
 * This class contains an entry in the Address Book
 */
public class AddressEntry {
  private String lastName;
  private String firstName;
  private String email;

  /**
   * Gets the e-mail
   * 
   * @return String
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the e-mail
   * 
   * @param email The email to set.
   */
  public void setEmail(String email) {
    this.email = email;
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
   * Sets the first name
   * 
   * @param firstName The firstName to set.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name
   * 
   * @return String
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name
   * 
   * @param lastName The lastName to set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}