package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.AttributeGroupConfiguration;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeFreebiesBonusPointReducer_ExistingModelTest {

  public static final class DummyCreditManager implements ICreditManager {
    private static final int DEFAULT_CREDIT = 3;

    @Override
    public boolean hasCredit(String templateId, String creditId) {
      return true;
    }

    @Override
    public int getCredit(ICharacterId characterId, String creditId) {
      return DEFAULT_CREDIT;
    }
  }

  private AttributeFreebiesBonusPointReducer reducer;
  private ITraitCollectionModel attributes;
  private ICharacterId characterId;

  @Before
  public void createReducer() throws Exception {
    this.attributes = Attributes.create(
        new AttributeGroupConfiguration().getGroups(),
        new EssenceSensitiveTraitTemplate());
    this.characterId = EasyMock.createMock(ICharacterId.class);
    IModelCollection modelProvider = AttributeObjectMother.createModelProvider(attributes, characterId);
    this.reducer = new AttributeFreebiesBonusPointReducer(modelProvider, null, new DummyCreditManager());
  }

  @Test
  public void returns0ForNoCharacter() throws Exception {
    assertEquals(0, reducer.getPoints(null));
  }

  @Test
  public void returns0ForAllAttributes1() throws Exception {
    setAllAttributesTo(1);
    assertEquals(0, reducer.getPoints(characterId));
  }

  @Test
  public void returnsNegative4ForOneAttributeAt2() throws Exception {
    setAllAttributesTo(1);
    attributes.getTraits()[0].getCreationModel().setValue(2);
    assertEquals(-4, reducer.getPoints(characterId));
  }

  @Test
  public void returnsNegative3ForOneFavoredAttributeAt2() throws Exception {
    setAllAttributesTo(1);
    IBasicTrait attribute = attributes.getTraits()[0];
    attribute.getCreationModel().setValue(2);
    attribute.getFavoredModel().setValue(true);
    assertEquals(-3, reducer.getPoints(characterId));
  }

  @Test
  public void returnsNegative36ForAllAttributeAt2() throws Exception {
    setAllAttributesTo(2);
    assertEquals(-36, reducer.getPoints(characterId));
  }

  @Test
  public void noReductionCalculatedForPointsExceedingCredit() throws Exception {
    setAllAttributesTo(2);
    attributes.getTraits()[0].getCreationModel().setValue(3);
    assertEquals(-36, reducer.getPoints(characterId));
  }

  private void setAllAttributesTo(int value) {
    for (IBasicTrait trait : attributes.getTraits()) {
      trait.getCreationModel().setValue(value);
    }
  }
}