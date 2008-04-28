package net.sf.anathema.character.core.repository.internal;

import java.io.IOException;

import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.IModelCache;
import net.sf.anathema.character.core.repository.CharacterDelible;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.junit.Before;
import org.junit.Test;

public class CharacterDelibleTest {

  private CharacterDelible characterDelible;
  private ICharacterId id;
  private IModelCache cache;

  @Before
  public void createDelible() {
    IContainer folder = EasyMock.createNiceMock(IContainer.class);
    id = new CharacterId(folder);
    cache = EasyMock.createMock(IModelCache.class);
    characterDelible = new CharacterDelible(id, cache);
  }

  @Test
  public void deletesIdFromCache() throws CoreException, IOException {
    cache.clearAllModels(id);
    IWorkbenchPage page = EasyMock.createNiceMock(IWorkbenchPage.class);
    EasyMock.expect(page.getEditorReferences()).andReturn(new IEditorReference[0]);
    EasyMock.replay(page, cache);
    characterDelible.delete(page, null);
    EasyMock.verify(cache);
  }
}