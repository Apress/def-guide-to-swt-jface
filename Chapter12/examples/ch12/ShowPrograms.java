package examples.ch12;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.*;

/**
 * This class shows the extensions on the system and their associated programs.
 */
public class ShowPrograms {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Show Programs");
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    shell.setLayout(new GridLayout(2, false));

    // Create the label and combo for the extensions
    new Label(shell, SWT.NONE).setText("Extension:");
    Combo extensionsCombo = new Combo(shell, SWT.BORDER | SWT.READ_ONLY);
    extensionsCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Create the label and the
    new Label(shell, SWT.NONE).setText("Program:");
    final Label programName = new Label(shell, SWT.NONE);
    programName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Fill the combo with the extensions on the system
    String[] extensions = Program.getExtensions();
    for (int i = 0, n = extensions.length; i < n; i++) {
      extensionsCombo.add(extensions[i]);
    }

    // Add a handler to get the selected extension, look up the associated
    // program, and display the program's name
    extensionsCombo.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Combo combo = (Combo) event.widget;

        // Get the program for the extension
        Program program = Program.findProgram(combo.getText());

        // Display the program's name
        programName.setText(program == null ? "(None)" : program.getName());
      }
    });

    // Create a list box to show all the programs on the system
    List allPrograms = new List(shell, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
        | SWT.V_SCROLL);
    GridData data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 2;
    allPrograms.setLayoutData(data);

    // Put all the known programs into the list box
    Program[] programs = Program.getPrograms();
    for (int i = 0, n = programs.length; i < n; i++) {
      String name = programs[i].getName();
      allPrograms.add(name);
      allPrograms.setData(name, programs[i]);
    }

    // Add a field for a data file
    new Label(shell, SWT.NONE).setText("Data File:");
    final Text dataFile = new Text(shell, SWT.BORDER);
    dataFile.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Double-clicking a program in the list launches the program
    allPrograms.addMouseListener(new MouseAdapter() {
      public void mouseDoubleClick(MouseEvent event) {
        List list = (List) event.widget;
        if (list.getSelectionCount() > 0) {
          String programName = list.getSelection()[0];
          Program program = (Program) list.getData(programName);
          program.execute(dataFile.getText());
        }
      }
    });

    // Let them use launch instead of execute
    Button launch = new Button(shell, SWT.PUSH);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 2;
    launch.setLayoutData(data);
    launch.setText("Use Program.launch() instead of Program.execute()");
    launch.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Use launch
        Program.launch(dataFile.getText());
      }
    });
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new ShowPrograms().run();
  }
}
