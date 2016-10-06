package examples.ch17;

import org.eclipse.jface.preference.*;

/**
 * This class demonstrates field editors
 */
public class FieldEditorPageOne extends FieldEditorPreferencePage {
  public FieldEditorPageOne() {
    // Use the "flat" layout
    super(FLAT);
  }

  /**
   * Creates the field editors
   */
  protected void createFieldEditors() {
    // Add a boolean field
    BooleanFieldEditor bfe = new BooleanFieldEditor("myBoolean", "Boolean",
        getFieldEditorParent());
    addField(bfe);

    // Add a color field
    ColorFieldEditor cfe = new ColorFieldEditor("myColor", "Color:",
        getFieldEditorParent());
    addField(cfe);

    // Add a directory field
    DirectoryFieldEditor dfe = new DirectoryFieldEditor("myDirectory",
        "Directory:", getFieldEditorParent());
    addField(dfe);

    // Add a file field
    FileFieldEditor ffe = new FileFieldEditor("myFile", "File:",
        getFieldEditorParent());
    addField(ffe);

    // Add a font field
    FontFieldEditor fontFe = new FontFieldEditor("myFont", "Font:",
        getFieldEditorParent());
    addField(fontFe);

    // Add a radio group field
    RadioGroupFieldEditor rfe = new RadioGroupFieldEditor("myRadioGroup",
        "Radio Group", 2, new String[][] { { "First Value", "first"},
            { "Second Value", "second"}, { "Third Value", "third"},
            { "Fourth Value", "fourth"}}, getFieldEditorParent(), true);
    addField(rfe);

    // Add a path field
    PathEditor pe = new PathEditor("myPath", "Path:", "Choose a Path",
        getFieldEditorParent());
    addField(pe);
  }
}
