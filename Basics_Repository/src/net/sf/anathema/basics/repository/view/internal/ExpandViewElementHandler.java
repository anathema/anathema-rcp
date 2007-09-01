package net.sf.anathema.basics.repository.view.internal;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExpandViewElementHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchPart part = HandlerUtil.getActivePart(event);
    IExpandableTree tree = (IExpandableTree) part;
    IViewElement element = (IViewElement) ((StructuredSelection) HandlerUtil.getCurrentSelection(event)).getFirstElement();
    tree.expand(element);
    return null;
  }
}