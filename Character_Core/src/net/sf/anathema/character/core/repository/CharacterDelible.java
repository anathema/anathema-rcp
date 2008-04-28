package net.sf.anathema.character.core.repository;

import java.io.IOException;

import net.sf.anathema.basics.repository.treecontent.deletion.ICloseHandler;
import net.sf.anathema.basics.repository.treecontent.deletion.ResourcePageDelible;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.IModelCache;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.repository.internal.CharacterElementCloseHandler;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbenchPage;

public class CharacterDelible extends ResourcePageDelible {

  private static ICloseHandler createHandler(ICharacterId id) {
    return new CharacterElementCloseHandler(id);
  }

  private final IModelCache cache;
  private final ICharacterId id;

  public CharacterDelible(ICharacterId id) {
    this(id, ModelCache.getInstance());
  }

  public CharacterDelible(ICharacterId id, IModelCache cache) {
    super(createHandler(id), (IContainer) id.getAdapter(IContainer.class));
    this.id = id;
    this.cache = cache;
  }

  @Override
  public void delete(IWorkbenchPage page, IProgressMonitor monitor) throws CoreException, IOException {
    super.delete(page, monitor);
    cache.clearAllModels(id);
  }
}