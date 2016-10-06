package examples.ch12;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.printing.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates printing images
 */
public class ImagePrinterExample {
  /**
   * The application entry point
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display, SWT.NONE);

    try {
      // Prompt the user for an image file
      FileDialog fileChooser = new FileDialog(shell, SWT.OPEN);
      String fileName = fileChooser.open();

      if (fileName == null) { return; }

      // Load the image
      ImageLoader loader = new ImageLoader();
      ImageData[] imageData = loader.load(fileName);

      if (imageData.length > 0) {
        // Show the Choose Printer dialog
        PrintDialog dialog = new PrintDialog(shell, SWT.NULL);
        PrinterData printerData = dialog.open();

        if (printerData != null) {
          // Create the printer object
          Printer printer = new Printer(printerData);
  
          // Calculate the scale factor between the screen resolution and printer
          // resolution in order to correctly size the image for the printer
          Point screenDPI = display.getDPI();
          Point printerDPI = printer.getDPI();
          int scaleFactor = printerDPI.x / screenDPI.x;
  
          // Determine the bounds of the entire area of the printer
          Rectangle trim = printer.computeTrim(0, 0, 0, 0);

          // Start the print job
          if (printer.startJob(fileName)) {
            if (printer.startPage()) {
              GC gc = new GC(printer);
              Image printerImage = new Image(printer, imageData[0]);
              
              // Draw the image
              gc.drawImage(printerImage, 0, 0, imageData[0].width,
                imageData[0].height, -trim.x, -trim.y, 
                scaleFactor * imageData[0].width, 
                scaleFactor * imageData[0].height);
  
              // Clean up
              printerImage.dispose();
              gc.dispose();
              printer.endPage();
            }
          }
          // End the job and dispose the printer
          printer.endJob();
          printer.dispose();
        }
      }
    } catch (Exception e) {
      MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
      messageBox.setMessage("Error printing test image");
      messageBox.open();
    }
  }
}
