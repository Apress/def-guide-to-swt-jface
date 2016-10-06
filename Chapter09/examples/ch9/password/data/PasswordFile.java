package examples.ch9.password.data;

import java.io.*;
import java.util.*;

import examples.ch9.password.Password;

/**
 * This class wraps a password file The file is stored as a properties file with
 * encrypted data
 */
public class PasswordFile {
  private static final String HASH = "hash";
  private static final String CATEGORY = ".category";
  private static final String USER_ID = ".userId";
  private static final String PASSWORD = ".password";

  // The categories become the root items in the table tree
  private Map categories = new HashMap();

  // The file name for this file
  private String filename;

  // Tracks whether this file has unsaved changes
  private boolean dirty;

  // The master password for this file
  private String password;

  /**
   * Sets the file name (including path)
   * 
   * @param filename the file name
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * Gets the file name
   * 
   * @return String
   */
  public String getFilename() {
    return filename;
  }

  /**
   * Adds an entry to the list of passwords
   * 
   * @param entry the entry to add
   * @return boolean
   */
  public boolean add(PasswordEntry entry) {
    dirty = true;
    return getEntries(entry.getCategory()).add(entry);
  }

  /**
   * Removes an entry from the list of passwords
   * 
   * @param entry the entry to remove
   * @return boolean
   */
  public boolean remove(PasswordEntry entry) {
    dirty = true;
    return getEntries(entry.getCategory()).remove(entry);
  }

  /**
   * Gets the list of entries for a category
   * 
   * @return List
   */
  public List getEntries(String category) {
    List entries = (List) categories.get(category);
    if (entries == null) {
      entries = new LinkedList();
      categories.put(category, entries);
    }
    return entries;
  }

  /**
   * Gets all the categories
   * 
   * @return Set
   */
  public Set getCategories() {
    return categories.keySet();
  }

  /**
   * Returns whether this file is "dirty"
   * 
   * @return boolean
   */
  public boolean isDirty() {
    return dirty;
  }

  /**
   * Sets whether this file is "dirty"
   * 
   * @param boolean dirty
   */
  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }

  /**
   * Saves this file
   * 
   * @throws IOException if file cannot be saved
   */
  public void save() throws IOException {
    if (filename == null) { throw new IllegalStateException(
        "Cannot save file without a file name."); }
    Properties props = new Properties();

    // Force a password to be entered, if it hasn't already
    if (password == null) {
      password = Password.getApp().getMasterPassword();
    }

    // If password is still null (they hit cancel), don't save
    if (password != null) {
      // Hash the password and store it
      props.setProperty(HASH, new String(SecurityHandler.getHash(password)));

      // Go through the categories
      for (Iterator itr = categories.keySet().iterator(); itr.hasNext();) {
        String key = (String) itr.next();
        List entries = getEntries(key);

        // Go through all the entries in this category
        for (int i = 0, n = entries.size(); i < n; i++) {
          PasswordEntry entry = (PasswordEntry) entries.get(i);
          String name = entry.getName();
          props.setProperty(name + CATEGORY, entry.getCategory());

          // Encrypt the user ID
          props.setProperty(name + USER_ID, SecurityHandler.encrypt(entry
              .getUserId(), password));

          // Encrypt the password
          props.setProperty(name + PASSWORD, SecurityHandler.encrypt(entry
              .getPassword(), password));
        }
      }
      // Save the file and turn off the dirty flag
      props.store(new FileOutputStream(filename), "Password File");
      dirty = false;
    } else {
      throw new IllegalStateException(
          "Cannot save file without a master password.");
    }
  }

  /**
   * Open (loads) this file
   * 
   * @throws IOException if file cannot be opened
   */
  public void open() throws IOException {
    if (filename == null) { throw new IllegalStateException(
        "Cannot open file without a file name."); }

    // Load the file into a Properties object
    Properties props = new Properties();
    props.load(new FileInputStream(filename));

    // Make user enter password for decryption
    password = Password.getApp().getMasterPassword();
    if (password != null) {
      // Verify the password
      if (SecurityHandler.checkHash(props.getProperty(HASH).getBytes(),
          SecurityHandler.getHash(password))) {
        // Password passed; load the entries, using password to decrypt
        for (Enumeration e = props.propertyNames(); e.hasMoreElements();) {
          String key = (String) e.nextElement();
          if (key.endsWith(PASSWORD)) {
            key = key.substring(0, key.length() - PASSWORD.length());
            List entries = getEntries(props.getProperty(key + CATEGORY));
            try {
              entries.add(new PasswordEntry(props.getProperty(key + CATEGORY),
                  key, SecurityHandler.decrypt(props.getProperty(key + USER_ID),
                      password), SecurityHandler.decrypt(props.getProperty(key
                      + PASSWORD), password)));
            } catch (IllegalStateException ex) {
              System.out.println("Failed to decrypt entry: " + key);
            }
          }
        }
      } else {
        // Password incorrect
        throw new IllegalStateException("Master password incorrect");
      }
    } else {
      // User canceled master password entry, so drop info
      props = null;
    }
  }
}
