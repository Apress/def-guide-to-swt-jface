package examples.ch8.xmlview;

import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import org.jdom.*;

/**
 * This class contains the controls for a tab in the XmlView application
 */
public class XmlViewTab extends Composite {
  private XmlDocument document;
  private Tree tree;
  private Table table;

  /**
   * Constructs an XmlViewTab
   * 
   * @param tabFolder the parent TabFolder
   * @param document the document associated with this tab
   */
  public XmlViewTab(TabFolder tabFolder, XmlDocument document) {
    super(tabFolder, SWT.NONE);
    this.document = document;

    // Create the widgets to display
    createContents();

    // Refresh the widgets, using the document
    refreshTree(document.getDocument().getRootElement());
  }

  /**
   * Gets the text for this tab
   */
  public String getText() {
    return document.getFilename();
  }

  /**
   * Creates the contents for this tab
   */
  private void createContents() {
    setLayout(new FormLayout());

    // Create the sash that will separate the tree and the table
    final Sash sash = new Sash(this, SWT.VERTICAL);
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(50, 0);
    sash.setLayoutData(data);
    sash.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ((FormData) sash.getLayoutData()).left = new FormAttachment(0, event.x);
        sash.getParent().layout();
      }
    });

    // Create the tree
    tree = new Tree(this, SWT.SINGLE | SWT.BORDER);
    data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(sash, 0);
    tree.setLayoutData(data);
    tree.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // When the user clicks an item in the tree,
        // we update the table to show the attributes for the item
        if (tree.getSelectionCount() == 1) {
          TreeItem item = tree.getSelection()[0];
          refreshTable(item.getData());
        }
      }
    });

    // Create the table
    table = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
    data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(sash, 0);
    data.right = new FormAttachment(100, 0);
    table.setLayoutData(data);
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    new TableColumn(table, 0).setText("Name");
    new TableColumn(table, 1).setText("Value");
  }

  /**
   * Refreshes the tree with the data from the XML file
   * 
   * @param root the root XML element
   */
  private void refreshTree(Element root) {
    // Turn off redrawing and remove all items
    tree.setRedraw(false);
    tree.removeAll();

    // Add the root element and recursively add the children
    TreeItem item = new TreeItem(tree, SWT.NONE);
    item.setData(root); // Set the actual element into the tree item
    item.setText(root.getName());
    addChildren(item);

    // Turn drawing back on
    tree.setRedraw(true);
  }

  /**
   * Refreshes the table with the data from the element selected in the tree
   * 
   * @param obj the data from the selected item
   */
  private void refreshTable(Object obj) {
    // Turn off redrawing and remove all item
    table.setRedraw(false);
    table.removeAll();

    // Get the attributes for the selected element
    // Add the attributes as name and value
    Element element = (Element) obj;
    for (Iterator itr = element.getAttributes().iterator(); itr.hasNext();) {
      Attribute attribute = (Attribute) itr.next();
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText(0, attribute.getName());
      item.setText(1, attribute.getValue());
    }

    // Repack the columns, so they're sized to fit the content
    TableColumn[] cols = table.getColumns();
    for (int i = 0, n = cols.length; i < n; i++) {
      cols[i].pack();
    }

    // Turn drawing back on
    table.setRedraw(true);
  }

  /**
   * Adds the children of an XML element
   * 
   * @param parent the parent item in the tree
   */
  private void addChildren(TreeItem parent) {
    // Recursively add each child and its children to the tree
    Element element = (Element) parent.getData();
    for (Iterator itr = element.getChildren().iterator(); itr.hasNext();) {
      Element child = (Element) itr.next();
      TreeItem item = new TreeItem(parent, SWT.NONE);
      item.setData(child);
      item.setText(child.getName());
      addChildren(item);
    }
  }
}
