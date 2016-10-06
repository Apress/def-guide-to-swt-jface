package examples.ch16;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This action class determines whether to show the book count
 */
public class ShowBookCountAction extends Action {
  public ShowBookCountAction() {
    super("&Show Book Count@Ctrl+C", IAction.AS_CHECK_BOX);
    setChecked(true);
    setImageDescriptor(ImageDescriptor.createFromFile(ShowBookCountAction.class,
        "/images/count.gif"));
    setDisabledImageDescriptor(ImageDescriptor.createFromFile(
        ShowBookCountAction.class, "/images/disabledCount.gif"));
  }
}