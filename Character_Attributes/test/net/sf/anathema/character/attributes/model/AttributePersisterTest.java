package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.persistence.TraitCollectionPersister;

import org.junit.Test;

public class AttributePersisterTest {

  @Test
  public void startValuesForNewAttributes() throws Exception {
    ITraitCollectionModel attributes = new TraitCollectionPersister().createNew(new DummyAttributeTemplate(0));
    for (IBasicTrait trait : attributes.getTraits()) {
      assertEquals(1, trait.getCreationModel().getValue());
    }
  }
}