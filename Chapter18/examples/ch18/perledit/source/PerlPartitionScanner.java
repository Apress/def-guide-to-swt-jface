package examples.ch18.perledit.source;

import org.eclipse.jface.text.rules.*;

/**
 * This class scans a document and partitions it
 */
public class PerlPartitionScanner extends RuleBasedPartitionScanner {
  // Create a partition for comments, and leave the rest for code
  public static final String COMMENT = "comment";
  public static final String[] TYPES = { COMMENT};

  /**
   * PerlPartitionScanner constructor
   */
  public PerlPartitionScanner() {
    super();

    // Create the token for comment partitions
    IToken comment = new Token(COMMENT);

    // Set the rule--anything from # to the end of the line is a comment
    setPredicateRules(new IPredicateRule[] { new EndOfLineRule("#", comment)});
  }
}
