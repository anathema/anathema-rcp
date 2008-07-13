package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.status.FavoredStatus;

import org.junit.Before;
import org.junit.Test;

public class TraitCollection_SubtraitTest {

  private static final String TRAIT_ID = "Horst"; //$NON-NLS-1$
  private TraitCollection collection;
  private IBasicTrait trait;
  private BasicTrait subtrait;

  @Before
  public void createCollectionAndAddSubTrait() {
    collection = new TraitCollection(new BasicTrait(TRAIT_ID));
    subtrait = new BasicTrait("Hugo"); //$NON-NLS-1$
    collection.addSubTrait(TRAIT_ID, subtrait);
    trait = collection.getTrait(TRAIT_ID);
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
    IIntValueModel valueModel = subtrait.getCreationModel();
    int increasedSubtraitValue = trait.getCreationModel().getValue() + 1;
    valueModel.setValue(increasedSubtraitValue);
    assertEquals(increasedSubtraitValue, trait.getCreationModel().getValue());
  }

  @Test
  public void decreasesTraitCreationValueAlongWithSubTrait() throws Exception {
    IIntValueModel subTraitValueModel = subtrait.getCreationModel();
    IIntValueModel traitValueModel = trait.getCreationModel();
    traitValueModel.setValue(4);
    int decreasedSubtraitValue = traitValueModel.getValue() - 1;
    subTraitValueModel.setValue(decreasedSubtraitValue);
    assertEquals(decreasedSubtraitValue, trait.getCreationModel().getValue());
  }

  @Test
  public void keepsHighCreationValueFromSubTrait() throws Exception {
    BasicTrait highSubtrait = new BasicTrait("Hoch ich bin"); //$NON-NLS-1$
    collection.addSubTrait(TRAIT_ID, highSubtrait);
    int highTraitValue = 3;
    highSubtrait.getCreationModel().setValue(highTraitValue);
    IIntValueModel valueModel = subtrait.getCreationModel();
    valueModel.setValue(highTraitValue - 1);
    assertEquals(highTraitValue, trait.getCreationModel().getValue());
  }

  @Test
  public void increasesTraitExperienceValueAlongWithSubTrait() throws Exception {
    IIntValueModel valueModel = subtrait.getExperiencedModel();
    int increasedSubtraitValue = trait.getExperiencedModel().getValue() + 1;
    valueModel.setValue(increasedSubtraitValue);
    assertEquals(increasedSubtraitValue, trait.getExperiencedModel().getValue());
  }

  @Test
  public void decreasesTraitExperienceValueAlongWithSubTrait() throws Exception {
    IIntValueModel subTraitValueModel = subtrait.getExperiencedModel();
    IIntValueModel traitValueModel = trait.getExperiencedModel();
    traitValueModel.setValue(4);
    int decreasedSubtraitValue = traitValueModel.getValue() - 1;
    subTraitValueModel.setValue(decreasedSubtraitValue);
    assertEquals(decreasedSubtraitValue, trait.getExperiencedModel().getValue());
  }

  @Test
  public void keepsHighExperienceValueFromSubTrait() throws Exception {
    BasicTrait highSubtrait = new BasicTrait("Hoch ich bin"); //$NON-NLS-1$
    collection.addSubTrait(TRAIT_ID, highSubtrait);
    int highTraitValue = 3;
    highSubtrait.getExperiencedModel().setValue(highTraitValue);
    IIntValueModel valueModel = subtrait.getExperiencedModel();
    valueModel.setValue(highTraitValue - 1);
    assertEquals(highTraitValue, trait.getExperiencedModel().getValue());
  }

  @Test
  public void marksSubTraitAsFavoredAlongWithParentTrait() throws Exception {
    trait.getStatusManager().setStatus(new FavoredStatus());
    assertTrue(subtrait.getStatusManager().getStatus() instanceof FavoredStatus);
  }
}