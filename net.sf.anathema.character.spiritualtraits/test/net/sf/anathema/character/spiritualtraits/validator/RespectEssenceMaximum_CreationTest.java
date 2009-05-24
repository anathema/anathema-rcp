package net.sf.anathema.character.spiritualtraits.validator;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import static net.sf.anathema.character.trait.validator.ValidationDtoObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.DummyCharacter;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollectionModelFactory;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.validator.ISpecialValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

import org.junit.Before;
import org.junit.Test;

public class RespectEssenceMaximum_CreationTest {

  private ValidationDto validationDto;
  private ISpecialValidator validator;
  private ITraitCollectionModel collection;
  private IExperience dummyExperience;

  @Before
  public void createValidationDto() throws Exception {
    TraitGroup[] groups = new SpiritualTraitGroupTemplate().getGroups();
    collection = TraitCollectionModelFactory.create(groups);
    this.validationDto = ForModel(MODEL_ID, collection);
    dummyExperience = Experience.Create(false);
    ((DummyCharacter) validationDto.container).modelsById.put(IExperience.MODEL_ID, dummyExperience);
  }

  @Before
  public void createValidator() throws Exception {
    validator = new RespectEssenceMaximum();
  }

  @Test
  public void restrictsValueTo5OnCreation() throws Exception {
    setExperiencedEssenceValue(7);
    validator.initValidation(validationDto);
    assertThat(validator.getValidValue(9), is(5));
  }

  @Test
  public void restrictsValueToHighEssenceValueOnExperience() throws Exception {
    dummyExperience.setExperienced(true);
    setExperiencedEssenceValue(7);
    validator.initValidation(validationDto);
    assertThat(validator.getValidValue(9), is(7));
  }

  @Test
  public void restrictsValue5WithLowEssenceOnExperience() throws Exception {
    dummyExperience.setExperienced(true);
    setExperiencedEssenceValue(3);
    validator.initValidation(validationDto);
    assertThat(validator.getValidValue(9), is(5));
  }

  private void setExperiencedEssenceValue(int value) {
    collection.getTrait(ESSENCE_ID).getExperiencedModel().setValue(value);
  }
}