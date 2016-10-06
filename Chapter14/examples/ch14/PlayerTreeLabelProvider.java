package examples.ch14;

import org.eclipse.swt.graphics.Image;

/**
 * This class provides the labels for the PlayerTableTree application
 */
public class PlayerTreeLabelProvider extends PlayerLabelProvider {
  /**
   * Gets the image for the specified column
   * 
   * @param arg0 the player or team
   * @param arg1 the column
   * @return Image
   */
  public Image getColumnImage(Object arg0, int arg1) {
    // Teams have no image
    if (arg0 instanceof Player) return super.getColumnImage(arg0, arg1);
    return null;
  }

  /**
   * Gets the text for the specified column
   * 
   * @param arg0 the player or team
   * @param arg1 the column
   * @return String
   */
  public String getColumnText(Object arg0, int arg1) {
    if (arg0 instanceof Player) return super.getColumnText(arg0, arg1);
    Team team = (Team) arg0;
    return arg1 == 0 ? team.getYear() + " " + team.getName() : "";
  }
}