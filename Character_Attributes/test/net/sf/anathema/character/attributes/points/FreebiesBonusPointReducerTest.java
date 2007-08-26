package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.AttributeObjectMother;
import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IBasicTrait;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FreebiesBonusPointReducerTest {

  private FreebiesBonusPointReducer reducer;
  private IAttributes attributes;
  private ICharacterId characterId;

  @Before
  public void createReducer() throws Exception {
    this.attributes = Attributes.create(new AttributeTemplate().getGroups());
    this.characterId = EasyMock.createMock(ICharacterId.class);
    IModelProvider modelProvider = AttributeObjectMother.createModelProvider(attributes, characterId);
    this.reducer = new FreebiesBonusPointReducer(modelProvider, new ICreditManager() {
    
      @Override
      public boolean hasCredit(String templateId, String creditId) {
        return true;
      }
    
      @Override
      public int getCredit(ICharacterId characterId, String creditId) {
        return 3;
      }
    });
  }
  
  @Test
  public void returns0ForNoCharacter() throws Exception {
    assertEquals(0, reducer.getPoints(null));
  }
  
  @Test
  public void returns0ForAllAttributes1() throws Exception {
    for (IBasicTrait trait : attributes.getTraits() ) {
      trait.getCreationModel().setValue(1);
    }
    assertEquals(0, reducer.getPoints(characterId));
  }
  
  @Test
  public void returnsNegative4ForOneAttributeAt2() throws Exception {
    for (IBasicTrait trait : attributes.getTraits() ) {
      trait.getCreationModel().setValue(1);
    }
    attributes.getTraits()[0].getCreationModel().setValue(2);
    assertEquals(-4, reducer.getPoints(characterId));
  }
  
  @Test
  public void returnsNegative36ForAllAttributeAt2() throws Exception {
    for (IBasicTrait trait : attributes.getTraits() ) {
      trait.getCreationModel().setValue(2);
    }
    assertEquals(-36, reducer.getPoints(characterId));
  }
}