package net.sf.anathema.character.core.repository;

import java.io.IOException;

import net.sf.anathema.basics.repository.treecontent.deletion.ICloseHandler;
import net.sf.anathema.basics.repository.treecontent.deletion.ResourcePageDelible;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.repository.internal.CharacterElementCloseHandler;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbenchPage;

public class CharacterDelible extends ResourcePageDelible {

  private static ICloseHandler createHandler(ICharacterId id) {
    return new CharacterElementCloseHandler(id);
  }

  public CharacterDelible(ICharacterId id) {
    super(createHandler(id), (IContainer) id.getAdapter(IContainer.class));
  }

  @Override
  public void delete(IWorkbenchPage page, IProgressMonitor monitor) throws CoreException, IOException {
    // TODO: Case 221: Anhand der CharacterId die Models aus dem Cache entfernen.
    super.delete(page, monitor);
  }
}