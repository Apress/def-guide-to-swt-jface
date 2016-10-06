package examples.ch11;

import java.util.*;

/**
 * This class contains information for syntax coloring and styling for an
 * extension
 */
public class SyntaxData {
  private String extension;
  private Collection keywords;
  private String punctuation;
  private String comment;
  private String multiLineCommentStart;
  private String multiLineCommentEnd;

  /**
   * Constructs a SyntaxData
   * 
   * @param extension the extension
   */
  public SyntaxData(String extension) {
    this.extension = extension;
  }

  /**
   * Gets the extension
   * 
   * @return String
   */
  public String getExtension() {
    return extension;
  }

  /**
   * Gets the comment
   * 
   * @return String
   */
  public String getComment() {
    return comment;
  }

  /**
   * Sets the comment
   * 
   * @param comment The comment to set.
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Gets the keywords
   * 
   * @return Collection
   */
  public Collection getKeywords() {
    return keywords;
  }

  /**
   * Sets the keywords
   * 
   * @param keywords The keywords to set.
   */
  public void setKeywords(Collection keywords) {
    this.keywords = keywords;
  }

  /**
   * Gets the multi-line comment end
   * 
   * @return String
   */
  public String getMultiLineCommentEnd() {
    return multiLineCommentEnd;
  }

  /**
   * Sets the multi-line comment end
   * 
   * @param multiLineCommentEnd The multiLineCommentEnd to set.
   */
  public void setMultiLineCommentEnd(String multiLineCommentEnd) {
    this.multiLineCommentEnd = multiLineCommentEnd;
  }

  /**
   * Gets the multi-line comment start
   * 
   * @return String
   */
  public String getMultiLineCommentStart() {
    return multiLineCommentStart;
  }

  /**
   * Sets the multi-line comment start
   * 
   * @param multiLineCommentStart The multiLineCommentStart to set.
   */
  public void setMultiLineCommentStart(String multiLineCommentStart) {
    this.multiLineCommentStart = multiLineCommentStart;
  }

  /**
   * Gets the punctuation
   * 
   * @return String
   */
  public String getPunctuation() {
    return punctuation;
  }

  /**
   * Sets the punctuation
   * 
   * @param punctuation The punctuation to set.
   */
  public void setPunctuation(String punctuation) {
    this.punctuation = punctuation;
  }
}
