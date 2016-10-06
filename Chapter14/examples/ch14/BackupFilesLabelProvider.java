package examples.ch14;

import java.io.*;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * This class provides the labels for files
 */
public class BackupFilesLabelProvider implements ILabelProvider {
  /**
   * Returns the image
   * 
   * @param arg0 the file
   * @return Image
   */
  public Image getImage(Object arg0) {
    return null;
  }

  /**
   * Returns the name of the file
   * 
   * @param arg0 the name of the file
   * @return String
   */
  public String getText(Object arg0) {
    return ((File) arg0).getName();
  }

  /**
   * Adds a listener
   * 
   * @param arg0 the listener
   */
  public void addListener(ILabelProviderListener arg0) {
  // Throw it away
  }

  /**
   * Disposes any created resources
   */
  public void dispose() {
  // Nothing to dispose
  }

  /**
   * Returns whether changing this property for this element affects the label
   * 
   * @param arg0 the element
   * @param arg1 the property
   */
  public boolean isLabelProperty(Object arg0, String arg1) {
    return false;
  }

  /**
   * Removes a listener
   * 
   * @param arg0 the listener
   */
  public void removeListener(ILabelProviderListener arg0) {
  // Ignore
  }
}