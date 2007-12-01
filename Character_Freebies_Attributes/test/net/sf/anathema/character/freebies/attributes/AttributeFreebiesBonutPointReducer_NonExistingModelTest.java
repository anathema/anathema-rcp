package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.AttributeGroupConfiguration;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.AttributeFreebiesBonusPointReducer_ExistingModelTest.DummyCreditManager;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeFreebiesBonutPointReducer_NonExistingModelTest {


  private AttributeFreebiesBonusPointReducer reducer;
  private ITraitCollectionModel attributes;
  private ICharacterId characterId;

  @Before
  public void createReducer() throws Exception {
    this.attributes = Attributes.create(
        new AttributeGroupConfiguration().getGroups(),
        new EssenceSensitiveTraitTemplate());
    this.characterId = EasyMock.createMock(ICharacterId.class);
    IModelCollection modelProvider = CharacterObjectMother.createEmptyModelProvider();
    IModelResourceHandler resourceManager = CharacterObjectMother.createEmptyResourceHandler();
    this.reducer = new AttributeFreebiesBonusPointReducer(modelProvider, resourceManager, new DummyCreditManager());
  }
  
  @Test
  public void hasZeroPoints() throws Exception {
    assertEquals(0, reducer.getPoints(characterId));
  }
}