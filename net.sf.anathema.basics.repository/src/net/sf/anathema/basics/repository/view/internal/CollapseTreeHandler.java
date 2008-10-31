package net.sf.anathema.basics.repository.view.internal;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

public class CollapseTreeHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ICollapsableTree tree = (ICollapsableTree) HandlerUtil.getActivePart(event);
    tree.collapseAll();
    return null;
  }
}