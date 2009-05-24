package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.view.CharacterPointsViewTitleFactory;
import net.sf.anathema.character.points.view.IExperienceProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ExperienceTitleFactoryTest {

  private CharacterPointsViewTitleFactory factory;
  private IExperienceProvider experienceProvider;

  @Before
  public void createFactory() {
    this.experienceProvider = EasyMock.createNiceMock(IExperienceProvider.class);
    this.factory = new CharacterPointsViewTitleFactory(experienceProvider);
  }

  @Test
  public void returnsNeutralNameForNoExperience() throws Exception {
    EasyMock.replay(experienceProvider);
    assertEquals("Character Points", factory.create()); //$NON-NLS-1$
  }

  @Test
  public void createsExperienceNameForExperienced() throws Exception {
    EasyMock.expect(experienceProvider.getExperience()).andReturn(createExperience(true));
    EasyMock.replay(experienceProvider);
    assertEquals("Experience Points", factory.create()); //$NON-NLS-1$
  }

  @Test
  public void createsBonusPointNameForUnexperienced() throws Exception {
    EasyMock.expect(experienceProvider.getExperience()).andReturn(createExperience(false));
    EasyMock.replay(experienceProvider);
    assertEquals("Bonus Points", factory.create()); //$NON-NLS-1$
  }

  private IExperience createExperience(boolean experienced) {
    IExperience experience = new Experience();
    experience.setExperienced(experienced);
    return experience;
  }
}