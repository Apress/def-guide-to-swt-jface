package examples.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates groups
 */
public class GroupExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout());

    // Create the first group
    Group group1 = new Group(shell, SWT.SHADOW_IN);
    group1.setText("Who's your favorite?");
    group1.setLayout(new RowLayout(SWT.VERTICAL));
    new Button(group1, SWT.RADIO).setText("John");
    new Button(group1, SWT.RADIO).setText("Paul");
    new Button(group1, SWT.RADIO).setText("George");
    new Button(group1, SWT.RADIO).setText("Ringo");

    // Create the second group
    Group group2 = new Group(shell, SWT.NO_RADIO_GROUP);
    group2.setText("Who's your favorite?");
    group2.setLayout(new RowLayout(SWT.VERTICAL));
    new Button(group2, SWT.RADIO).setText("Barry");
    new Button(group2, SWT.RADIO).setText("Robin");
    new Button(group2, SWT.RADIO).setText("Maurice");
    
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}