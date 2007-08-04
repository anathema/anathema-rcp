package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.model.Experience;
import net.sf.anathema.character.points.ExperienceViewTitleFactory;
import net.sf.anathema.character.points.IExperienceProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ExperienceTitleFactoryTest {

  private ExperienceViewTitleFactory factory;
  private IExperienceProvider experienceProvider;

  @Before
  public void createFactory() {
    this.experienceProvider = EasyMock.createNiceMock(IExperienceProvider.class);
    this.factory = new ExperienceViewTitleFactory(experienceProvider);
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

  private Experience createExperience(boolean experienced) {
    Experience experience = new Experience();
    experience.setExperienced(experienced);
    return experience;
  }
}