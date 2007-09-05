package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IBasicTrait;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeFavorizationHandlerWithCreditsTest {

  private AttributeFavorizationHandler attributeFavorizationHandler;
  private IAttributes attributes;

  @Before
  public void createFavorizationHandlerWithFavoredCredit1() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    ICreditManager creditManager = EasyMock.createMock(ICreditManager.class);
    EasyMock.expect(creditManager.getCredit(characterId, "net.sf.anathema.character.attributes.favored")).andReturn(1).anyTimes(); //$NON-NLS-1$
    AttributeTemplate attributeTemplate = new AttributeTemplate();
    attributes = Attributes.create(attributeTemplate.getGroups(), attributeTemplate.getTraitTemplate());
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, IAttributes.MODEL_ID);
    IModelProvider modelProvider = CharacterObjectMother.createModelProvider(modelIdentifier, attributes);
    EasyMock.replay(creditManager);
    attributeFavorizationHandler = new AttributeFavorizationHandler(characterId, modelProvider, creditManager);
  }

  @Test
  public void isFavorable() throws Exception {
    assertTrue(attributeFavorizationHandler.isFavorable());
  }
  
  @Test
  public void unfavoredAttributeIsSetFavoredOnToggleFavored() throws Exception {
    IBasicTrait trait = attributes.getTraits()[0];
    assertFalse(trait.getFavoredModel().getValue());
    attributeFavorizationHandler.toogleFavored(trait.getTraitType());
    assertTrue(trait.getFavoredModel().getValue());
  }

  @Test
  public void otherUnfavoredAttributeIsSetFavoredOnToggleFavored() throws Exception {
    IBasicTrait trait = attributes.getTraits()[1];
    assertFalse(trait.getFavoredModel().getValue());
    attributeFavorizationHandler.toogleFavored(trait.getTraitType());
    assertTrue(trait.getFavoredModel().getValue());
  }
  
  @Test
  public void favoredAttributeIsSetUnfavoredOnToggleFavored() throws Exception {
    IBasicTrait trait = attributes.getTraits()[0];
    trait.getFavoredModel().setValue(true);
    attributeFavorizationHandler.toogleFavored(trait.getTraitType());
    assertFalse(trait.getFavoredModel().getValue());
  }
  
  @Test
  public void toggleFavoredHasNoEffectWhenFavorizationLimitIsReached() throws Exception {
    attributes.getTraits()[0].getFavoredModel().setValue(true);
    IBasicTrait toggleTrait = attributes.getTraits()[1];
    attributeFavorizationHandler.toogleFavored(toggleTrait.getTraitType());
    assertFalse(toggleTrait.getFavoredModel().getValue());
  }
}