package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.IBasicTrait;

import org.junit.Test;

public class AttributePersisterTest {

  @Test
  public void startValuesForNewAttributes() throws Exception {
    IAttributes attributes = new AttributesPersister().createNew();
    for (IBasicTrait trait : attributes.getTraits()) {
      assertEquals(1, trait.getCreationModel().getValue());
    }
  }
}