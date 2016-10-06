package examples.ch6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates various listeners
 */
public class MultipleListenersExample implements HelpListener, VerifyListener,
    ModifyListener {

  // Constants used for conversions
  private static final double FIVE_NINTHS = 5.0 / 9.0;
  private static final double NINE_FIFTHS = 9.0 / 5.0;

  // Widgets used in the window
  private Text fahrenheit;
  private Text celsius;
  private Label help;

  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Temperatures");
    createContents(shell);
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Create the main window's contents
   * @param shell the main window
   */
  private void createContents(Shell shell) {
    shell.setLayout(new GridLayout(3, true));

    // Create the label and input box for Fahrenheit
    new Label(shell, SWT.LEFT).setText("Fahrenheit:");
    fahrenheit = new Text(shell, SWT.BORDER);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 2;
    fahrenheit.setLayoutData(data);

    // Set the context-sensitive help
    fahrenheit.setData("Type a temperature in Fahrenheit");

    // Add the listeners
    fahrenheit.addHelpListener(this);
    fahrenheit.addVerifyListener(this);
    fahrenheit.addModifyListener(this);

    // Create the label and input box for Celsius
    new Label(shell, SWT.LEFT).setText("Celsius:");
    celsius = new Text(shell, SWT.BORDER);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 2;
    celsius.setLayoutData(data);

    // Set the context-sensitive help
    celsius.setData("Type a temperature in Celsius");

    // Add the listeners
    celsius.addHelpListener(this);
    celsius.addVerifyListener(this);
    celsius.addModifyListener(this);

    // Create the area for help
    help = new Label(shell, SWT.LEFT | SWT.BORDER);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 3;
    help.setLayoutData(data);
  }

  /**
   * Called when user requests help
   */
  public void helpRequested(HelpEvent event) {
    // Get the help text from the widget and set it into the help label
    help.setText((String) event.widget.getData());
  }

  /**
   * Called when the user types into a text box, but before the text box gets
   * what the user typed
   */
  public void verifyText(VerifyEvent event) {
    // Assume we don't allow it
    event.doit = false;

    // Get the character typed
    char myChar = event.character;
    String text = ((Text) event.widget).getText();

    // Allow '-' if first character
    if (myChar == '-' && text.length() == 0) event.doit = true;

    // Allow 0-9
    if (Character.isDigit(myChar)) event.doit = true;

    // Allow backspace
    if (myChar == '\b') event.doit = true;
  }

  /**
   * Called when the user modifies the text in a text box
   */
  public void modifyText(ModifyEvent event) {
    // Remove all the listeners, so we don't enter any infinite loops
    celsius.removeVerifyListener(this);
    celsius.removeModifyListener(this);
    fahrenheit.removeVerifyListener(this);
    fahrenheit.removeModifyListener(this);

    // Get the widget whose text was modified
    Text text = (Text) event.widget;

    try {
      // Get the modified text
      int temp = Integer.parseInt(text.getText());

      // If they modified fahrenheit, convert to Celsius
      if (text == fahrenheit) {
        celsius.setText(String.valueOf((int) (FIVE_NINTHS * (temp - 32))));
      } else {
        // Convert to fahrenheit
        fahrenheit.setText(String.valueOf((int) (NINE_FIFTHS * temp + 32)));
      }
    } catch (NumberFormatException e) { /* Ignore */ }

    // Add the listeners back
    celsius.addVerifyListener(this);
    celsius.addModifyListener(this);
    fahrenheit.addVerifyListener(this);
    fahrenheit.addModifyListener(this);
  }

  /**
   * The application entry point
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new MultipleListenersExample().run();
  }
}
