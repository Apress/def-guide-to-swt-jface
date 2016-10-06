package examples.ch11;

import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * This class supports multiline comments. It turns comments green.
 */
public class MultiLineCommentListener implements LineStyleListener {
  // Markers for multiline comments
  private static final String COMMENT_START = "/*";
  private static final String COMMENT_END = "*/";

  // Color for comments
  private static final Color COMMENT_COLOR = Display.getCurrent().getSystemColor(
      SWT.COLOR_DARK_GREEN);

  // Offsets for all multiline comments
  List commentOffsets;

  /**
   * MultilineCommentListener constructor
   */
  public MultiLineCommentListener() {
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

    // Go through all the instances of COMMENT_START
    for (int pos = text.indexOf(COMMENT_START); pos > -1; pos = text.indexOf(
        COMMENT_START, pos)) {
      // offsets[0] holds the COMMENT_START offset
      // and COMMENT_END holds the ending offset
      int[] offsets = new int[2];
      offsets[0] = pos;

      // Find the corresponding end comment.
      pos = text.indexOf(COMMENT_END, pos);

      // If no corresponding end comment, use the end of the text
      offsets[1] = pos == -1 ? text.length() - 1 : pos + COMMENT_END.length() - 1;
      pos = offsets[1];

      // Add the offsets to the collection
      commentOffsets.add(offsets);
    }
  }

  /**
   * Called by StyledText to get the styles for a line
   * 
   * @param event the event
   */
  public void lineGetStyle(LineStyleEvent event) {
    // Create a collection to hold the StyleRanges
    List styles = new ArrayList();

    // Store the length for convenience
    int length = event.lineText.length();

    for (int i = 0, n = commentOffsets.size(); i < n; i++) {
      int[] offsets = (int[]) commentOffsets.get(i);

      // If starting offset is past current line--quit
      if (offsets[0] > event.lineOffset + length) break;

      // Check if we're inside a multiline comment
      if (offsets[0] <= event.lineOffset + length
          && offsets[1] >= event.lineOffset) {
        // Calculate starting offset for StyleRange
        int start = Math.max(offsets[0], event.lineOffset);

        // Calculate length for style range
        int len = Math.min(offsets[1], event.lineOffset + length) - start + 1;

        // Add the style range
        styles.add(new StyleRange(start, len, COMMENT_COLOR, null));
      }
    }

    // Copy all the ranges into the event
    event.styles = (StyleRange[]) styles.toArray(new StyleRange[0]);
  }
}
