package examples.ch18.perledit.source;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * This class detects words in a perl file
 */
public class PerlWordDetector implements IWordDetector {
  /**
   * Gets whether the specified character is the start of a word
   * 
   * @return boolean
   */
  public boolean isWordStart(char c) {
    for (int i = 0, n = PerlSyntax.KEYWORDS.length; i < n; i++)
      if (c == ((String) PerlSyntax.KEYWORDS[i]).charAt(0)) return true;
    return false;
  }

  /**
   * Gets whether the specified character is part of a word
   * 
   * @return boolean
   */
  public boolean isWordPart(char c) {
    for (int i = 0, n = PerlSyntax.KEYWORDS.length; i < n; i++)
      if (((String) PerlSyntax.KEYWORDS[i]).indexOf(c) != -1) return true;
    return false;
  }
}
