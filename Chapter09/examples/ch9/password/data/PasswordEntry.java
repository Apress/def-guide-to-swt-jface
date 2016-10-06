package examples.ch9.password.data;

/**
 * This class contains the data for a single password entry
 */
public class PasswordEntry {
  private String category;
  private String name;
  private String userId;
  private String password;

  /**
   * Constructs a PasswordEntry
   */
  public PasswordEntry() {
    this(null, null, null, null);
  }

  /**
   * Constructs a PasswordEntry
   */
  public PasswordEntry(String newCategory, String newName, String newUserId,
      String newPassword) {
    setCategory(newCategory);
    setName(newName);
    setUserId(newUserId);
    setPassword(newPassword);
  }

  /**
   * Returns the category.
   * 
   * @return String
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the category
   * 
   * @param category The category to set.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Returns the name.
   * 
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name
   * 
   * @param name The name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the password.
   * 
   * @return String
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password
   * 
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns the userId.
   * 
   * @return String
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Sets the userId
   * 
   * @param userId The userId to set.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Clones an entry
   * 
   * @param entry
   */
  public void clone(PasswordEntry entry) {
    setCategory(entry.getCategory());
    setName(entry.getName());
    setUserId(entry.getUserId());
    setPassword(entry.getPassword());
  }
}
