package examples.ch18.perledit.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;

import examples.ch18.perledit.Constants;

/**
 * This preference page shows preferences for the text
 */
public class TextPreferencePage extends FieldEditorPreferencePage {
  private FontFieldEditor fontFieldEditor;
  private BooleanFieldEditor wrapFieldEditor;

  /**
   * TextPreferencePage constructor
   */
  public TextPreferencePage() {
    super(GRID);
  }

  /**
   * Creates the field editors
   */
  protected void createFieldEditors() {
    // Add the field for the font
    fontFieldEditor = new FontFieldEditor(Constants.FONT, "Font:", "Font",
        getFieldEditorParent());
    addField(fontFieldEditor);

    // Add the field for word wrap
    wrapFieldEditor = new BooleanFieldEditor(Constants.WRAP, "Word Wrap",
        getFieldEditorParent());
    addField(wrapFieldEditor);
  }
}
