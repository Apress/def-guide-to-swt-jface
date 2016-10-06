package examples.ch14;

import org.eclipse.swt.graphics.RGB;

/**
 * This class represents a person
 */
public class Person {
  private String name;
  private boolean male;
  private Integer ageRange;
  private RGB shirtColor;

  /**
   * @return Returns the ageRange.
   */
  public Integer getAgeRange() {
    return ageRange;
  }

  /**
   * @param ageRange The ageRange to set.
   */
  public void setAgeRange(Integer ageRange) {
    this.ageRange = ageRange;
  }

  /**
   * @return Returns the male.
   */
  public boolean isMale() {
    return male;
  }

  /**
   * @param male The male to set.
   */
  public void setMale(boolean male) {
    this.male = male;
  }

  /**
   * @return Returns the name.
   */
  public String getName() {
    return name;
  }

  /**
   * @param name The name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return Returns the shirtColor.
   */
  public RGB getShirtColor() {
    return shirtColor;
  }

  /**
   * @param shirtColor The shirtColor to set.
   */
  public void setShirtColor(RGB shirtColor) {
    this.shirtColor = shirtColor;
  }
}