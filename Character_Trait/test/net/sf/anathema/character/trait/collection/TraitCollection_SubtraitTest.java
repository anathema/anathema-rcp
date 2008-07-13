package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
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

  @Test
  public void changesTraitCreationValueToHighSubTraitValue() throws Exception {
    IBasicTrait trait = collection.getTrait(TRAIT_ID);
    IBasicTrait subtrait = collection.getSubTraits(TRAIT_ID).get(0);
    IIntValueModel valueModel = subtrait.getCreationModel();
    int increasedSubtraitValue = trait.getCreationModel().getValue() + 1;
    valueModel.setValue(increasedSubtraitValue);
    assertEquals(increasedSubtraitValue, trait.getCreationModel().getValue());
  }

  @Test
  public void keepsHighCreationTraitValue() throws Exception {
    IBasicTrait trait = collection.getTrait(TRAIT_ID);
    IBasicTrait subtrait = collection.getSubTraits(TRAIT_ID).get(0);
    IIntValueModel valueModel = subtrait.getCreationModel();
    int highTraitValue = trait.getCreationModel().getValue();
    valueModel.setValue(highTraitValue - 1);
    assertEquals(highTraitValue, trait.getCreationModel().getValue());
  }

  @Test
  public void changesTraitExperienceValueToHighSubTraitValue() throws Exception {
    IBasicTrait trait = collection.getTrait(TRAIT_ID);
    IBasicTrait subtrait = collection.getSubTraits(TRAIT_ID).get(0);
    IIntValueModel valueModel = subtrait.getExperiencedModel();
    int increasedSubtraitValue = trait.getExperiencedModel().getValue() + 1;
    valueModel.setValue(increasedSubtraitValue);
    assertEquals(increasedSubtraitValue, trait.getExperiencedModel().getValue());
  }

  @Test
  public void keepsHighExperienceTraitValue() throws Exception {
    IBasicTrait trait = collection.getTrait(TRAIT_ID);
    IBasicTrait subtrait = collection.getSubTraits(TRAIT_ID).get(0);
    IIntValueModel valueModel = subtrait.getExperiencedModel();
    int highTraitValue = trait.getExperiencedModel().getValue();
    valueModel.setValue(highTraitValue - 1);
    assertEquals(highTraitValue, trait.getExperiencedModel().getValue());
  }
}