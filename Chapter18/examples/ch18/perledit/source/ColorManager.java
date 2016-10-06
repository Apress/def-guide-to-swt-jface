package examples.ch18.perledit.source;

import java.util.*;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.Display;

/**
 * This class manages the colors. It uses a lazy initialization approach, only
 * creating the actual color if it's requested.
 */
public class ColorManager {
  public static final RGB BACKGROUND = new RGB(255, 255, 255);
  public static final RGB COMMENT = new RGB(0, 128, 0);
  public static final RGB DEFAULT = new RGB(0, 0, 0);
  public static final RGB KEYWORD = new RGB(0, 128, 128);
  public static final RGB NUMBER = new RGB(255, 0, 255);
  public static final RGB STRING = new RGB(255, 0, 0);

  // Map to store created colors, with the corresponding RGB as key
  private Map colors = new HashMap();

  /**
   * Gets a color
   * 
   * @param rgb the corresponding rgb
   * @return Color
   */
  public Color getColor(RGB rgb) {
    // Get the color from the map
    Color color = (Color) colors.get(rgb);
    if (color == null) {
      // Color hasn't been created yet; create and put in map
      color = new Color(Display.getCurrent(), rgb);
      colors.put(rgb, color);
    }
    return color;
  }

  /**
   * Dispose any created colors
   */
  public void dispose() {
    for (Iterator itr = colors.values().iterator(); itr.hasNext();)
      ((Color) itr.next()).dispose();
  }
}
