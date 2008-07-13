package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;

import org.junit.Before;
import org.junit.Test;

public class TraitCollection_SubtraitTest {

  private static final String TRAIT_ID = "Horst"; //$NON-NLS-1$
  private TraitCollection collection;

  @Before
  public void createCollectionAndAddSubTrait() {
    collection = new TraitCollection(new BasicTrait(TRAIT_ID));
    BasicTrait subTrait = new BasicTrait("Hugo"); //$NON-NLS-1$
    collection.addSubTrait(TRAIT_ID, subTrait);
  }

  @Test
  public void isDirty() throws Exception {
    assertTrue(collection.isDirty());
  }

  @Test
  public void isDirtyAfterSubTraitCreationValueChange() throws Exception {
    collection.setClean();
    IIntValueModel valueModel = collection.getSubTraits(TRAIT_ID).get(0).getCreationModel();
    valueModel.setValue(valueModel.getValue() + 1);
    assertTrue(collection.isDirty());
  }

  @Test
  public void isDirtyAfterSubTraitExperiencedValueChange() throws Exception {
    collection.setClean();
    IIntValueModel valueModel = collection.getSubTraits(TRAIT_ID).get(0).getExperiencedModel();
    valueModel.setValue(valueModel.getValue() + 1);
    assertTrue(collection.isDirty());
  }
}