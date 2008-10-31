package net.sf.anathema.basics.repository.linkage;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

public class RepositoryEditorLinkHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ILinker linker = (ILinker) HandlerUtil.getActivePart(event);
    linker.toggleLink();
    return null;
  }
}
