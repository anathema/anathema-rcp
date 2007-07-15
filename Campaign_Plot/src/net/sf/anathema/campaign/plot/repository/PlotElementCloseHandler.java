package net.sf.anathema.campaign.plot.repository;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

public class PlotElementCloseHandler {

  private final IEditorCloser closer;
  private final IPlotElementViewElement element;

  public PlotElementCloseHandler(IEditorCloser closer, IPlotElementViewElement element) {
    this.closer = closer;
    this.element = element;
  }

  public void closeIfRequired(IEditorReference reference) throws PartInitException {
    IPlotElementViewElement currentElement = element;
    closeIfRequired(reference, currentElement);
  }

  private boolean closeIfRequired(IEditorReference reference, IPlotElementViewElement currentElement)
      throws PartInitException {
    if (mustBeClosed(currentElement, reference)) {
      closer.close(reference.getEditor(false));
      return true;
    }
    IPlotChild newChild = (IPlotChild) reference.getEditorInput().getAdapter(IPlotChild.class);
    IPlotPart plotElement = currentElement.getPlotElement();
    if (newChild != null && plotElement.equals(newChild.getParent())) {
      closer.close(reference.getEditor(false));
      return true;
    }
    for (IPlotElementViewElement child : currentElement.getChildren()) {
      if (closeIfRequired(reference, child)) {
        return true;
      }
    }
    return false;
  }

  private boolean mustBeClosed(IPlotElementViewElement currentElement, IEditorReference reference) {
    // TODO 140514 Do not close unrelated plot editors with identical names
    return currentElement.getDisplayName().equals(reference.getName());
  }
}