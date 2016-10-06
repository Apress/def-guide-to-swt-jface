package examples.ch16;

import java.io.*;
import java.util.*;

/**
 * This class holds all the books in a library. It also handles loading from and
 * saving to disk
 */
public class Library {
  private static final String SEP = "|";

  // The filename
  private String filename;

  // The books
  private Collection books;

  // The dirty flag
  private boolean dirty;

  /**
   * Library constructor Note the signature :-)
   */
  public Library() {
    books = new LinkedList();
  }

  /**
   * Loads the library from a file
   * 
   * @param filename the filename
   * @throws IOException
   */
  public void load(String filename) throws IOException {
    BufferedReader in = new BufferedReader(new LineNumberReader(new FileReader(
        filename)));
    String line;
    while ((line = in.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(line, SEP);
      Book book = null;
      if (st.hasMoreTokens()) book = new Book(st.nextToken());
      if (st.hasMoreTokens()) book.checkOut(st.nextToken());
      if (book != null) add(book);
    }
    in.close();
    this.filename = filename;
    dirty = false;
  }

  /**
   * Saves the library to a file
   * 
   * @param filename the filename
   * @throws IOException
   */
  public void save(String filename) throws IOException {
    BufferedWriter out = new BufferedWriter(new FileWriter(filename));
    for (Iterator itr = books.iterator(); itr.hasNext();) {
      Book book = (Book) itr.next();
      out.write(book.getTitle());
      out.write('|');
      out.write(book.getCheckedOutTo() == null ? "" : book.getCheckedOutTo());
      out.write('\r');
    }
    out.close();
    this.filename = filename;
    dirty = false;
  }

  /**
   * Adds a book
   * 
   * @param book the book to add
   * @return boolean
   */
  public boolean add(Book book) {
    boolean added = books.add(book);
    if (added) setDirty();
    return added;
  }

  /**
   * Removes a book
   * 
   * @param book the book to remove
   */
  public void remove(Book book) {
    books.remove(book);
    setDirty();
  }

  /**
   * Gets the books
   * 
   * @return Collection
   */
  public Collection getBooks() {
    return Collections.unmodifiableCollection(books);
  }

  /**
   * Gets the file name
   * 
   * @return String
   */
  public String getFileName() {
    return filename;
  }

  /**
   * Gets whether this file is dirty
   * 
   * @return boolean
   */
  public boolean isDirty() {
    return dirty;
  }

  /**
   * Sets this file as dirty
   */
  public void setDirty() {
    dirty = true;
  }
}