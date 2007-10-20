package net.sf.anathema.character.points.problems;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.PointsObjectMother;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class BonusPointProviderTest {

  private DummyExperience dummyExperience;
  private DummyModelCollection modelCollection;
  private BonusPointsProvider bonusPointsProvider;
  private ICharacterId characterId;
  private ICharacterTemplate characterTemplate;
  private IBonusPointContainer bonusPointContainer;

  @Before
  public void createProviderWithFiveSpentBonusPoints() throws Exception {
    this.characterId = EasyMock.createMock(ICharacterId.class);
    this.characterTemplate = TemplateProviderObjectMother.createTemplate("templateId"); //$NON-NLS-1$
    modelCollection = new DummyModelCollection();
    dummyExperience = new DummyExperience();
    modelCollection.addModel(IExperience.MODEL_ID, dummyExperience);
    bonusPointContainer = EasyMock.createMock(IBonusPointContainer.class);
    bonusPointsProvider = new BonusPointsProvider(modelCollection, TemplateProviderObjectMother.createTemplateProvider(
        characterId,
        characterTemplate), bonusPointContainer, PointsObjectMother.createPointConfigurationProvider(
        characterTemplate,
        PointsObjectMother.createPointConfiguration(characterId, 5)));
  }

  @Test
  public void zeroBonusPointsAvailableOnExperienced() throws Exception {
    dummyExperience.setExperienced(true);
    assertEquals(0, bonusPointsProvider.getAvailableBonusPoints(characterId));
  }

  @Test
  public void twoBonusPointsAvailableForBonusPointCreditSeven() throws Exception {
    EasyMock.expect(bonusPointContainer.getBonusPoints(characterTemplate.getId())).andReturn(7);
    EasyMock.replay(bonusPointContainer);
    assertEquals(2, bonusPointsProvider.getAvailableBonusPoints(characterId));
  }

  @Test
  public void minus1BonusPointsAvailableForBonusPointCreditFour() throws Exception {
    EasyMock.expect(bonusPointContainer.getBonusPoints(characterTemplate.getId())).andReturn(4);
    EasyMock.replay(bonusPointContainer);
    assertEquals(-1, bonusPointsProvider.getAvailableBonusPoints(characterId));
  }
}