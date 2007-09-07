package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeFavorizationHandlerWithoutCreditsTest {

  private AbstractTraitCollectionFavorizationHandler attributeFavorizationHandler;
  private ITraitCollectionModel attributes;

  @Before
  public void createFavorizationHandler() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    ICreditManager creditManager = EasyMock.createMock(ICreditManager.class);
    EasyMock.expect(creditManager.getCredit(characterId, "net.sf.anathema.character.attributes.count.favored")).andReturn(0); //$NON-NLS-1$
    AttributeTemplate attributeTemplate = new AttributeTemplate();
    attributes = Attributes.create(attributeTemplate.getGroups(), attributeTemplate.getTraitTemplate());
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, Attributes.MODEL_ID);
    IModelProvider modelProvider = CharacterObjectMother.createModelProvider(modelIdentifier, attributes);
    EasyMock.replay(creditManager);
    attributeFavorizationHandler = new AttributeFavorizationHandler(characterId, modelProvider, creditManager);
  }

  @Test
  public void isNotFavorable() throws Exception {
    assertFalse(attributeFavorizationHandler.isFavorable());
  }
  
  @Test
  public void attributeIsToggledToFavored() throws Exception {
    IBasicTrait trait = attributes.getTraits()[0];
    assertFalse(trait.getFavoredModel().getValue());
    attributeFavorizationHandler.toogleFavored(trait.getTraitType());
    assertFalse(trait.getFavoredModel().getValue());
  }
}