package examples.ch14;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates the CheckboxTreeViewer
 */
public class CheckFileTree extends FileTree {
  /**
   * Configures the shell
   * 
   * @param shell the shell
   */
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText("Check File Tree");
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, false));

    // Add a checkbox to toggle whether the labels preserve case
    Button preserveCase = new Button(composite, SWT.CHECK);
    preserveCase.setText("&Preserve case");

    // Create the tree viewer to display the file tree
    final CheckboxTreeViewer tv = new CheckboxTreeViewer(composite);
    tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
    tv.setContentProvider(new FileTreeContentProvider());
    tv.setLabelProvider(new FileTreeLabelProvider());
    tv.setInput("root"); // pass a non-null that will be ignored

    // When user checks the checkbox, toggle the preserve case attribute
    // of the label provider
    preserveCase.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        boolean preserveCase = ((Button) event.widget).getSelection();
        FileTreeLabelProvider ftlp = (FileTreeLabelProvider) tv
            .getLabelProvider();
        ftlp.setPreserveCase(preserveCase);
      }
    });

    // When user checks a checkbox in the tree, check all its children
    tv.addCheckStateListener(new ICheckStateListener() {
      public void checkStateChanged(CheckStateChangedEvent event) {
        // If the item is checked . . .
        if (event.getChecked()) {
          // . . . check all its children
          tv.setSubtreeChecked(event.getElement(), true);
        }
      }
    });
    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new CheckFileTree().run();
  }
}