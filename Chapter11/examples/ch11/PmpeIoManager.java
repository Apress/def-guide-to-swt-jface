package examples.ch11;

import java.io.*;

/**
 * This class handles loading and saving files
 */
public class PmpeIoManager {
  /**
   * Gets a file (loads it) from the filesystem
   * 
   * @param filename the full path of the file
   * @return String
   * @throws IOException if file cannot be loaded
   */
  public static String getFile(String filename) throws IOException {
    InputStream in = new BufferedInputStream(new FileInputStream(filename));
    StringBuffer buf = new StringBuffer();
    int c;
    while ((c = in.read()) != -1) {
      buf.append((char) c);
    }
    return buf.toString();
  }

  /**
   * Saves a file
   * 
   * @param filename the full path of the file to save
   * @param data the data to save
   * @throws IOException if file cannot be saved
   */
  public static void saveFile(String filename, byte[] data) throws IOException {
    File outputFile = new File(filename);
    FileOutputStream out = new FileOutputStream(outputFile);
    out.write(data);
    out.close();
  }
}
