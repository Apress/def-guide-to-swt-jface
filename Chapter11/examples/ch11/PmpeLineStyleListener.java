package examples.ch11;

import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * This class performs the syntax highlighting and styling for Pmpe
 */
public class PmpeLineStyleListener implements LineStyleListener {
  // Colors
  private static final Color COMMENT_COLOR = Display.getCurrent().getSystemColor(
      SWT.COLOR_DARK_GREEN);
  private static final Color COMMENT_BACKGROUND = Display.getCurrent()
      .getSystemColor(SWT.COLOR_GRAY);
  private static final Color PUNCTUATION_COLOR = Display.getCurrent()
      .getSystemColor(SWT.COLOR_DARK_CYAN);
  private static final Color KEYWORD_COLOR = Display.getCurrent().getSystemColor(
      SWT.COLOR_DARK_MAGENTA);

  // Holds the syntax data
  private SyntaxData syntaxData;

  // Holds the offsets for all multiline comments
  List commentOffsets;

  /**
   * PmpeLineStyleListener constructor
   * 
   * @param syntaxData the syntax data to use
   */
  public PmpeLineStyleListener(SyntaxData syntaxData) {
    this.syntaxData = syntaxData;
    commentOffsets = new LinkedList();
  }

  /**
   * Refreshes the offsets for all multiline comments in the parent StyledText.
   * The parent StyledText should call this whenever its text is modified. Note
   * that this code doesn't ignore comment markers inside strings.
   * 
   * @param text the text from the StyledText
   */
  public void refreshMultilineComments(String text) {
    // Clear any stored offsets
    commentOffsets.clear();

    if (syntaxData != null) {
      // Go through all the instances of COMMENT_START
      for (int pos = text.indexOf(syntaxData.getMultiLineCommentStart()); pos > -1; pos = text
          .indexOf(syntaxData.getMultiLineCommentStart(), pos)) {
        // offsets[0] holds the COMMENT_START offset
        // and COMMENT_END holds the ending offset
        int[] offsets = new int[2];
        offsets[0] = pos;

        // Find the corresponding end comment.
        pos = text.indexOf(syntaxData.getMultiLineCommentEnd(), pos);

        // If no corresponding end comment, use the end of the text
        offsets[1] = pos == -1 ? text.length() - 1 : pos
            + syntaxData.getMultiLineCommentEnd().length() - 1;
        pos = offsets[1];

        // Add the offsets to the collection
        commentOffsets.add(offsets);
      }
    }
  }

  /**
   * Checks to see if the specified section of text begins inside a multiline
   * comment. Returns the index of the closing comment, or the end of the line if
   * the whole line is inside the comment. Returns -1 if the line doesn't begin
   * inside a comment.
   * 
   * @param start the starting offset of the text
   * @param length the length of the text
   * @return int
   */
  private int getBeginsInsideComment(int start, int length) {
    // Assume section doesn't being inside a comment
    int index = -1;

    // Go through the multiline comment ranges
    for (int i = 0, n = commentOffsets.size(); i < n; i++) {
      int[] offsets = (int[]) commentOffsets.get(i);

      // If starting offset is past range, quit
      if (offsets[0] > start + length) break;

      // Check to see if section begins inside a comment
      if (offsets[0] <= start && offsets[1] >= start) {
        // It does; determine if the closing comment marker is inside
        // this section
        index = offsets[1] > start + length ? start + length : offsets[1]
            + syntaxData.getMultiLineCommentEnd().length() - 1;
      }
    }
    return index;
  }

  /**
   * Called by StyledText to get styles for a line
   */
  public void lineGetStyle(LineStyleEvent event) {
    // Only do styles if syntax data has been loaded
    if (syntaxData != null) {
      // Create collection to hold the StyleRanges
      List styles = new ArrayList();

      int start = 0;
      int length = event.lineText.length();

      // Check if line begins inside a multiline comment
      int mlIndex = getBeginsInsideComment(event.lineOffset, event.lineText
          .length());
      if (mlIndex > -1) {
        // Line begins inside multiline comment; create the range
        styles.add(new StyleRange(event.lineOffset, mlIndex - event.lineOffset,
            COMMENT_COLOR, COMMENT_BACKGROUND));
        start = mlIndex;
      }
      // Do punctuation, single-line comments, and keywords
      while (start < length) {
        // Check for multiline comments that begin inside this line
        if (event.lineText.indexOf(syntaxData.getMultiLineCommentStart(), start) == start) {
          // Determine where comment ends
          int endComment = event.lineText.indexOf(syntaxData
              .getMultiLineCommentEnd(), start);

          // If comment doesn't end on this line, extend range to end of line
          if (endComment == -1)
            endComment = length;
          else
            endComment += syntaxData.getMultiLineCommentEnd().length();
          styles.add(new StyleRange(event.lineOffset + start, endComment - start,
              COMMENT_COLOR, COMMENT_BACKGROUND));

          // Move marker
          start = endComment;
        }
        // Check for single line comments
        else if (event.lineText.indexOf(syntaxData.getComment(), start) == start) {
          // Comment rest of line
          styles.add(new StyleRange(event.lineOffset + start, length - start,
              COMMENT_COLOR, COMMENT_BACKGROUND));

          // Move marker
          start = length;
        }
        // Check for punctuation
        else if (syntaxData.getPunctuation()
            .indexOf(event.lineText.charAt(start)) > -1) {
          // Add range for punctuation
          styles.add(new StyleRange(event.lineOffset + start, 1,
              PUNCTUATION_COLOR, null));
          ++start;
        } else if (Character.isLetter(event.lineText.charAt(start))) {
          // Get the next word
          StringBuffer buf = new StringBuffer();
          int i = start;

          // Call any consecutive letters a word
          for (; i < length && Character.isLetter(event.lineText.charAt(i)); i++) {
            buf.append(event.lineText.charAt(i));
          }

          // See if the word is a keyword
          if (syntaxData.getKeywords().contains(buf.toString())) {
            // It's a keyword; create the StyleRange
            styles.add(new StyleRange(event.lineOffset + start, i - start,
                KEYWORD_COLOR, null, SWT.BOLD));
          }
          // Move the marker to the last char (the one that wasn't a letter)
          // so it can be retested in the next iteration through the loop
          start = i;
        } else
          // It's nothing we're interested in; advance the marker
          ++start;
      }

      // Copy the StyleRanges back into the event
      event.styles = (StyleRange[]) styles.toArray(new StyleRange[0]);
    }
  }
}
