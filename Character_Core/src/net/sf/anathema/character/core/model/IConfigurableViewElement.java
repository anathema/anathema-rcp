package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IConfigurableViewElement extends IViewElement {

  public void addChild(IViewElement child);

  public void openEditorForChild(IWorkbenchPage page, IEditorInput editorInput) throws PartInitException;
}