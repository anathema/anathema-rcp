package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

import org.junit.Test;

public class AttributePersisterTest {

  @Test
  public void startValuesForNewAttributes() throws Exception {
    ITraitCollectionModel attributes = new AttributesPersister().createNew(new AttributeTemplate());
    for (IBasicTrait trait : attributes.getTraits()) {
      assertEquals(1, trait.getCreationModel().getValue());
    }
  }
}