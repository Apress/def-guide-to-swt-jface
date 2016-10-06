package examples.ch12;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.printing.*;
import org.eclipse.swt.widgets.*;

import java.io.*;

/**
 * This class demonstrates printing text
 */
public class TextPrinterExample {
  /**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);

    // Get the file to print
    FileDialog fileChooser = new FileDialog(shell, SWT.OPEN);
    String fileName = fileChooser.open();
    if (fileName != null) {
      // Have user select a printer
      PrintDialog dialog = new PrintDialog(shell);
      PrinterData printerData = dialog.open();
      if (printerData != null) {
        // Create the printer
        Printer printer = new Printer(printerData);

        try {
          // Print the contents of the file
          new WrappingPrinter(printer, fileName, getFileContents(fileName)).print();
        } catch (Exception e) {
          MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
          mb.setMessage(e.getMessage());
          mb.open();
        }

        // Dispose the printer
        printer.dispose();
      }
    }
    display.dispose();
  }

  /**
   * Read in the file and return its contents
   * @param fileName
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  private String getFileContents(String fileName)
  throws FileNotFoundException, IOException {
    StringBuffer contents = new StringBuffer();
    BufferedReader reader = null;
    try {
      // Read in the file
      reader = new BufferedReader(new FileReader(fileName));
      while (reader.ready()) {
        contents.append(reader.readLine());
        contents.append("\n"); // Throw away LF chars, and just replace CR
      }
    } finally {
      if (reader != null) try {
        reader.close();
      } catch (IOException e) {}
    }
    return contents.toString();
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new TextPrinterExample().run();
  }
}

/**
 * This class performs the printing, wrapping text as necessary
 */
class WrappingPrinter {
  private Printer printer; // The printer
  private String fileName; // The name of the file to print
  private String contents; // The contents of the file to print
  private GC gc; // The GC to print on
  private int xPos, yPos; // The current x and y locations for print
  private Rectangle bounds; // The boundaries for the print
  private StringBuffer buf; // Holds a word at a time
  private int lineHeight; // The height of a line of text

  /**
   * WrappingPrinter constructor
   * @param printer the printer
   * @param fileName the fileName
   * @param contents the contents
   */
  WrappingPrinter(Printer printer, String fileName, String contents) {
    this.printer = printer;
    this.fileName = fileName;
    this.contents = contents;
  }
  
  /**
   * Prints the file
   */
  void print() {
    // Start the print job
    if (printer.startJob(fileName)) {
      // Determine print area, with margins
      bounds = computePrintArea(printer);
      xPos = bounds.x;
      yPos = bounds.y;
      
      // Create the GC
      gc = new GC(printer);
      
      // Determine line height
      lineHeight = gc.getFontMetrics().getHeight();
      
      // Determine tab width--use three spaces for tabs
      int tabWidth = gc.stringExtent("   ").x;
      
      // Print the text
      printer.startPage();
      buf = new StringBuffer();
      char c;
      for (int i = 0, n = contents.length(); i < n; i++) {
        // Get the next character
        c = contents.charAt(i);
        
        // Check for newline
        if (c == '\n') {
          printBuffer();
          printNewline();
        }
        // Check for tab
        else if (c == '\t') {
          xPos += tabWidth;
        }
        else {
          buf.append(c);
          // Check for space
          if (Character.isWhitespace(c)) {
            printBuffer();
          }
        }
      }
      printer.endPage();
      printer.endJob();
      gc.dispose();
    }
  }

  /**
   * Prints the contents of the buffer
   */
  void printBuffer() {
    // Get the width of the rendered buffer
    int width = gc.stringExtent(buf.toString()).x;
    
    // Determine if it fits
    if (xPos + width > bounds.x + bounds.width) {
      // Doesn't fit--wrap
      printNewline();
    }
    
    // Print the buffer
    gc.drawString(buf.toString(), xPos, yPos, false);
    xPos += width;
    buf.setLength(0);
  }

  /**
   * Prints a newline
   */
  void printNewline() {
    // Reset x and y locations to next line
    xPos = bounds.x;
    yPos += lineHeight;
    
    // Have we gone to the next page?
    if (yPos > bounds.y + bounds.height) {
      yPos = bounds.y;
      printer.endPage();
      printer.startPage();
    }
  }

  /**
   * Computes the print area, including margins
   * @param printer the printer
   * @return Rectangle
   */
  Rectangle computePrintArea(Printer printer) {
    // Get the printable area
    Rectangle rect = printer.getClientArea();
    
    // Compute the trim
    Rectangle trim = printer.computeTrim(0, 0, 0, 0);
    
    // Get the printer's DPI
    Point dpi = printer.getDPI();
    
    // Calculate the printable area, using 1 inch margins
    int left = trim.x + dpi.x;
    if (left < rect.x) left = rect.x;
    
    int right = (rect.width + trim.x + trim.width) - dpi.x;
    if (right > rect.width) right = rect.width;
    
    int top = trim.y + dpi.y;
    if (top < rect.y) top = rect.y;
    
    int bottom = (rect.height + trim.y + trim.height) - dpi.y;
    if (bottom > rect.height) bottom = rect.height;
    
    return new Rectangle(left, top, right - left, bottom - top);
  }
}