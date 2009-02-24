package net.sf.anathema.character.core.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.DummyModel;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ModelCacheTest {

  private ModelCache instance;
  private ICharacterId id;

  @Before
  public void createModels() {
    instance = ModelCache.getInstance();
  }

  @Before
  public void createId() {
    IContainer folder = EasyMock.createNiceMock(IContainer.class);
    id = new CharacterId(folder);
  }

  @Test
  public void storesModels() throws Exception {
    IModelIdentifier identifier = new ModelIdentifier(id, "test");
    IModel model = new DummyModel();
    instance.storeModel(identifier, model);
    assertTrue(instance.contains(identifier));
  }

  @Test
  public void removesAllModelsForId() throws Exception {
    IModelIdentifier identifier = new ModelIdentifier(id, "test");
    IModel model = new DummyModel();
    instance.storeModel(identifier, model);
    instance.clearAllModels(id);
    assertFalse(instance.contains(identifier));
  }
}