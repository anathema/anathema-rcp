package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;

import org.junit.Before;
import org.junit.Test;

public class TraitCollection_TraitTest {

  private static final String TRAIT_ID = "Horst"; //$NON-NLS-1$
  private TraitCollection collection;
  private IBasicTrait trait;

  @Before
  public void createCollectionWithoutSubTrait() {
    final BasicTrait basicTrait = new BasicTrait(TRAIT_ID);
    collection = new TraitCollection(basicTrait);
    trait = collection.getTrait(TRAIT_ID);
  }

  @Test
  public void allowsCreationValueChange() throws Exception {
    trait.getCreationModel().setValue(3);
    assertEquals(3, trait.getCreationModel().getValue());
  }

  @Test
  public void allowsExperiencedValueChange() throws Exception {
    trait.getExperiencedModel().setValue(3);
    assertEquals(3, trait.getExperiencedModel().getValue());
  }
}