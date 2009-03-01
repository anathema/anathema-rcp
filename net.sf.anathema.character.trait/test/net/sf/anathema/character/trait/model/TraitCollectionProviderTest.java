package net.sf.anathema.character.trait.model;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.model.TraitCollectionProvider;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Test;

public class TraitCollectionProviderTest {

  @Test
  public void returnsEmptyTraitCollectionIsNullModelId() throws Exception {
    IModelCollection modelCollection = createMock(IModelCollection.class);
    replay(modelCollection);
    TraitCollectionProvider provider = new TraitCollectionProvider(modelCollection, null);
    assertEquals(0, provider.getModel(new DummyCharacterId()).getAllTraits().length);
  }

  @Test
  public void returnsTraitCollectionFromModelCollection() throws Exception {
    TraitCollection collection = new TraitCollection(new BasicTrait(new Identificate("TraitItIs"))); //$NON-NLS-1$
    DummyCharacterId characterId = new DummyCharacterId();
    IModelCollection modelCollection = createMock(IModelCollection.class);
    expect(modelCollection.getModel(new ModelIdentifier(characterId, "modelId"))).andReturn(collection); //$NON-NLS-1$
    replay(modelCollection);
    TraitCollectionProvider provider = new TraitCollectionProvider(modelCollection, "modelId"); //$NON-NLS-1$
    assertSame(collection, provider.getModel(characterId));
  }
}