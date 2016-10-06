package examples.ch8.xmlview;

import java.io.*;

import org.jdom.*;
import org.jdom.input.*;

/**
 * This class wraps an XML file
 */
public class XmlDocument {
  private String filename;
  private Document document;

  /**
   * Constructs an XmlDocument
   * 
   * @param filename the file name of the XML file
   */
  public XmlDocument(String filename) {
    this.filename = filename;
  }

  /**
   * Gets just the file name
   * 
   * @return String
   */
  public String getFilename() {
    return filename.substring(filename.lastIndexOf(File.separator) + 1);
  }

  /**
   * Opens and parses the file
   * 
   * @throws IOException if any problem opening or parsing
   */
  public void open() throws IOException {
    SAXBuilder builder = new SAXBuilder();
    try {
      document = builder.build(new FileInputStream(filename));
    } catch (JDOMException e) {
      throw new IOException(e.getMessage());
    }
  }

  /**
   * Gets the underlying JDOM Document object
   * 
   * @return Document
   */
  public Document getDocument() {
    return document;
  }
}
