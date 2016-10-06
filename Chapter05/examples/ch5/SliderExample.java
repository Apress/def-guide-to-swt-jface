package examples.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates Sliders
 */
public class SliderExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout(1, false));

    // Create a horizontal slider, accepting the defaults
    new Slider(shell, SWT.HORIZONTAL);
    
    // Create a vertical slider and set its properties
    Slider slider = new Slider(shell, SWT.VERTICAL);
    slider.setMinimum(0);
    slider.setMaximum(100);
    slider.setIncrement(5);
    slider.setPageIncrement(20);
    slider.setSelection(75);
    
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}