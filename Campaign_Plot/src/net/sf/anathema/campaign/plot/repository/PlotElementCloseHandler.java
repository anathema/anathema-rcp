package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.basics.item.editor.EditorCloser;
import net.sf.anathema.basics.item.editor.IEditorCloser;
import net.sf.anathema.basics.jface.IFileEditorInput;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

public class PlotElementCloseHandler {

  private final IEditorCloser closer;
  private final IPlotElementViewElement element;

  public PlotElementCloseHandler(IPlotElementViewElement element) {
    this(new EditorCloser(), element);
  }

  public PlotElementCloseHandler(IEditorCloser closer, IPlotElementViewElement element) {
    this.closer = closer;
    this.element = element;
  }

  public void closeIfRequired(IEditorReference reference) throws PartInitException {
    IEditorInput editorInput = reference.getEditorInput();
    IFileEditorInput input = (IFileEditorInput) editorInput.getAdapter(IFileEditorInput.class);
    if (input != null && element.isPartOf(input.getFile().getParent())) {
      closer.close(reference.getEditor(false));
      return;
    }
    IPlotPart plotElement = element.getPlotElement();
    IPlotChild newChild = (IPlotChild) editorInput.getAdapter(IPlotChild.class);
    if (newChild != null && plotElement.getRoot().equals(newChild.getParent().getRoot())) {
      closer.close(reference.getEditor(false));
    }
  }
}